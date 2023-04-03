package com.example.cars.domain.models

import android.graphics.Bitmap
import java.util.*

/**
 * Класс представляет модель данных машины.
 * @constructor данные экземпляра модели.
 */

data class CarModel(
    val id: Int? = null,
    val name: String? = null,
    val year: Int? = null,
    val engineVolume: Double? = null,
    val created: Long? = null,
    val createdAt: String? = null,
    val imageString: String? = null,
    val imageInt: Int? = null,
    val image: Bitmap? = null,
)
