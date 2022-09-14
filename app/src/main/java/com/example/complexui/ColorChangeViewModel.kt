package com.example.complexui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ColorChangeViewModel:ViewModel() {
    var colorChange= mutableStateOf(0xFFD0CBD6)

    fun changeBackground(green: Long) {
        colorChange.value=green
    }
}