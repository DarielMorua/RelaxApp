package com.example.relaxapp.views.profesionales.conexionChat

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id") val id: String,
    val name: String
)