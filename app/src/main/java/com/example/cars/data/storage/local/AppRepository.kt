package com.example.cars.data.storage.local

import com.example.cars.data.storage.local.models.CarEntity
import com.example.cars.data.storage.local.models.SubscribeEntity

/**
 * Внешний класс для управления данными в базе данных.
 * @property CarsDao  реализует интерфейс CarsStorage
 * @property SubscribeDao  реализует интерфейс SubscriptionStorage
 */
class AppRepository(
    private val carsDao: CarsDao,
    private val subscribeDao: SubscribeDao
) {

    /**
     * Метод для получения всех CarEntity из базы данных
     * @return список сущностей
     */
    suspend fun getAllCars(): List<CarEntity> {
        return carsDao.getAllCars()
    }

    /**
     * Метод для получения CarEntity из базы данных
     * @param id  CarEntity для поиска.
     * @return CarEntity содержащий указанный ID.
     */
    suspend fun getCarById(id: Int): CarEntity {
        return carsDao.getCarById(id)
    }

    /**
     * метод для получения отсортированного списка всех сущностей по name
     * @return сортированный список CarEntity
     */
    suspend fun getCarsSortedByName(): List<CarEntity> {
        return carsDao.getCarsSortedByName()
    }

    /**
     * метод для получения отсортированного списка всех сущностей по year
     * @return сортированный список CarEntity
     */
    suspend fun getCarsSortedByYear(): List<CarEntity> {
        return carsDao.getCarsSortedByYear()
    }

    /**
     * метод для получения отсортированного списка всех сущностей по createdAt
     * @return сортированный список CarEntity
     */
    suspend fun getCarsSortedByCreatedAt(): List<CarEntity> {
        return carsDao.getCarsSortedByCreatedAt()
    }

    /**
     * метод для получения отсортированного списка всех сущностей по engineVolume
     * @return сортированный список CarEntity
     */
    suspend fun getCarsSortedByVolume(): List<CarEntity> {
        return carsDao.getCarsSortedByVolume()
    }

    /**
     * Метод для записи CarEntity в базу, перезаписывает запись с таким же primary key.
     * @param car  CarEntity сущность для записи.
     */
    suspend fun addNewCar(car: CarEntity) {
        return carsDao.addNewCar(car)
    }

    /**
     * Метод для удаления всех CarEntity из базы.
     */
    suspend fun clearCarsTable() {
        return carsDao.clearCarsTable()
    }

    /**
     * Метод для записи сущности в базу, перезаписывает сущность при совпадении id
     * @param SubscribeEntity для записи.
     */
    suspend fun addSubscription(subscribe: SubscribeEntity) {
        return subscribeDao.addSubscription(subscribe)
    }

    /**
     * Метод для поиска сущности по указанному id в базе данных
     * @param id  SubscribeEntity для поиска.
     * @return сущность SubscribeEntity с указанным ID.
     */
    suspend fun checkSubscription(id: Int): SubscribeEntity {
        return subscribeDao.checkSubscription(id)
    }

    /**
     * Метод для удалений всех записей в базе.
     */
    suspend fun clearSubscriptionTable() {
        return subscribeDao.clearSubscriptionTable()
    }

    /**
     * @return список всех SubscribeEntity в базе.
     */
    suspend fun getAllSubscriptions(): List<SubscribeEntity> {
        return subscribeDao.getAllSubscribe()
    }


}