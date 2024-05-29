package com.example.artspace.model

import androidx.annotation.DrawableRes

data class Photo(
    val title: String,
    val author: String,
    val year: Int,
    @DrawableRes val imageResourceId: Int
)
