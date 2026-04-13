package com.dresta0056.tsundoku.ui.theme.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dresta0056.tsundoku.R
import com.dresta0056.tsundoku.navigation.Screen
import com.dresta0056.tsundoku.ui.theme.TsundokuTheme
import com.dresta0056.tsundoku.ui.theme.components.TsundokuTopAppBar
import kotlin.math.ceil
import kotlin.math.roundToInt

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
        TsundokuDashboard(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TsundokuDashboard(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val genres = listOf(
        "",
        stringResource(R.string.novel),
        stringResource(R.string.non_fiction),
        stringResource(R.string.manga),
        stringResource(R.string.textbook)
    )

    var title by remember { mutableStateOf("") }
    var titleError by remember { mutableStateOf(false) }

    var pageCount by remember { mutableStateOf("") }
    var pageCountError by remember { mutableStateOf(false) }

    var selectedGenre by remember { mutableStateOf(genres[0]) }
    var genreError by remember { mutableStateOf(false) }
    var showSheet by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(R.drawable.book_stack),
            contentDescription = stringResource(R.string.stack_of_book_picture),
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.tagline),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                if (titleError) titleError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.title_field_text)) },
            supportingText = { ErrorHint(titleError, stringResource(R.string.title_error)) },
            isError = titleError,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = pageCount,
            onValueChange = {
                pageCount = it
                if (pageCountError) pageCountError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.number_of_pages_field_text)) },
            trailingIcon = {
                Text(
                    text = stringResource(R.string.pages),
                    modifier = Modifier.padding(end = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            supportingText = { ErrorHint(pageCountError, stringResource(R.string.page_count_error)) },
            isError = pageCountError,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        GenreDropdownMenu(
            selectedValue = selectedGenre,
            options = genres,
            label = stringResource(R.string.genre),
            isError = genreError,
            errorMessage = stringResource(R.string.genre_error),
            onValueChangedEvent = { newSelection ->
                selectedGenre = newSelection
                if (genreError) genreError = false
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                titleError = title.isBlank()
                val pages = pageCount.toIntOrNull()
                pageCountError = pages == null || pages <= 0
                genreError = selectedGenre.isBlank()

                if (titleError || pageCountError || genreError) return@Button

                showSheet = true
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.result_button_text),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (showSheet) {
            ResultBottomSheet(
                title = title,
                pageCount = pageCount,
                genre = selectedGenre,
                onDismiss = { showSheet = false },
                onTryAnother = {
                    showSheet = false
                    title = ""
                    pageCount = ""
                    selectedGenre = genres[0]
                    titleError = false
                    pageCountError = false
                    genreError = false
                },
                context = context
            )
        }
    }
}

@Composable
fun ErrorHint(isError: Boolean, errorMessage: String) {
    if (isError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDropdownMenu(
    modifier: Modifier = Modifier,
    selectedValue: String,
    options: List<String>,
    label: String,
    isError: Boolean = false,
    errorMessage: String = "",
    onValueChangedEvent: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    true
                ),
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            isError = isError,
            supportingText = { ErrorHint(isError, errorMessage) },
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = if (index == 0)
                                stringResource(R.string.genre_placeholder)
                            else option,
                            color = if (index == 0)
                                MaterialTheme.colorScheme.onSurfaceVariant
                            else MaterialTheme.colorScheme.onSurface
                        )
                    },
                    onClick = {
                        onValueChangedEvent(option)
                        expanded = false
                    },
                    enabled = index != 0
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
    onDismiss: () -> Unit,
    onTryAnother: () -> Unit,
    context: Context
) {
    val readingHours = estimateReadingHours(pageCount.toInt(), genre)
    val message = resultMessage(readingHours)
    val shareText = buildShareText(title, readingHours, message)

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text(
                    text = genre,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Text(
                text = stringResource(R.string.estimate_reading_time),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = stringResource(R.string.estimate_reading_hours, readingHours),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = stringResource(R.string.result_pages, pageCount),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic
                    ),
                    color = MaterialTheme.colorScheme.onError
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { shareResult(context, shareText) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(vertical = 14.dp)
                ) {
                    Text(
                        text = stringResource(R.string.share),
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = onTryAnother,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(vertical = 14.dp)
                ) {
                    Text(
                        text = stringResource(R.string.try_another),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private fun estimateReadingHours(pageCount: Int, genre: String): Int {
    if (pageCount <= 0) return 0

    val (wpp, wpm) = when (genre.lowercase()) {
        "manga"       -> 30 to 300
        "novel"       -> 250 to 230
        "non-fiction"  -> 300 to 200
        "textbook"    -> 400 to 100
        else          -> 250 to 200
    }

    return ((pageCount * wpp) / (wpm * 60.0)).roundToInt()
}

@Composable
private fun resultMessage(readingHours: Int): String {
    val hoursPerDay = 0.5
    val daysNeeded = ceil(readingHours.toDouble() / hoursPerDay).toInt().coerceAtLeast(1)

    return stringResource(R.string.result_message, readingHours, daysNeeded)
}

private fun buildShareText(title: String, readingHours: Int, message: String): String {
    return "\uD83D\uDCDA \"$title\" will take me ~$readingHours hours to read. $message #Tsundoku"
}

@SuppressLint("QueryPermissionsNeeded")
private fun shareResult(context: Context, text: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }

    context.startActivity(
        Intent.createChooser(shareIntent, null)
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    TsundokuTheme {
        MainScreen(rememberNavController())
    }
}