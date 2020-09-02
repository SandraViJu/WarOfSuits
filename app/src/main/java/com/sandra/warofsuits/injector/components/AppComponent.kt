package com.sandra.warofsuits.injector.components

import com.sandra.warofsuits.app.WarOfSuitsApp
import com.sandra.warofsuits.injector.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<WarOfSuitsApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: WarOfSuitsApp): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}