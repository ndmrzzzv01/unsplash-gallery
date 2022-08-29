package com.example.testapplication.network

import com.example.testapplication.BuildConfig
import com.example.testapplication.network.api.GalleryApi
import com.example.testapplication.network.data.Gallery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GalleryRepository {

    private val retrofit = RetrofitInitializer.get()

    suspend fun getPhotos(page: Int): List<Gallery> = withContext(Dispatchers.IO) {
        if (page >= 2 && BuildConfig.DEBUG) {
            delay(2000L)
        }
        retrofit.create(GalleryApi::class.java).getPhotos(page)
    }

}