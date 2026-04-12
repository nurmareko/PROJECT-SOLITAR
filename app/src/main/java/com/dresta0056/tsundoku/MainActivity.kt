package com.dresta0056.tsundoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dresta0056.tsundoku.navigation.SetupNavGraph
import com.dresta0056.tsundoku.ui.theme.TsundokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TsundokuTheme {
                SetupNavGraph()
            }
        }
    }
}