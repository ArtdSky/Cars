package com.example.cars.data.storage.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cars.data.storage.local.models.CarEntity

/**
 * интерфейс для доступа к данными в таблице cars_table.
 */
@Dao
interface CarsDao {

    /**
     * Метод для получения всех сущностей из "cars_table".
     * @return список сущностей .
     */
    @Query("SELECT * FROM cars_table")
    suspend fun getAllCars(): List<CarEntity>

    /**
     * Метод для поиска сущности по указанному id в "cars_table"
     * @param id  CarEntity для поиска.
     * @return сущность CarEntity с указанным ID.
     */
    @Query("SELECT * FROM cars_table WHERE id = :id")
    suspend fun getCarById(id: Int): CarEntity

    /**
     * метод для получения отсортированного списка всех сущностей по name из "cars_table"
     * @return сортированный список CarEntity
     */
    @Query("SELECT * FROM cars_table ORDER BY name ASC")
    suspend fun getCarsSortedByName(): List<CarEntity>

    /**
     * метод для получения отсортированного списка всех сущностей по year из "cars_table"
     * @return сортированный список CarEntity
     */
    @Query("SELECT * FROM cars_table ORDER BY year ASC")
    suspend fun getCarsSortedByYear(): List<CarEntity>

    /**
     * метод для получения отсортированного списка всех сущностей по createdAt из "cars_table"
     * @return сортированный список CarEntity
     */
    @Query("SELECT * FROM cars_table ORDER BY createdAt ASC")
    suspend fun getCarsSortedByCreatedAt(): List<CarEntity>

    /**
     * метод для получения отсортированного списка всех сущностей по engineVolume из "cars_table"
     * @return сортированный список CarEntity
     */
    @Query("SELECT * FROM cars_table ORDER BY engineVolume ASC")
    suspend fun getCarsSortedByVolume(): List<CarEntity>

    /**
     * Метод для записи CarEntity в "cars_table", перезаписывает запись с таким же primary key.
     * @param car  CarEntity сущность для записи.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewCar(car: CarEntity)

    /**
     * Метод для удаления всех записей из "cars_table".
     */
    @Query("DELETE FROM cars_table")
    suspend fun clearCarsTable()

}