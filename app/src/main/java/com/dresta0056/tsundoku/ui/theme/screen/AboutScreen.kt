package com.dresta0056.tsundoku.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dresta0056.tsundoku.ui.theme.TsundokuTheme

@Composable
fun AboutScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Text("About Screen")
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview() {
    TsundokuTheme() {
        AboutScreen(rememberNavController())
    }
}