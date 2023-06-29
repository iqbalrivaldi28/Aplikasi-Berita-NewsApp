package id.tomuchcoffee.newsapp.source.network

import id.tomuchcoffee.newsapp.source.news.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("top-headlines")
    suspend fun fetchNews(
        @Query("apiKey") apikey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") query: String,
        @Query("page") page: Int
    ): NewsModel
}