package com.fgr.neonews.data.retrofit

import com.fgr.neonews.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getHotNews(
        @Query("country") country: String = "id",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}