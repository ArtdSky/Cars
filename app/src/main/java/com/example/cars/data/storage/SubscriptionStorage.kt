package com.example.cars.data.storage

import android.util.Log
import com.example.cars.data.storage.local.AppRepository
import com.example.cars.data.storage.local.CarsApi
import com.example.cars.data.storage.local.SubscriptionApi
import com.example.cars.data.storage.local.models.SubscribeEntity
import java.util.*

class SubscriptionStorage(
    private val appRepository: AppRepository
) : SubscriptionApi {

    /**
     * Метод для записи SubscribeEntity в репозиторий
     * @param SubscribeEntity для записи.
     *  @return true в случае успеха, false если ошибка
     */
    override suspend fun buySubscription(subscription: SubscribeEntity): Boolean {
        return try {
            appRepository.addSubscription(subscription)
            true
        } catch (e : Exception){
            Log.d("ApiStorage-buySubscription", "Exception: ${e.message}")
            false
        }
    }

    /**
     * Метод для поиска сущности по указанному id в репозитории
     * @param id  SubscribeEntity для поиска.
     * @return сущность SubscribeEntity с указанным ID.
     */
    override suspend fun checkSubscription(id: Int): SubscribeEntity {
        return appRepository.checkSubscription(id)
    }

    /**
     * Метод для очистки записей SubscribeEntity в репозитории.
     */
    override suspend fun clearSubscriptionTable() {
        return  appRepository.clearSubscriptionTable()
    }

    /**
     * @return список всех SubscribeEntity в репоизитории.
     */
    override suspend fun getAllSubscriptions(): List<SubscribeEntity> {
       return appRepository.getAllSubscriptions()
    }


}