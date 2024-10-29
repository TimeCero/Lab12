package com.example.lab12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab12.ui.theme.MapScreen

import androidx.activity.enableEdgeToEdge
import com.example.lab12.ui.theme.MapScreenWithBottomTypeSelector


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapScreen()
            MapScreenWithBottomTypeSelector()
        }
    }
}