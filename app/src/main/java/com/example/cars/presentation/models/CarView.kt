package com.example.cars.presentation.models

import android.graphics.Bitmap

/**
 * Класс модели отображения машин
 */
data class CarView(
    val id: Int? = null,
    val name: String? = null,
    val image: Bitmap? = null,
    val imageString: String? = null,
    val imageInt: Int? = null,
    val year: Int? = null,
    val engineVolume: Double? = null,
    val createdAt: String? = null,
    val created: Long? = null,
)
