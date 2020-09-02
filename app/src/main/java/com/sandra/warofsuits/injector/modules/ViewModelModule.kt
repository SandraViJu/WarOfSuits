package com.sandra.warofsuits.injector.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sandra.warofsuits.injector.ViewModelFactory
import com.sandra.warofsuits.injector.ViewModelKey
import com.sandra.warofsuits.view.MainViewModel
import com.sandra.warofsuits.view.summary.SummaryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SummaryViewModel::class)
    fun providesSummaryViewModel(summaryViewModel: SummaryViewModel): ViewModel

}