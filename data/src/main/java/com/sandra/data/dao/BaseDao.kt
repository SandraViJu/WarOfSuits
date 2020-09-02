package com.sandra.data.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

abstract class BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(type: T)

}
