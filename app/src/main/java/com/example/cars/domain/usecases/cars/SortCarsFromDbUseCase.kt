package com.example.cars.domain.usecases.cars

import com.example.cars.domain.repository.CarsRepository
import com.example.cars.domain.utils.CarsUtils
import com.example.cars.presentation.models.CarView

/**
 * Use case для получения отсортированного списка CarView по sortBy из храннилища машин.
 *
 * @param carsRepository хранилище машин .
 */
class SortCarsFromDbUseCase(
    private val carsRepository: CarsRepository
) {

    /**
     * Метод для вызова Use Case.
     *
     * @param sortBy способ сортировки, принимает значения [name, year, createdAt, engine].
     * @return сортированный список.
     */
    suspend operator fun invoke(sortBy: String): List<CarView> {
        val res = carsRepository.sortCarBy(sortBy)
        return res.map {
            CarsUtils.mapToView(it)
        }
    }

}