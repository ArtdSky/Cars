package com.example.cars.data.storage.local

import com.example.cars.data.storage.local.models.CarEntity

/**
 * интерфейс для управления данными в базе данных
 */
interface CarsApi {

    /**
     * Метод для получения всех сущностей из "cars_table".
     * @return список сущностей .
     */
    suspend fun getAllCars(): List<CarEntity>

    /**
     * Метод для поиска сущности по указанному id в "cars_table"
     * @param id CarEntity для поиска.
     * @return сущность CarEntity с указанным ID.
     */
    suspend fun getCarById(id: Int): CarEntity

    /**
     * метод для получения отсортированного списка всех сущностей по name из "cars_table"
     * @return сортированный список CarEntity
     */
    suspend fun getCarsSortedByName(): List<CarEntity>

    /**
     * метод для получения отсортированного списка всех сущностей по year из "cars_table"
     * @return сортированный список CarEntity
     */
    suspend fun getCarsSortedByYear(): List<CarEntity>

    /**
     * метод для получения отсортированного списка всех сущностей по createdAt из "cars_table"
     * @return сортированный список CarEntity
     */
    suspend fun getCarsSortedByCreatedAt(): List<CarEntity>

    /**
     * метод для получения отсортированного списка всех сущностей по engineVolume из "cars_table"
     * @return сортированный список CarEntity
     */
    suspend fun getCarsSortedByVolume(): List<CarEntity>

    /**
     * Метод для записи CarEntity в "cars_table", перезаписывает запись с таким же primary key.
     * @param car  CarEntity сущность для записи.
     * @return true в случае успеха, false если ошибка
     */
    suspend fun addNewCar(car: CarEntity): Boolean

    /**
     * Метод для удаления всех записей из "cars_table".
     */
    suspend fun clearCarsTable()


}