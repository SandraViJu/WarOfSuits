package com.sandra.warofsuits.injector.modules

import android.content.Context
import com.sandra.warofsuits.app.WarOfSuitsApp
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(
    includes = [ActivityModules::class,
        RepositoryModule::class,
        UseCaseModule::class,
        RoomModule::class,
        ViewModelModule::class,
        MapperModule::class]
)
class AppModule(val app: WarOfSuitsApp) {

    @Provides
    fun providesApplication(): WarOfSuitsApp = app

    @Provides
    fun providesApplicationContext(): Context = app.applicationContext

    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO

}