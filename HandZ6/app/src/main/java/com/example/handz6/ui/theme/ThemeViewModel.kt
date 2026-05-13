package com.example.handz6.ui.theme

import android.graphics.Bitmap
import androidx.compose.material3.ColorScheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ThemeViewModel.kt
class ThemeViewModel : ViewModel() {
    private val _colorScheme = MutableStateFlow<ColorScheme?>(null)
    val colorScheme: StateFlow<ColorScheme?> = _colorScheme

    fun updateThemeFromBitmap(bitmap: Bitmap, darkTheme: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            val palette = extractColorsFromBitmap(bitmap)
            _colorScheme.value = palette.toColorScheme(darkTheme)
        }
    }
}