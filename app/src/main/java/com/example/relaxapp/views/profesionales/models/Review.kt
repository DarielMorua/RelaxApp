package com.example.relaxapp.views.profesionales.models

import com.example.relaxapp.views.doctordetails.models.ReviewUser

data class Review(val _id: String,
                  val comment: String,
                  val score: Float,
                  val professionalId: String,
                  val userId: ReviewUser,
                  val reviewDate: String)
