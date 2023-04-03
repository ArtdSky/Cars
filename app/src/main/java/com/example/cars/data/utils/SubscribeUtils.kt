package com.example.cars.data.utils

import com.example.cars.data.storage.local.models.SubscribeEntity
import com.example.cars.domain.models.SubscribeModel


/**
 * Вспомогательный класс для преобразования данных
 * между SubscribeEntity и SubscribeModel
 */
class SubscribeUtils {

    companion object {

        /**
         * метод для преобразования данных
         * из SubscribeModel в SubscribeEntity
         */
        fun mapSubscribeModelToSubscribeEntity(subscription: SubscribeModel): SubscribeEntity {
            return SubscribeEntity(
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
         * из SubscribeEntity в SubscribeModel
         */
        fun mapSubscribeEntityToSubscribeModel(subscription: SubscribeEntity): SubscribeModel {
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