package com.example.testapplication

import android.app.Application
import com.example.testapplication.network.RetrofitInitializer

class GalleryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitInitializer.init()
    }
}