package com.example.testapplication.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    companion object {
        private const val URL = "https://api.unsplash.com/"
        private var instance: Retrofit? = null

        fun init() {
            if (instance == null) {
                val client = OkHttpClient.Builder().addInterceptor(GalleryInterceptor()).build()
                instance =
                    Retrofit.Builder().baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client).build()
            }
        }

        fun get(): Retrofit {
            return instance ?: throw IllegalStateException("Loh")
        }
    }
}