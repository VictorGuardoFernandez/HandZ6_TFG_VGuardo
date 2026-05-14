package com.example.handz6.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Colores ───────────────────────────────────────────────────────────────────
private val DarkBg       = Color(0xFF1A1A2E)
private val CardBg       = Color(0xFF16213E)
private val AccentOrange = Color(0xFFD4833A)
private val AccentGreen  = Color(0xFF2E5C40)
private val TextPrimary  = Color(0xFFEEEEEE)
private val TextMuted    = Color(0xFFAAAAAA)
private val GoalPost     = Color(0xFFCCCCCC)
private val ZoneEmpty    = Color(0x22FFFFFF)
private val ZoneHover    = Color(0x55FFFFFF)

// ── Modelo ────────────────────────────────────────────────────────────────────
data class GoalZoneState(
    val counts: List<Int> = List(9) { 0 }
)

// ── Pantalla principal ────────────────────────────────────────────────────────
@Composable
fun GoalTrackingScreen(
    teamLocalName: String = "Local",
    teamVisitanteName: String = "Visitante",
    onZoneClick: (team: String, zone: Int) -> Unit = { _, _ -> }
) {
    var localState    by remember { mutableStateOf(GoalZoneState()) }
    var visitanteState by remember { mutableStateOf(GoalZoneState()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // Header
        Text(
            text = "REGISTRO DE GOLES",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp,
            color = AccentOrange,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Totales
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ScoreChip(
                name = teamLocalName,
                total = localState.counts.sum(),
                color = AccentOrange
            )
            Text(
                text = "VS",
                color = TextMuted,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            ScoreChip(
                name = teamVisitanteName,
                total = visitanteState.counts.sum(),
                color = Color(0xFF1565C0)
            )
        }

        HorizontalDivider(color = Color(0xFF2A2A3E), thickness = 0.5.dp)

        // Portería local
        GoalSection(
            teamName = teamLocalName,
            accentColor = AccentOrange,
            state = localState,
            onZoneClick = { zone ->
                val newCounts = localState.counts.toMutableList()
                newCounts[zone] = newCounts[zone] + 1
                localState = localState.copy(counts = newCounts)
                onZoneClick(teamLocalName, zone)
            },
            onZoneLongClick = { zone ->
                val newCounts = localState.counts.toMutableList()
                if (newCounts[zone] > 0) newCounts[zone] = newCounts[zone] - 1
                localState = localState.copy(counts = newCounts)
            }
        )

        // Portería visitante
        GoalSection(
            teamName = teamVisitanteName,
            accentColor = Color(0xFF1565C0),
            state = visitanteState,
            onZoneClick = { zone ->
                val newCounts = visitanteState.counts.toMutableList()
                newCounts[zone] = newCounts[zone] + 1
                visitanteState = visitanteState.copy(counts = newCounts)
                onZoneClick(teamVisitanteName, zone)
            },
            onZoneLongClick = { zone ->
                val newCounts = visitanteState.counts.toMutableList()
                if (newCounts[zone] > 0) newCounts[zone] = newCounts[zone] - 1
                visitanteState = visitanteState.copy(counts = newCounts)
            }
        )

        // Botón reset
        OutlinedButton(
            onClick = {
                localState = GoalZoneState()
                visitanteState = GoalZoneState()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextMuted),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF444455))
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar registro", fontSize = 13.sp)
        }
    }
}

// ── Sección portería ──────────────────────────────────────────────────────────
@Composable
fun GoalSection(
    teamName: String,
    accentColor: Color,
    state: GoalZoneState,
    onZoneClick: (Int) -> Unit,
    onZoneLongClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Nombre del equipo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(accentColor, RoundedCornerShape(50))
            )
            Text(
                text = teamName.uppercase(),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 2.sp,
                color = accentColor
            )
        }

        // Portería
        GoalGrid(
            accentColor = accentColor,
            counts = state.counts,
            onZoneClick = onZoneClick,
            onZoneLongClick = onZoneLongClick
        )

        // Leyenda
        Text(
            text = "Toca para sumar · Mantén pulsado para restar",
            fontSize = 10.sp,
            color = Color(0xFF555566),
            textAlign = TextAlign.Center
        )
    }
}

// ── Grid de la portería ───────────────────────────────────────────────────────
@Composable
fun GoalGrid(
    accentColor: Color,
    counts: List<Int>,
    onZoneClick: (Int) -> Unit,
    onZoneLongClick: (Int) -> Unit
) {
    // Marco de la portería
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        // Postes y larguero
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .border(3.dp, GoalPost, RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                .background(Color(0x11FFFFFF))
        ) {
            // Grid 3x3
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                for (row in 0..2) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        for (col in 0..2) {
                            val zone = row * 3 + col
                            val count = counts[zone]
                            val hasGoals = count > 0

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(1.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(
                                        if (hasGoals) accentColor.copy(alpha = 0.15f + (count * 0.08f).coerceAtMost(0.5f))
                                        else ZoneEmpty
                                    )
                                    .border(
                                        width = if (hasGoals) 1.5.dp else 0.5.dp,
                                        color = if (hasGoals) accentColor.copy(alpha = 0.6f) else Color(0x33FFFFFF),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .clickable { onZoneClick(zone) },
                                contentAlignment = Alignment.Center
                            ) {
                                if (hasGoals) {
                                    Text(
                                        text = count.toString(),
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                } else {
                                    Text(
                                        text = zoneLabel(zone),
                                        fontSize = 10.sp,
                                        color = Color(0x44FFFFFF),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Línea de suelo de la portería
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .align(Alignment.BottomCenter)
                .background(GoalPost)
        )
    }
}

// ── Etiquetas de zona ─────────────────────────────────────────────────────────
private fun zoneLabel(zone: Int): String = when (zone) {
    0 -> "↖"
    1 -> "↑"
    2 -> "↗"
    3 -> "←"
    4 -> "·"
    5 -> "→"
    6 -> "↙"
    7 -> "↓"
    8 -> "↘"
    else -> ""
}

// ── Chip de puntuación ────────────────────────────────────────────────────────
@Composable
fun ScoreChip(name: String, total: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = total.toString(),
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = name,
            fontSize = 12.sp,
            color = TextMuted
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true)
@Composable
fun GoalTrackingScreenPreview() {
    GoalTrackingScreen(
        teamLocalName = "BM Gijón",
        teamVisitanteName = "BM Santander"
    )
}
