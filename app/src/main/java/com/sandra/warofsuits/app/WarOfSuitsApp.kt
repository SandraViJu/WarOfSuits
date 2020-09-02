package com.sandra.warofsuits.app

import com.sandra.warofsuits.injector.components.DaggerAppComponent
import com.sandra.warofsuits.injector.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WarOfSuitsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()

}