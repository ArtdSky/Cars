package com.example.cars.data.repository

import android.util.Log
import com.example.cars.data.storage.CarsStorage
import com.example.cars.data.storage.SubscriptionStorage
import com.example.cars.data.utils.CarsUtils
import com.example.cars.data.utils.SubscribeUtils
import com.example.cars.domain.models.CarModel
import com.example.cars.domain.models.SubscribeModel
import com.example.cars.domain.repository.CarsRepository
import com.example.cars.domain.repository.ResetRepository
import com.example.cars.domain.repository.SubscriptionRepository
import java.util.*

/**
 * Класс реализует интерфейсы CarsRepository, SubscriptionRepository, ResetRepository.
 * @property CarsStorage  реализует интерфейс CarsStorage
 * @property SubscriptionStorage  реализует интерфейс SubscriptionStorage
 */
class RepositoryImpl(
    private val carsStorage: CarsStorage,
    private val subscriptionStorage: SubscriptionStorage
) : CarsRepository, SubscriptionRepository, ResetRepository {

    /**
     * Метод для получения списка машин из реализации CarsStorage
     * @return список CarModel
     */
    override suspend fun getAllCars(): List<CarModel> {
        val res = carsStorage.getAllCars()
        return res.map {
            CarsUtils.mapCarEntityToCarModel(it)
        }
    }

    /**
     * Метод для получения CarModel по Id из реализации CarsStorage
     * @param id для CarModel
     * @return CarModel с указанным id
     */
    override suspend fun getCarById(id: Int): CarModel {
        val res = carsStorage.getCarById(id)
        return CarsUtils.mapCarEntityToCarModel(res)
    }

    /**
     * Метод для получения сортированного списка по указанному параметру из реализации CarsStorage
     * @param sortBy для определения сортировки
     * @return отсортированный список CarModel
     */
    override suspend fun sortCarBy(sortBy: String): List<CarModel> {
        when (sortBy) {
            "name" -> {
                val result = carsStorage.getCarsSortedByName()
                return result.map {
                    CarsUtils.mapCarEntityToCarModel(it)
                }
            }
            "year" -> {
                    val result = carsStorage.getCarsSortedByYear()
                    return result.map {
                        CarsUtils.mapCarEntityToCarModel(it)
                    }
            }
            "engine" -> {
                    val result = carsStorage.getCarsSortedByVolume()
                    return result.map {
                        CarsUtils.mapCarEntityToCarModel(it)
                    }
            }
            "created" -> {
                    val result = carsStorage.getCarsSortedByCreatedAt()
                    return result.map {
                        CarsUtils.mapCarEntityToCarModel(it)
                    }
            }
            else -> {
                val errorMessage = "Unknown value of 'sortBy'"
                Log.e("RepositoryImpl-sortCarBy", errorMessage)
                throw IllegalArgumentException(errorMessage)
            }
        }
    }

    /**
     * Метод для добавления CarModel  из реализации CarsStorage
     * @param car CarModel для добавления в реализацию
     * @return true в случае успеха, false если ошибка
     */
    override suspend fun addNewCar(car: CarModel): Boolean {
        val res = CarsUtils.mapCarModelToEntity(car)
        return carsStorage.addNewCar(res)
    }

    /**
     * Метод для добавления SubscribeModel в реализацию  SubscriptionStorage
     * @param subscription SubscribeModel для добавления в реализацию
     * @return  true в случае успеха, false если ошибка
     */
    override suspend fun addNewSubscription(subscription: SubscribeModel): Boolean {
        val res = SubscribeUtils.mapSubscribeModelToSubscribeEntity(subscription)
        return subscriptionStorage.buySubscription(res)
    }

    /**
     * Метод для поиска SubscribeModel по указанному id из реализации SubscriptionStorage.
     * @param id SubscribeModel
     * @return SubscribeModel содержащий указанный id
     */
    override suspend fun checkSubscription(id: Int): SubscribeModel {
        val res = subscriptionStorage.checkSubscription(id)
        return SubscribeUtils.mapSubscribeEntityToSubscribeModel(res)
    }

    /**
     * Метод для поиска всех SubscribeModel из реализации SubscriptionStorage.
     * @param id подписки
     * @return SubscribeModel содержащий указанный id
     */
    override suspend fun getAllSubscriptions(): List<SubscribeModel> {
        val res = subscriptionStorage.getAllSubscriptions()
        return res.map {
            SubscribeUtils.mapSubscribeEntityToSubscribeModel(it)
        }
    }

    /**
     * Метод дляудаления всех SubscribeModel и CarModel.
     * @return true в случае успеха, false если ошибка
     */
    override suspend fun resetAppData(): Boolean {
        return try {
            carsStorage.clearCarsTable()
            subscriptionStorage.clearSubscriptionTable()
            true
        } catch (e: Exception) {
            Log.d("RepositoryImpl-resetAppData", "Exception: ${e.message}")
            false
        }
    }

}