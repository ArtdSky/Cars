package com.example.cars.domain.utils

import com.example.cars.domain.models.CarModel
import com.example.cars.presentation.models.CarView

/**
 * Вспомогательный класс для преобразования данных
 * между CarModel и CarView
 */
class CarsUtils {

    companion object {
        /**
         * метод для преобразования данных
         * из CarModel в CarView
         */
        fun mapToView(car: CarModel): CarView {
            return CarView(
                id = car.id,
                name = car.name,
                image = car.image,
                imageString = car.imageString,
                imageInt = car.imageInt,
                year = car.year,
                engineVolume = car.engineVolume,
                created = car.created,
                createdAt = car.createdAt,
            )
        }
        /**
         * метод для преобразования данных
         * из CarView в CarModel
         */
        fun mapToModel(car: CarView): CarModel {
            return CarModel(
                id = car.id,
                name = car.name,
                image = car.image,
                imageString = car.imageString,
                imageInt = car.imageInt,
                year = car.year,
                engineVolume = car.engineVolume,
                created = car.created,
                createdAt = car.createdAt,
            )
        }


    }

}