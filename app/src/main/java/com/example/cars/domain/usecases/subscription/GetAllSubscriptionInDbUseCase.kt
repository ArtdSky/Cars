package com.example.cars.domain.usecases.subscription

import com.example.cars.domain.repository.SubscriptionRepository
import com.example.cars.domain.utils.SubscribeUtils
import com.example.cars.presentation.models.SubscriptionView

/**
 * Use case  для получения списка SubscriptionView из репозитория.
 *
 * @param subscriptionRepository экземпляр SubscriptionRepository.
 */
class GetAllSubscriptionInDbUseCase(
    private val subscriptionRepository: SubscriptionRepository
) {
    /**
     * Метод вызова Use Case
     * @return список SubscriptionView
     */
    suspend operator fun invoke(): List<SubscriptionView> {
        val res = subscriptionRepository.getAllSubscriptions()
        return res.map {
            SubscribeUtils.mapToView(it)
        }
    }
}