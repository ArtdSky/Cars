package com.example.cars.presentation.state

import com.example.cars.presentation.models.CarView
import com.example.cars.presentation.models.SubscriptionView

/**
 * Класс представляющий модель внутреннего состояния приложения
 */
data class ViewState(
    val subscriptionInfo : SubscriptionView? = null,
    val isNeedData : Boolean? = null,
    val currentCar : CarView? = null,
    val cars : List<CarView>? = null,
    val subscriptions : List<SubscriptionView>? = null,
    val sortBy : String? = null
)