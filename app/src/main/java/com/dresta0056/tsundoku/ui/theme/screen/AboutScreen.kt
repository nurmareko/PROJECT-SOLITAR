package com.dresta0056.tsundoku.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dresta0056.tsundoku.R
import com.dresta0056.tsundoku.ui.theme.TsundokuTheme
import com.dresta0056.tsundoku.ui.theme.components.TsundokuTopAppBar

@Composable
fun AboutScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TsundokuTopAppBar(
                title = { Text(stringResource(R.string.about)) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        AboutScreen(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("積ん読")
        Text("Pronounciation")
        Text("tsun-doku")
        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ac porttitor nisi. Quisque sit amet quam magna. Nulla euismod, elit a volutpat elementum, justo est ullamcorper eros, vel hendrerit nisl velit blandit purus.")
        Icon(
            imageVector = Icons.Filled.StarBorder,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview() {
    TsundokuTheme {
        AboutScreen(rememberNavController())
    }
}