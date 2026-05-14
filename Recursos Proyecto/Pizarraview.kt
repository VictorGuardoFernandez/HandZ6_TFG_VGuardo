package com.example.handball

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController

// ── Modelos ──────────────────────────────────────────────────────────────────

data class DrawnPath(
    val points: List<Offset>,
    val color: Color,
    val strokeWidth: Float
)

// ── Colores del campo ─────────────────────────────────────────────────────────

private val CourtFloor     = Color(0xFFD4833A)
private val CourtLines     = Color.White
private val GoalAreaColor  = Color(0x33FFFFFF)
private val GoalColor      = Color(0xFFCCCCCC)

// ── Pantalla principal ────────────────────────────────────────────────────────


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun HandballCourtScreen() {
    val drawingPaths   = remember { mutableStateListOf<DrawnPath>() }
    val currentPoints  = remember { mutableStateListOf<Offset>() }
    var selectedColor  by remember { mutableStateOf(Color.Red) }
    var strokeWidth    by remember { mutableStateOf(6f) }
    var isEraser       by remember { mutableStateOf(false) }

    val drawColors = listOf(
        Color.Red, Color(0xFF1565C0), Color(0xFF2E7D32),
        Color(0xFFF9A825), Color.White, Color.Black
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A2E)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título
        Text(
            text = "HANDBALL BOARD",
            color = Color.White,
            fontSize = 20.sp,
            letterSpacing = 4.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        // Campo de juego
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(isEraser, selectedColor, strokeWidth) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                currentPoints.clear()
                                currentPoints.add(offset)
                            },
                            onDrag = { change, _ ->
                                currentPoints.add(change.position)
                            },
                            onDragEnd = {
                                if (currentPoints.size > 1) {
                                    if (isEraser) {
                                        // Borrar trazos cercanos
                                        val eraseRadius = strokeWidth * 3
                                        drawingPaths.removeAll { path ->
                                            path.points.any { pt ->
                                                currentPoints.any { ep ->
                                                    (pt - ep).getDistance() < eraseRadius
                                                }
                                            }
                                        }
                                    } else {
                                        drawingPaths.add(
                                            DrawnPath(
                                                points = currentPoints.toList(),
                                                color = selectedColor,
                                                strokeWidth = strokeWidth
                                            )
                                        )
                                    }
                                }
                                currentPoints.clear()
                            }
                        )
                    }
            ) {
                // 1. Dibujar campo
                drawHandballCourt()

                // 2. Dibujar trazos guardados
                for (path in drawingPaths) {
                    drawSmoothPath(path.points, path.color, path.strokeWidth)
                }

                // 3. Trazo en curso
                if (currentPoints.size > 1) {
                    val activeColor = if (isEraser) Color(0x88FF5555) else selectedColor
                    drawSmoothPath(currentPoints, activeColor, strokeWidth)
                }
            }
        }

        // ── Barra de herramientas ──────────────────────────────────────────
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF16213E),
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // Colores
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Color", color = Color(0xFFAAAAAA), fontSize = 12.sp,
                        modifier = Modifier.width(46.dp))
                    drawColors.forEach { color ->
                        val isSelected = !isEraser && selectedColor == color
                        Box(
                            modifier = Modifier
                                .size(if (isSelected) 34.dp else 28.dp)
                                .background(color, CircleShape)
                                .then(
                                    if (isSelected) Modifier.background(
                                        Color.Transparent, CircleShape
                                    ) else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(if (isSelected) 34.dp else 28.dp)
                                    .background(color, CircleShape)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        selectedColor = color
                                        isEraser = false
                                    },
                                contentAlignment = Alignment.Center
                            ){

                            }
                        }
                    }

                    // Botón borrador
                    Spacer(Modifier.weight(1f))
                    Button(
                        onClick = { isEraser = !isEraser },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isEraser) Color(0xFFE53935) else Color(0xFF37474F)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text("✏ Borrar", fontSize = 12.sp)
                    }
                }

                // Grosor del trazo
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Grosor", color = Color(0xFFAAAAAA), fontSize = 12.sp,
                        modifier = Modifier.width(46.dp))
                    Slider(
                        value = strokeWidth,
                        onValueChange = { strokeWidth = it },
                        valueRange = 3f..20f,
                        modifier = Modifier.weight(1f),
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF4FC3F7),
                            activeTrackColor = Color(0xFF4FC3F7)
                        )
                    )
                    Box(
                        modifier = Modifier
                            .size(strokeWidth.dp.coerceIn(3.dp, 20.dp))
                            .background(
                                if (isEraser) Color.Gray else selectedColor,
                                CircleShape
                            )
                    )
                }

                // Botones acción
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            if (drawingPaths.isNotEmpty()) drawingPaths.removeLast()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF555555))
                    ) {
                        Text("↩ Deshacer", fontSize = 12.sp)
                    }
                    Button(
                        onClick = { drawingPaths.clear() },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF37474F))
                    ) {
                        Text("🗑 Limpiar", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

// ── Dibuja el campo completo ──────────────────────────────────────────────────

private fun DrawScope.drawHandballCourt() {
    val w = size.width
    val h = size.height

    // Fondo del campo
    drawRect(color = CourtFloor)

    val lineStroke = Stroke(width = w * 0.008f)
    val thinStroke = Stroke(width = w * 0.004f)

    // ── Portería izquierda ────────────────────────────────────────────────────
    val goalWidth  = h * 0.12f
    val goalDepth  = w * 0.03f
    val goalTop    = (h - goalWidth) / 2f
    drawGoal(left = true, goalTop = goalTop, goalWidth = goalWidth, goalDepth = goalDepth)

    // ── Portería derecha ──────────────────────────────────────────────────────
    drawGoal(left = false, goalTop = goalTop, goalWidth = goalWidth, goalDepth = goalDepth)

    // ── Área de 6 metros (izquierda) ──────────────────────────────────────────
    val sixMeterRadius = h * 0.38f
    drawGoalArea(
        centerX = 0f,
        centerY = h / 2f,
        radius = sixMeterRadius,
        fillColor = GoalAreaColor,
        strokeColor = CourtLines,
        stroke = lineStroke
    )

    // ── Área de 6 metros (derecha) ────────────────────────────────────────────
    drawGoalArea(
        centerX = w,
        centerY = h / 2f,
        radius = sixMeterRadius,
        fillColor = GoalAreaColor,
        strokeColor = CourtLines,
        stroke = lineStroke
    )

    // ── Línea de 9 metros punteada (izquierda) ────────────────────────────────
    val nineMeterRadius = h * 0.50f
    drawDashedArc(
        centerX = 0f,
        centerY = h / 2f,
        radius = nineMeterRadius,
        stroke = thinStroke
    )

    // ── Línea de 9 metros punteada (derecha) ──────────────────────────────────
    drawDashedArc(
        centerX = w,
        centerY = h / 2f,
        radius = nineMeterRadius,
        stroke = thinStroke,
        mirrorX = true
    )

    // ── Línea de 7 metros (izquierda) ─────────────────────────────────────────
    val sevenMeterX = w * 0.175f
    val sevenLineHalf = h * 0.03f
    drawLine(
        color = CourtLines,
        start = Offset(sevenMeterX, h / 2f - sevenLineHalf),
        end   = Offset(sevenMeterX, h / 2f + sevenLineHalf),
        strokeWidth = lineStroke.width
    )

    // ── Línea de 7 metros (derecha) ───────────────────────────────────────────
    drawLine(
        color = CourtLines,
        start = Offset(w - sevenMeterX, h / 2f - sevenLineHalf),
        end   = Offset(w - sevenMeterX, h / 2f + sevenLineHalf),
        strokeWidth = lineStroke.width
    )

    // ── Rectángulo exterior ───────────────────────────────────────────────────
    drawRect(
        color = CourtLines,
        topLeft = Offset.Zero,
        size = size,
        style = lineStroke
    )

    // ── Línea central ─────────────────────────────────────────────────────────
    drawLine(
        color = CourtLines,
        start = Offset(w / 2f, 0f),
        end   = Offset(w / 2f, h),
        strokeWidth = lineStroke.width
    )

    // ── Círculo central ───────────────────────────────────────────────────────
    val centerRadius = h * 0.09f
    drawCircle(
        color = CourtLines,
        radius = centerRadius,
        center = Offset(w / 2f, h / 2f),
        style = lineStroke
    )
    drawCircle(
        color = CourtLines,
        radius = lineStroke.width / 2f,
        center = Offset(w / 2f, h / 2f)
    )
}

// ── Portería ──────────────────────────────────────────────────────────────────

private fun DrawScope.drawGoal(
    left: Boolean,
    goalTop: Float,
    goalWidth: Float,
    goalDepth: Float
) {
    val w = size.width
    val x = if (left) 0f else w - goalDepth

    drawRect(
        color = GoalColor,
        topLeft = Offset(x, goalTop),
        size = Size(goalDepth, goalWidth)
    )
    drawRect(
        color = CourtLines,
        topLeft = Offset(x, goalTop),
        size = Size(goalDepth, goalWidth),
        style = Stroke(width = size.width * 0.005f)
    )
}

// ── Área semicircular ─────────────────────────────────────────────────────────

private fun DrawScope.drawGoalArea(
    centerX: Float,
    centerY: Float,
    radius: Float,
    fillColor: Color,
    strokeColor: Color,
    stroke: Stroke
) {
    val startAngle = if (centerX == 0f) -90f else 90f
    val rect = Rect(
        left   = centerX - radius,
        top    = centerY - radius,
        right  = centerX + radius,
        bottom = centerY + radius
    )
    drawArc(
        color      = fillColor,
        startAngle = startAngle,
        sweepAngle = 180f,
        useCenter  = true,
        topLeft    = rect.topLeft,
        size       = rect.size
    )
    drawArc(
        color      = strokeColor,
        startAngle = startAngle,
        sweepAngle = 180f,
        useCenter  = false,
        topLeft    = rect.topLeft,
        size       = rect.size,
        style      = stroke
    )
}

// ── Arco punteado de 9 metros ─────────────────────────────────────────────────

private fun DrawScope.drawDashedArc(
    centerX: Float,
    centerY: Float,
    radius: Float,
    stroke: Stroke,
    mirrorX: Boolean = false
) {
    val startAngle = if (!mirrorX) -90f else 90f
    val rect = Rect(
        left   = centerX - radius,
        top    = centerY - radius,
        right  = centerX + radius,
        bottom = centerY + radius
    )
    drawArc(
        color      = CourtLines,
        startAngle = startAngle,
        sweepAngle = 180f,
        useCenter  = false,
        topLeft    = rect.topLeft,
        size       = rect.size,
        style      = Stroke(
            width      = stroke.width,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
        )
    )
}

// ── Trazo suavizado ───────────────────────────────────────────────────────────

private fun DrawScope.drawSmoothPath(
    points: List<Offset>,
    color: Color,
    strokeWidth: Float
) {
    if (points.size < 2) return
    val path = Path().apply {
        moveTo(points[0].x, points[0].y)
        for (i in 1 until points.size - 1) {
            val mid = Offset(
                (points[i].x + points[i + 1].x) / 2f,
                (points[i].y + points[i + 1].y) / 2f
            )
            quadraticBezierTo(points[i].x, points[i].y, mid.x, mid.y)
        }
        lineTo(points.last().x, points.last().y)
    }
    drawPath(
        path  = path,
        color = color,
        style = Stroke(
            width    = strokeWidth,
            cap      = StrokeCap.Round,
            join     = StrokeJoin.Round
        )
    )
}
