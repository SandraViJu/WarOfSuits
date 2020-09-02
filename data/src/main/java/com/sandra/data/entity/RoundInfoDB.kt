package com.sandra.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "war_of_suits_winners")
data class RoundInfoDB(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id : Int? = null,
    @ColumnInfo(name = "winner") var winner: String,
    @ColumnInfo(name = "card_a") var cardA: String,
    @ColumnInfo(name = "card_b") var cardB: String
)
