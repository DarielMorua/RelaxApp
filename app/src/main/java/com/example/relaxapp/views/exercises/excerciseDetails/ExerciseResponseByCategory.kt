package com.example.relaxapp.views.exercises.excerciseDetails

import com.google.gson.annotations.SerializedName

class ExerciseResponseByCategory (
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("shortDescription") val shortDescription: String,
    @SerializedName("longDescription") val longDescription: String,
    @SerializedName("urlVideo") val urlVideo: String
)