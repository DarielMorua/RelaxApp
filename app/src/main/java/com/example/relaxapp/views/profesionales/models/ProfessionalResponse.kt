package com.example.relaxapp.views.profesionales.models

data class ProfessionalResponse(val name: String,
                                val photo: String,
                                val score: Double,
                                val description: String,
                                val phone: String,
                                val ubicacion: Ubicacion,
                                val isActive: Boolean,
                                val userId: String)
