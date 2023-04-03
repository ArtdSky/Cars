package com.example.cars.presentation.models

/**
 * Класс модели отображения подписки
 */
data class SubscriptionView(
    val id : Int? =null,
    val start : Long? = null,
    val end : Long? = null,
    val watchCarsCount : Int? = null,
    val addToFavoriteCount : Int? = null,
    val isUnlimitedSubscription: Boolean? = null
)
