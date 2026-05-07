package com.dresta0056.tsundoku.ui.theme.screen

import androidx.lifecycle.ViewModel
import com.dresta0056.tsundoku.model.Book

class MainViewModel: ViewModel() {
    val BooksDummy = listOf(
        Book(
            title = "Frieren: Beyond Journey's End, Vol. 1",
            pageCount = 192,
            pagesRead = 0,
            genre = "Fantasy",
            dateAdded = System.currentTimeMillis() - 86400000L, // 1 day ago
            lastUpdated = System.currentTimeMillis() - 86400000L
        ),
        Book(
            title = "86 -Eighty Six-, Vol. 1",
            pageCount = 256,
            pagesRead = 15, // Started, but currently stalled
            genre = "Sci-Fi",
            dateAdded = System.currentTimeMillis() - 172800000L, // 2 days ago
            lastReadAt = System.currentTimeMillis() - 86400000L, // Last read 1 day ago
            lastUpdated = System.currentTimeMillis() - 86400000L
        ),
        Book(
            title = "NieR Replicant: Project Gestalt Recollections - File 01",
            pageCount = 304,
            pagesRead = 0,
            genre = "Sci-Fi / Fantasy",
            dateAdded = System.currentTimeMillis(), // Added today
            lastUpdated = System.currentTimeMillis()
        )
    )
}