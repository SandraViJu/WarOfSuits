package com.sandra.warofsuits.injector.modules

import com.sandra.data.repository.DataBaseRepository
import com.sandra.data.repository.SuitsRepository
import com.sandra.data.repositoyimpl.DataBaseRepositoryImpl
import com.sandra.data.repositoyimpl.SuitsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindsDataBaseRepository(dataBaseRepository: DataBaseRepositoryImpl): DataBaseRepository

    @Binds
    fun bindsSuitsRepository(repository: SuitsRepositoryImpl): SuitsRepository

}