package com.example.cars.data.storage.local

import com.example.cars.data.storage.local.models.SubscribeEntity

/**
 * интерфейс для управления данными в базе данных
 */
interface SubscriptionApi {

    /**
     * Метод для записи сущности в таблицу, перезаписывает сущность при совпадении id
     * @param SubscribeEntity для записи.
     * @return true в случае успеха, false если ошибка
     */
    suspend fun buySubscription(subscription: SubscribeEntity): Boolean

    /**
     * Метод для поиска сущности по указанному id в "subscribe_table"
     * @param id  SubscribeEntity для поиска.
     * @return сущность SubscribeEntity с указанным ID.
     */
    suspend fun checkSubscription(id: Int): SubscribeEntity

    /**
     * Метод для очистки записей в "subscribe_table".
     */
    suspend fun clearSubscriptionTable()

    /**
     * @return список всех SubscribeEntity в "subscribe_table".
     */
    suspend fun getAllSubscriptions(): List<SubscribeEntity>

}