package com.example.testapplication.network.api

import com.example.testapplication.network.data.Gallery
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryApi {

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int
    ): List<Gallery>

}