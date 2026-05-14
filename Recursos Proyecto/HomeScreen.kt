package com.example.handz6.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.handz6.data.Entities.Equipo

// ── Colores ───────────────────────────────────────────────────────────────────

private val DarkBg       = Color(0xFF1A1A2E)
private val CardBg       = Color(0xFF16213E)
private val AccentOrange = Color(0xFFD4833A)
private val AccentGreen  = Color(0xFF2E5C40)
private val TextPrimary  = Color(0xFFEEEEEE)
private val TextMuted    = Color(0xFFAAAAAA)

// ── Pantalla Home ─────────────────────────────────────────────────────────────

@Composable
fun HomeScreen(
    equipos: List<Equipo>,
    onEquipoClick: (Equipo) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "HANDZ6",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp,
                    color = AccentOrange
                )
                Text(
                    text = "Mis equipos",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }

            // Botón añadir equipo
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(AccentOrange)
                    .clickable { /* onAddEquipo */ },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        HorizontalDivider(color = Color(0xFF2A2A3E), thickness = 0.5.dp)

        // Lista de equipos
        if (equipos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No hay equipos aún",
                        fontSize = 16.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Pulsa + para añadir tu primer equipo",
                        fontSize = 13.sp,
                        color = Color(0xFF666677)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(equipos, key = { it.id }) { equipo ->
                    EquipoCard(
                        equipo = equipo,
                        onClick = { onEquipoClick(equipo) }
                    )
                }
            }
        }
    }
}

// ── Card de equipo ────────────────────────────────────────────────────────────

@Composable
fun EquipoCard(
    equipo: Equipo,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Nombre del equipo (izquierda)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = equipo.nombre,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "ID: ${equipo.id}",
                    fontSize = 12.sp,
                    color = TextMuted
                )
            }

            // Logo genérico (derecha) — reemplazar con AsyncImage cuando tengas la URL
            LogoPlaceholder(nombre = equipo.nombre)
        }
    }
}

// ── Logo placeholder ──────────────────────────────────────────────────────────
// Muestra las iniciales del equipo hasta que tengas logos reales.
// Para usar imagen real: reemplaza por AsyncImage de Coil:
//   AsyncImage(model = equipo.logoUrl, contentDescription = equipo.nombre, ...)

@Composable
fun LogoPlaceholder(nombre: String) {
    val iniciales = nombre
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString("") { it.first().uppercase() }

    // Color del fondo basado en el nombre (siempre el mismo para el mismo equipo)
    val bgColor = when (nombre.first().lowercaseChar()) {
        in 'a'..'f' -> AccentOrange
        in 'g'..'m' -> AccentGreen
        in 'n'..'s' -> Color(0xFF1565C0)
        else        -> Color(0xFF6A1B9A)
    }

    Box(
        modifier = Modifier
            .size(52.dp)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = iniciales,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val equiposFake = listOf(
        Equipo(id = 1, nombre = "BM Gijón"),
        Equipo(id = 2, nombre = "Real Sporting"),
        Equipo(id = 3, nombre = "Atlético Valladolid"),
        Equipo(id = 4, nombre = "Nantes FC"),
    )
    HomeScreen(equipos = equiposFake)
}
