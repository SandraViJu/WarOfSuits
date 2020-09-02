package com.sandra.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sandra.data.dao.WarOfSuitsDao
import com.sandra.data.entity.RoundInfoDB

@Database(entities = [(RoundInfoDB::class)], version = 1)
abstract class WarOfSuitsDatabase : RoomDatabase() {

    abstract fun warOfSuitsDao(): WarOfSuitsDao

}
