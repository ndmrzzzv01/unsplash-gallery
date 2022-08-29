package com.example.testapplication.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class GalleryInterceptor : Interceptor {

    companion object {
        const val ID = "ab3411e4ac868c2646c0ed488dfd919ef612b04c264f3374c97fff98ed253dc9"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newUrl: HttpUrl = request.url().newBuilder()
            .addQueryParameter("client_id", ID)
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}