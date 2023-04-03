package com.example.cars.domain.usecases.cars

import com.example.cars.domain.repository.CarsRepository
import com.example.cars.domain.utils.CarsUtils
import com.example.cars.presentation.models.CarView

/**
 * Use case  для получения списка CarView из репозитория.
 *
 * @param carsRepository хранилище машин приложения.
 */
class GetAllCarsInDbUseCase(
    private val carsRepository: CarsRepository
) {

    /**
     * Метод вызова UseCase.
     * @return список CarView.
     */
    suspend operator fun invoke(): List<CarView> {
        val res = carsRepository.getAllCars()
        return res.map {
            CarsUtils.mapToView(it)
        }
    }
}