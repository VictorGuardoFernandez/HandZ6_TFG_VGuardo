package com.example.handz6.ui.theme

// ColorExtractor.kt
import androidx.palette.graphics.Palette
import android.graphics.Bitmap

fun extractColorsFromBitmap(bitmap: Bitmap): Palette {
    return Palette.from(bitmap)
        .maximumColorCount(16)
        .generate()
}