package com.example.cars.presentation.utils

import android.content.Context
import android.util.Log
import com.example.cars.presentation.models.CarView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileNotFoundException
import java.io.InputStream

/**
 * Класс для загрузки статических данных в приложение.
 * Используется для получения статических данных об автомобилях из файла JSON.
 */
class GetStaticCarsData {

    companion object {

        /**
         * метод пытается открыть файл с именем «cars.json»
         * из папки assets предоставленного контекста. Затем он считывает содержимое файла в
         * строковую переменная с использованием bufferedReader()
         *
         * @param [context] текущий контекст приложения
         * @return[items] статические данные в виде списка List<Cars>
         */
        fun getData(context: Context): List<CarView> {
            return try {
                val inputStream: InputStream = context.assets.open("cars.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val gson = Gson()

                val cars: List<CarView> = gson.fromJson(jsonString, object : TypeToken<List<CarView>>() {}.type)
                cars
            } catch (e: FileNotFoundException) {
                Log.d("GetStaticCarsData", "File ${e.message} not found!")
                emptyList<CarView>()
            }
        }



    }
}