package com.example.relaxapp.views.mainmenu

import com.google.gson.annotations.SerializedName

data class ExcerciseResponse(
    @SerializedName("_id") val id: String,
    val title: String,
    val category: String,
    val image: String,
    val shortDescription: String,
    val longDescription: String,
    val urlVideo: String
)