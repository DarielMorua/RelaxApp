package com.example.relaxapp.views.mainmenu

data class ExcerciseResponse(
    val id: String,
    val title: String,
    val category: String,
    val image: String,
    val shortDescription: String,
    val longDescription: String,
    val urlVideo: String
)