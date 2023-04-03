package com.example.cars.domain.repository

/**
 * Внешний интерфейс для взаимодействия с reset usecases .
 */
interface ResetRepository {

    /**
     * Метод дляудаления всех SubscribeModel и CarModel.
     * @return true в случае успеха, false если ошибка
     */
    suspend fun resetAppData() : Boolean

}