package com.example.cars.domain.repository

import com.example.cars.domain.models.SubscribeModel

/**
 * Внешний интерфейс для взаимодействия с subscription usecases .
 */
interface SubscriptionRepository {

    /**
     * Метод для добавления SubscribeModel в бд
     * @param subscription SubscribeModel для добавления в реализацию
     * @return  true в случае успеха, false если ошибка
     */
    suspend fun addNewSubscription(subscription: SubscribeModel) : Boolean

    /**
     * Метод для поиска SubscribeModel по указанному id из реализации SubscriptionStorage.
     * @param id SubscribeModel
     * @return SubscribeModel содержащий указанный id
     */
    suspend fun checkSubscription(id: Int) : SubscribeModel

    /**
     * Метод для поиска всех SubscribeModel из реализации SubscriptionStorage.
     * @param id подписки
     * @return SubscribeModel содержащий указанный id
     */
    suspend fun getAllSubscriptions() : List<SubscribeModel>

}