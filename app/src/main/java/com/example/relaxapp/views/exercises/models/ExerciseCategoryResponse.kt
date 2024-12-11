package com.example.relaxapp.views.exercises.models

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("categories") val categories: List<Category>
)

data class Category(
    @SerializedName("name") val name: String,
    @SerializedName("_id") val id: String,
    @SerializedName("exercises") val exercises: List<ExerciseResponseByCategory>
)