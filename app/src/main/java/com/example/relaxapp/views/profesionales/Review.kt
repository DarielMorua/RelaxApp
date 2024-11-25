package com.example.relaxapp.views.profesionales

import com.example.relaxapp.views.doctordetails.ReviewUser

data class Review(val _id: String,
                  val comment: String,
                  val score: Float, // Cambia a Float para manejar valores decimales
                  val professionalId: String,
                  val userId: ReviewUser,
                  val reviewDate: String)
