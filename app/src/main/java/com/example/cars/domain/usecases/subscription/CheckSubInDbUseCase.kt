package com.example.cars.domain.usecases.subscription

import com.example.cars.domain.repository.SubscriptionRepository
import com.example.cars.domain.utils.SubscribeUtils
import com.example.cars.presentation.models.SubscriptionView


/**
 * Use case для получения списка CarView по ID из репозитория.
 *
 * @param subscriptionRepository экземпляр SubscriptionRepository.
 */
class CheckSubInDbUseCase(
    private val subscriptionRepository: SubscriptionRepository
) {

    /**
     * Метод для вызова UseCase.
     *
     * @param id ID SubscriptionView для поиска.
     * @return SubscriptionView  с указанным id.
     */
    suspend operator fun invoke(id: Int): SubscriptionView {
        val res = subscriptionRepository.checkSubscription(id)

        return SubscribeUtils.mapToView(res)
    }

}