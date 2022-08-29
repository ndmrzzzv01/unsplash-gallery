package com.example.testapplication.network.data

import com.google.gson.annotations.SerializedName

data class Gallery(
    @SerializedName("id") val id: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("urls") val url: Url? = null,
    @SerializedName("user") val user: User? = null
)
