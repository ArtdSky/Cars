package com.example.cars.data.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.cars.data.storage.local.models.CarEntity
import com.example.cars.domain.models.CarModel
import java.io.ByteArrayOutputStream


/**
 * Вспомогательный класс для преобразования данных
 * между CarModel и CarEntity
 */
class CarsUtils {
    companion object{

        /**
         * метод для преобразования данных
         * из CarModel в CarEntity
         */
        fun mapCarModelToEntity(car: CarModel): CarEntity {
            return CarEntity(
                id = car.id,
                name = car.name,
                image = getBytesFromBitmap(car.image),
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
         * из CarEntity в CarModel
         */
        fun mapCarEntityToCarModel(car: CarEntity): CarModel {
            val bitmap: Bitmap? = if (car.image != null) {
                BitmapFactory.decodeByteArray(car.image, 0, car.image.size)
            } else {
                null
            }
            return CarModel(
                id = car.id,
                name = car.name,
                image = bitmap,
                imageString = car.imageString,
                imageInt = car.imageInt,
                year = car.year,
                engineVolume = car.engineVolume,
                created = car.created,
                createdAt = car.createdAt,
            )
        }

        private fun getBytesFromBitmap(bitmap: Bitmap?): ByteArray? {
            if (bitmap == null) {
                return null
            }
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }


    }
}