package com.sandra.data.entity.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.sandra.data.entity.CardsEntity

class DataTypeConverter {
    companion object {

        var gson= Gson()

        @TypeConverter
        fun cardToString( card: CardsEntity): String =
            gson.toJson(card)

        @TypeConverter
        fun stringToCard(cardString: String): CardsEntity =
            try {
                gson.fromJson(cardString, CardsEntity::class.java)
            } catch (e: JsonSyntaxException) {
                null
            }?: CardsEntity()

    }
}