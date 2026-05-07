package com.dresta0056.tsundoku.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dresta0056.tsundoku.R
import com.dresta0056.tsundoku.model.Book
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
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
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
        ScreenContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()
    val books = viewModel.BooksDummy

    if (books.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
        ) {
            Text("there is no book")
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(books) {
                Text("book")
            }
        }
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