package com.fgr.neonews.data.remote

import com.fgr.neonews.data.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "",
        @Query ("category") category: String = "",
        @Query("apiKey") apiKey: String,
        @Query("q") query: String = "",
        @Query("pageSize") pageSize: Int,
    ): NewsResponse

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "",
        @Query("from") fromDate: String = "",
        @Query("to") toDate: String = "",
        @Query("sortBy") sortBy: String = "",
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String,
    ): NewsResponse
}