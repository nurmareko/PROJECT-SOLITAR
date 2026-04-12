package com.dresta0056.tsundoku.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TsundokuTopAppBar(
                title = {Text(stringResource(R.string.app_name))},
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
        TsundokuDashboard(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TsundokuDashboard(
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var pageCount by remember { mutableStateOf("") }
    var showSheet by remember { mutableStateOf(false) }

    val genres = listOf(
        stringResource(R.string.fiction),
        stringResource(R.string.non_fiction),
        stringResource(R.string.manga),
        stringResource(R.string.textbook)
    )
    var selectedGenre by remember { mutableStateOf(genres[0]) }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            modifier = Modifier
                .padding(top = 20.dp),
            painter = painterResource(R.drawable.book_stack),
            contentDescription = stringResource(R.string.stack_of_book_picture)
        )

        Text(stringResource(R.string.tagline))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(stringResource(R.string.title_field_text)) },
            supportingText = { Text("Error") }, // TODO
            isError = false,
            singleLine = true,
            keyboardOptions = KeyboardOptions()
        )

        OutlinedTextField(
            value = pageCount,
            onValueChange = { pageCount = it },
            label = { Text(stringResource(R.string.number_of_pages_field_text)) },
            trailingIcon = { Text(stringResource(R.string.pages)) },
            supportingText = { Text("Error") }, // TODO
            isError = false,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        GenreDropdownMenu(
            selectedValue = selectedGenre,
            options = genres,
            label = stringResource(R.string.genre),
            onValueChangedEvent = { newSelection ->
                selectedGenre = newSelection
            }
        )

        Button(onClick = { showSheet = true }) {
            Text(stringResource(R.string.result_button_text))
        }

        if (showSheet) {
            ResultBottomSheet(
                title = title,
                pageCount = pageCount,
                genre = selectedGenre,
                onDismiss = { showSheet = false }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDropdownMenu(
    selectedValue: String,
    options: List<String>,
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, true)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChangedEvent(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultBottomSheet(
    title: String,
    pageCount: String,
    genre: String,
    onDismiss: () -> Unit
) {
    val readingHours = estimateReadingHours(pageCount.toInt(), genre)

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        // Your bottom sheet content goes here
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(title)
            Text(genre)
            Text("Estimate reading time")
            Text(stringResource(R.string.estimate_reading_hours, readingHours))
            Text(stringResource(R.string.result_pages, pageCount))
            Card {
                Text("result message")
            }

            Row {
                Button(onClick = {}) {
                    Text("share")
                }
                Button(onClick = {}) {
                    Text("try another")
                }
            }
        }
    }
}

private fun estimateReadingHours(pageCount: Int, genre: String): Int {
    if (pageCount <= 0) return 0

    val pagesPerHour = when (genre.lowercase()) {
        "manga" -> 120
        "fiction" -> 60
        "nonfiction" -> 40
        "textbook" -> 20
        else -> 50 // fallback
    }

    return ceil(pageCount.toDouble() / pagesPerHour).toInt()
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    TsundokuTheme {
        MainScreen(rememberNavController())
    }
}