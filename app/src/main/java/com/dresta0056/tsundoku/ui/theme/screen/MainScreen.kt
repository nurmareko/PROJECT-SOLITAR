package com.dresta0056.tsundoku.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dresta0056.tsundoku.R
import com.dresta0056.tsundoku.navigation.Screen
import com.dresta0056.tsundoku.ui.theme.TsundokuTheme
import com.dresta0056.tsundoku.ui.theme.components.TsundokuTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TsundokuTopAppBar(
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Screen.About.route) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(top = 20.dp),
            painter = painterResource(R.drawable.book_stack),
            contentDescription = stringResource(R.string.stack_of_book_picture)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    TsundokuTheme {
        MainScreen(rememberNavController())
    }
}