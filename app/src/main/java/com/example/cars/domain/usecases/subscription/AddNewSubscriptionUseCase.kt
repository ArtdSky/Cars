package com.example.cars.domain.usecases.subscription

import com.example.cars.domain.repository.SubscriptionRepository
import com.example.cars.domain.utils.SubscribeUtils
import com.example.cars.presentation.models.SubscriptionView

/**
 * UseCase для добавления данных в  SubscriptionRepository.
 *
 * @param subscriptionRepository реализация интерфейса SubscriptionRepository.
 */
class AddNewSubscriptionUseCase(
    private val subscriptionRepository: SubscriptionRepository
) {

    /**
     * Метод вызова UseCase.
     * @param subscription модель SubscriptionView.
     * @return если успех то true, если ошибка то false.
     */
    suspend operator fun invoke(subscription: SubscriptionView): Boolean {
        val res = SubscribeUtils.mapToModel(subscription)
        return subscriptionRepository.addNewSubscription(res)
    }

}