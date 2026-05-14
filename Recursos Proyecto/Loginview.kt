package com.example.handz6.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.handz6.R

// ── Colores (mover a Theme.kt cuando centralices) ─────────────────────────────
private val DarkBg       = Color(0xFF1A1A2E)
private val CardBg       = Color(0xFF16213E)
private val AccentOrange = Color(0xFFD4833A)
private val TextPrimary  = Color(0xFFEEEEEE)
private val TextMuted    = Color(0xFFAAAAAA)
private val FieldBorder  = Color(0xFF2A2A3E)

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit = { _, _ -> }
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(horizontal = 28.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Logo",
            modifier = Modifier.size(220.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Inicia sesión para continuar",
            fontSize = 13.sp,
            color = TextMuted,
            modifier = Modifier.padding(top = 4.dp, bottom = 36.dp)
        )

        // Campo usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario", color = TextMuted) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedBorderColor = AccentOrange,
                unfocusedBorderColor = FieldBorder,
                cursorColor = AccentOrange,
                focusedContainerColor = CardBg,
                unfocusedContainerColor = CardBg
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Campo contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", color = TextMuted) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedBorderColor = AccentOrange,
                unfocusedBorderColor = FieldBorder,
                cursorColor = AccentOrange,
                focusedContainerColor = CardBg,
                unfocusedContainerColor = CardBg
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Botón login
        Button(
            onClick = { onLoginClick(username, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentOrange,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )
        }
    }
}