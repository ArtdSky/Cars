package com.example.cars.domain.usecases.cars

import com.example.cars.domain.models.CarModel
import com.example.cars.domain.repository.CarsRepository

/**
 * Use case для получения списка CarView по ID из репозитория.
 *
 * @param carsRepository хранилище машин приложения.
 */
class GetCarByIdInDbUseCase(
    private val carsRepository: CarsRepository
) {

    /**
     * Метод вызова UseCase.
     * @return  CarModel.
     */
    suspend operator fun invoke(id: Int): CarModel {
        return carsRepository.getCarById(id)
    }

}