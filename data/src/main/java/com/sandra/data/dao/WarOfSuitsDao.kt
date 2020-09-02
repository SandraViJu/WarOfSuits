package com.sandra.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.sandra.data.entity.RoundInfoDB

@Dao
abstract class WarOfSuitsDao : BaseDao<RoundInfoDB>() {

    @Transaction
    @Query("Select * from war_of_suits_winners")
    abstract fun getRoundInfo(): List<RoundInfoDB>

    @Transaction
    @Query("DELETE from war_of_suits_winners")
    abstract fun clear()

}
