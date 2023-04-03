package com.example.cars.domain.models

/**
 * Класс представляет модель данных подписки.
 * @constructor данные экземпляра модели.
 */
data class SubscribeModel(
    val id: Int? = null,
    val start: Long? = null,
    val end: Long? = null,
    val watchCarsCount: Int? = null,
    val addToFavoriteCount: Int? = null,
    val isUnlimitedSubscription: Boolean? = null
)
