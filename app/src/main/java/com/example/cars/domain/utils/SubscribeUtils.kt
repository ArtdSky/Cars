package com.example.cars.domain.utils

import com.example.cars.domain.models.SubscribeModel
import com.example.cars.presentation.models.SubscriptionView

/**
 * Вспомогательный класс для преобразования данных
 * между CarModel и CarView
 */
class SubscribeUtils {
    companion object {
        /**
         * метод для преобразования данных
         * из SubscribeModel в SubscriptionView
         */
        fun mapToView(subscription: SubscribeModel): SubscriptionView {
            return SubscriptionView(
                id = subscription.id,
                start = subscription.start,
                end = subscription.end,
                watchCarsCount = subscription.watchCarsCount,
                addToFavoriteCount = subscription.addToFavoriteCount,
                isUnlimitedSubscription = subscription.isUnlimitedSubscription
            )
        }

        /**
         * метод для преобразования данных
         * из SubscriptionView в SubscribeModel
         */
        fun mapToModel(subscription: SubscriptionView): SubscribeModel {
            return SubscribeModel(
                id = subscription.id,
                start = subscription.start,
                end = subscription.end,
                watchCarsCount = subscription.watchCarsCount,
                addToFavoriteCount = subscription.addToFavoriteCount,
                isUnlimitedSubscription = subscription.isUnlimitedSubscription
            )
        }


    }
}