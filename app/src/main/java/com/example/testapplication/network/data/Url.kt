package com.example.testapplication.network.data

import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("small") val smallPhoto: String? = null,
    @SerializedName("full") val fullPhoto: String? = null
)
