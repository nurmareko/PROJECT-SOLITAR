package com.dresta0056.tsundoku.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val pageCount: Int,
    val pagesRead: Int = 0,
    val genre: String,
    val dateAdded: Long,
    val lastReadAt: Long? = null,
    val lastUpdated: Long,
    val deletedAt: Long? = null
)
