package com.example.testapplication.network.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") val name: String? = null
)