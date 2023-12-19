package com.fgr.neonews.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fgr.neonews.data.room.table.NewsTable

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(news: NewsTable)

    @Query("SELECT COUNT(*) FROM tb_news WHERE id = :id")
    suspend fun isFavorite(id: Long): Boolean

    @Query("SELECT * FROM tb_news ORDER BY createdAt DESC")
    fun getAllFavorite(): LiveData<List<NewsTable>>

    @Query("DELETE FROM tb_news WHERE id = :newsId")
    suspend fun deleteFavoriteById(newsId: Long)

    @Query("DELETE FROM tb_news")
    suspend fun deleteAllFavorite()
}