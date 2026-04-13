package com.dresta0056.tsundoku.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                title = {
                    Text(
                        text = stringResource(R.string.about),
                        fontStyle = FontStyle.Italic
                    )
                },
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
        AboutContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun AboutContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "積ん読",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 52.sp,
                letterSpacing = 8.sp
            ),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.about_pronunciation_label),
            style = MaterialTheme.typography.labelSmall.copy(
                letterSpacing = 3.sp,
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "tsun-doku",
            style = MaterialTheme.typography.titleMedium.copy(
                fontStyle = FontStyle.Italic
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\u201C",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                    modifier = Modifier.align(Alignment.Start)
                )

                Text(
                    text = buildAnnotatedString {
                        append(stringResource(R.string.about_definition_body))
                        append("\n")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.about_definition_bold))
                        }
                    },
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontStyle = FontStyle.Italic,
                        lineHeight = 28.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "\u201D",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Icon(
            imageVector = Icons.Outlined.AutoStories,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(48.dp))
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