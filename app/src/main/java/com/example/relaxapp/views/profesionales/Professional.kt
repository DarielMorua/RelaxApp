package com.example.relaxapp.views.profesionales

data class Professional(
    val _id: String,
    val name: String,
    val photo: String,
    val score: Double,
    val reviews: List<String>,
    val creationDate: String,
    val description: String,
    val phone: String,
    val ubicacion: Ubicacion,
    val isActive: Boolean)
