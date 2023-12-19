package com.fgr.neonews.data.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_news")
data class NewsTable(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    val title: String,
    val source: String,
    val imageUrl: String,
    val newsUrl: String,
    val description: String,
    val publishedAt: String,
    val createdAt: Long,
)