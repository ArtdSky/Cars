package com.example.cars.data.storage.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cars.data.storage.local.models.CarEntity
import com.example.cars.data.storage.local.models.SubscribeEntity

/**

 * Класс для создания базы данных приложения.
 *
 * @property carsDao интерфейс для работы с cars_table.
 * @property subscribeDao интерфейс для работы с subscribe_table.
 *
 */
@Database(entities = [CarEntity::class, SubscribeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carsDao(): CarsDao

    abstract fun subscribeDao(): SubscribeDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /** метод возвращает текущую базу данных если она существует,
         * если нет то создает новую.
         *
         * @param context конекст приложения.
         * @return экземпляр базы данных.
         */
        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}