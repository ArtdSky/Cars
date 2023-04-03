package com.example.cars.data.storage.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Класс представляет сущность в таблице "subscribe_table".
 * Сущность содержит автоинкриментированный id как primary key
 */
@Entity(tableName = "subscribe_table")
data class SubscribeEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val start : Long? = null,
    val end : Long? = null,
    val watchCarsCount : Int? = null,
    val addToFavoriteCount : Int? = null,
    val isUnlimitedSubscription: Boolean? = null
)
