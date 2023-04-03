package com.example.cars.data.storage.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cars.data.storage.local.models.SubscribeEntity

/**
 * интерфейс для доступа и управлениями данными в таблице subscribe_table.
 */
@Dao
interface SubscribeDao {

    /**
     * Метод для записи сущности в таблицу, перезаписывает сущность при совпадении id
     * @param SubscribeEntity для записи.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubscription(subscribe: SubscribeEntity)

    /**
     * Метод для поиска сущности по указанному id в "subscribe_table"
     * @param id  SubscribeEntity для поиска.
     * @return сущность SubscribeEntity с указанным ID.
     */
    @Query("SELECT * FROM subscribe_table WHERE id = :id")
    suspend fun checkSubscription(id: Int): SubscribeEntity

    /**
     * Метод для очистки записей в "subscribe_table".
     */
    @Query("DELETE FROM subscribe_table")
    suspend fun clearSubscriptionTable()

    /**
     * @return список всех SubscribeEntity в "subscribe_table".
     */
    @Query("SELECT * FROM subscribe_table")
    suspend fun getAllSubscribe(): List<SubscribeEntity>


}