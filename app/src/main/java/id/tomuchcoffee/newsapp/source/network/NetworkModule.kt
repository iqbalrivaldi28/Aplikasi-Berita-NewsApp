package id.tomuchcoffee.newsapp.source.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val base_url = "https://newsapi.org/v2/"
val networkModule = module {
    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideNewsApi(get()) }
}

fun provideOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(base_url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideNewsApi(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)