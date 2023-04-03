package com.example.cars.domain.usecases.cars

import com.example.cars.domain.repository.CarsRepository
import com.example.cars.domain.utils.CarsUtils
import com.example.cars.presentation.models.CarView

/**
 * UseCase для добавления данных в  CarsRepository.
 *
 * @param carsRepository реализация интерфейса CarsRepository.
 */
class AddNewCarToDbUseCase(
    private val carsRepository: CarsRepository
) {

    /**
     * Метод вызова UseCase.
     * @param car модель CarView.
     * @return если успех то true, если ошибка то false.
     */
    suspend operator fun invoke(car: CarView): Boolean {
        val res = CarsUtils.mapToModel(car)
        return carsRepository.addNewCar(res)
    }

}