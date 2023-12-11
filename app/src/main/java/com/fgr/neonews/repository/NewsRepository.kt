package com.fgr.neonews.repository

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.fgr.neonews.data.listener.ApiListener
import com.fgr.neonews.data.remote.NewsApi
import com.fgr.neonews.data.response.ArticlesItem
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class NewsRepository @Inject constructor(
    private val appContext: Application,
    private val newsApi: NewsApi,
    private val apiKey: String,
) {
    suspend fun getLocalNews(
        apiListener: ApiListener<List<ArticlesItem>>,
        pageSize: Int = 5,
    ) {
        try {
            val result = newsApi.getHeadlines(
                country = "id",
                apiKey = apiKey,
                pageSize = pageSize
            )
            if (result.status == "ok") {
                apiListener.onSuccess(result.articles ?: listOf())
            } else {
                // if status is 401 = Invalid API Key
                apiListener.onFailure("Api Key Invalid or Limited")

            }
        } catch (e: Exception) {
            apiListener.onFailure(e.message.toString())
            Log.e("LocalNews", e.message.toString())
        }
    }

    suspend fun getAbroadNews(
        apiListener: ApiListener<List<ArticlesItem>>,
        pageSize: Int = 5,
    ) {
        try {
            val result = newsApi.getHeadlines(
                country = "us",
                apiKey = apiKey,
                pageSize = pageSize
            )
            if (result.status == "ok") {
                apiListener.onSuccess(result.articles ?: listOf())
            } else {
                // if status is 401 = Invalid API Key
                apiListener.onFailure("Api Key Invalid or Limited")

            }
        } catch (e: Exception) {
            apiListener.onFailure(e.message.toString())
        }
    }

    suspend fun getPalestineNews(
        apiListener: ApiListener<List<ArticlesItem>>,
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
                apiListener.onSuccess(result.articles ?: listOf())
            } else {
                // if status is 401 = Invalid API Key
                apiListener.onFailure("Api Key Invalid or Limited")

            }
        } catch (e: Exception) {
            apiListener.onFailure(e.message.toString())
        }
    }

    suspend fun getSportNews(
        apiListener: ApiListener<List<ArticlesItem>>,
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
                apiListener.onSuccess(result.articles ?: listOf())
            } else {
                // if status is 401 = Invalid API Key
                apiListener.onFailure("Api Key Invalid or Limited")

            }
        } catch (e: Exception) {
            apiListener.onFailure(e.message.toString())
        }
    }
}

