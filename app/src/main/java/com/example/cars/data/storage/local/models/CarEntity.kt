package com.example.cars.data.storage.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс представляет сущность в таблице "cars_table".
 * Сущность содержит автоинкриментированный id как primary key
 */
@Entity(tableName = "cars_table")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String? = null,
    val year: Int? = null,
    val engineVolume: Double? = null,
    val created: Long? = null,
    val createdAt: String? = null,
    val imageString: String? = null,
    val imageInt: Int? = null,
    val image: ByteArray? = null,
)
