package com.kent.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        // TODO: Add entities
        // ChatEntity::class,
        // MessageEntity::class,
        // UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class KentDatabase : RoomDatabase() {
    // TODO: Add DAOs
    // abstract fun chatDao(): ChatDao
    // abstract fun messageDao(): MessageDao
}

