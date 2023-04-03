package com.example.cars.data.storage

import android.util.Log
import com.example.cars.data.storage.local.AppRepository
import com.example.cars.data.storage.local.CarsApi
import com.example.cars.data.storage.local.SubscriptionApi
import com.example.cars.data.storage.local.models.CarEntity
import com.example.cars.data.storage.local.models.SubscribeEntity
import java.util.Date

/**
 * Класс реализует интерфейс CarsApi
 * @property AppRepository класс управления данными в бд
 */
class CarsStorage(
    private val appRepository: AppRepository
) : CarsApi{

    /**
     * Метод для получения списка всех CarEntity из репозитория
     * @return список CarEntity
     */
    override suspend fun getAllCars() : List<CarEntity>{
        return appRepository.getAllCars()
    }

    /**
     * Метод для получения CarEntity по id из репозитория
     * @return CarEntity с указанным id
     */
    override suspend fun getCarById(id: Int): CarEntity {
       return appRepository.getCarById(id)
    }

    /**
     * метод для получения отсортированного списка всех сущностей по name
     * @return сортированный список CarEntity
     */
    override suspend fun getCarsSortedByName(): List<CarEntity> {
        return appRepository.getCarsSortedByName()
    }

    /**
     * метод для получения отсортированного списка всех сущностей по Year
     * @return сортированный список CarEntity
     */
    override suspend fun getCarsSortedByYear(): List<CarEntity> {
        return appRepository.getCarsSortedByYear()
    }

    /**
     * метод для получения отсортированного списка всех сущностей по CreatedAt
     * @return сортированный список CarEntity
     */
    override suspend fun getCarsSortedByCreatedAt(): List<CarEntity> {
        return appRepository.getCarsSortedByCreatedAt()
    }

    /**
     * метод для получения отсортированного списка всех сущностей по Volume
     * @return сортированный список CarEntity
     */
    override suspend fun getCarsSortedByVolume(): List<CarEntity> {
        return appRepository.getCarsSortedByVolume()
    }

    /**
     * Метод для записи CarEntity в репозиторий
     * @param car  CarEntity сущность для записи.
     * @return true в случае успеха, false если ошибка
     */
    override suspend fun addNewCar(car: CarEntity): Boolean {
        return try {
            appRepository.addNewCar(car)
            true
        } catch (e : Exception){
            Log.d("ApiStorage-addNewCar", "Exception: ${e.message}")
            false
        }
    }

    /**
     * Метод для удаления всех записей из "cars_table".
     */
    override suspend fun clearCarsTable() {
        return appRepository.clearCarsTable()
    }

}