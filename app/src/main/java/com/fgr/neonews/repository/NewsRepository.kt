package com.fgr.neonews.repository

import android.app.Application
import android.util.Log
import com.fgr.neonews.data.listener.CallbackListener
import com.fgr.neonews.data.remote.NewsApi
import com.fgr.neonews.data.response.ArticlesItem
import com.fgr.neonews.data.room.dao.NewsDao
import com.fgr.neonews.data.room.table.NewsTable
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val appContext: Application,
    private val newsApi: NewsApi,
    private val apiKey: String,
    private val newsDao: NewsDao,
) {
    suspend fun getLocalNews(
        callbackListener: CallbackListener<List<ArticlesItem>>,
        pageSize: Int = 5,
    ) {
        try {
            val result = newsApi.getHeadlines(
                country = "id",
                apiKey = apiKey,
                pageSize = pageSize
            )
            if (result.status == "ok") {
                callbackListener.onSuccess(result.articles ?: listOf())
            } else {
                // if status is 401 = Invalid API Key
                callbackListener.onFailure("Api Key Invalid or Limited")

            }
        } catch (e: Exception) {
            callbackListener.onFailure(e.message.toString())
            Log.e("LocalNews", e.message.toString())
        }
    }

    suspend fun getAbroadNews(
        callbackListener: CallbackListener<List<ArticlesItem>>,
        pageSize: Int = 5,
    ) {
        try {
            val result = newsApi.getHeadlines(
                country = "us",
                apiKey = apiKey,
                pageSize = pageSize
            )
            if (result.status == "ok") {
                callbackListener.onSuccess(result.articles ?: listOf())
            } else {
                // if status is 401 = Invalid API Key
                callbackListener.onFailure("Api Key Invalid or Limited")

            }
        } catch (e: Exception) {
            callbackListener.onFailure(e.message.toString())
        }
    }

    suspend fun getPalestineNews(
        callbackListener: CallbackListener<List<ArticlesItem>>,
        pageSize: Int = 5,
    ) {
        try {
            val result = newsApi.getNews(
                query = "palestine",
                sortBy = "popularity",
                pageSize = pageSize,
                apiKey = apiKey,
            )
            if (result.status == "ok") {
                callbackListener.onSuccess(result.articles ?: listOf())
            } else {
                // if status is 401 = Invalid API Key
                callbackListener.onFailure("Api Key Invalid or Limited")

            }
        } catch (e: Exception) {
            callbackListener.onFailure(e.message.toString())
        }
    }

    suspend fun getSportNews(
        callbackListener: CallbackListener<List<ArticlesItem>>,
        pageSize: Int = 5,
    ) {
        try {
            val result = newsApi.getHeadlines(
                country = "id",
                category = "sport",
                apiKey = apiKey,
                pageSize = pageSize,
            )
            if (result.status == "ok") {
                callbackListener.onSuccess(result.articles ?: listOf())
            } else {
                // if status is 401 = Invalid API Key
                callbackListener.onFailure("Api Key Invalid or Limited")

            }
        } catch (e: Exception) {
            callbackListener.onFailure(e.message.toString())
        }
    }

    suspend fun insertFavorite(
        news: NewsTable,
    ) = newsDao.insertFavorite(news)

    suspend fun isFavorite(
        id: Int,
    ) = newsDao.isFavorite(id)

    suspend fun getAllFavorite(
    ) = newsDao.getAllFavorite()

    suspend fun deleteAllFavorite() = newsDao.deleteAllFavorite()

    suspend fun deleteFavoriteById(
        id: Int,
    ) = newsDao.deleteFavoriteById(id)
}

