package com.example.handz6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.handz6.ui.theme.HandZ6Theme
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.handz6.data.Entities.Usuario
import com.example.handz6.view.LoginScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HandZ6Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}
// MainScreen.kt
@Composable
fun MainScreen() {
    var usuarioLogueado by rememberSaveable { mutableStateOf<Usuario?>(null) }

    if (usuarioLogueado == null) {
        LoginScreen { user , pass->

        }
    } else {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HandZ6Theme {
        Greeting("Android")
    }
}
