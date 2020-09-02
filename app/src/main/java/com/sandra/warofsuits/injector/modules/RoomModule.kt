package com.sandra.warofsuits.injector.modules

import android.content.Context
import androidx.room.Room
import com.sandra.data.database.WarOfSuitsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesWarOfSuitsDatabase(context: Context): WarOfSuitsDatabase =
        Room.databaseBuilder(context, WarOfSuitsDatabase::class.java, "war_of_suits.db")
            .build()

}
