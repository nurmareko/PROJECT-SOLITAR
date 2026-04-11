package com.dresta0056.tsundoku.ui.theme.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dresta0056.tsundoku.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TsundokuTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        actions = actions,
        navigationIcon = navigationIcon
    )
}