package com.sandra.warofsuits.injector.modules

import com.sandra.warofsuits.view.MainActivity
import com.sandra.warofsuits.view.summary.SummaryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModules {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeSummaryActivity(): SummaryActivity

}
