package com.example.cars.domain.repository

import com.example.cars.domain.models.CarModel

/**
 * Внешний интерфейс для взаимодействия с cars usecases .
 */
interface CarsRepository {

    /**
     * Метод для получения списка машин.
     * @return список CarModel .
     */
    suspend fun getAllCars() : List<CarModel>

    /**
     * Метод для получения машины по id.
     * @param id CarModel.
     * @return CarModel с указанным id.
     */
    suspend fun getCarById(id : Int) : CarModel

    /**
     * Метод получения отсортированного списка машин.
     *
     * @param sortBy тип сортировки, принимает значения ["name", "year", "engine", "createdAt" ]
     * @return отсортированный по sortBy список.
     */
    suspend fun sortCarBy(sortBy: String) : List<CarModel>

    /**
     * Метод для добавления указанной в параметрах машины в бд
     *
     * @param car CarModel для добавления.
     * @return true в случае успеха, `false` если не удалось добавить.
     */
    suspend fun addNewCar(car : CarModel) : Boolean
}