package com.brunoccbertolini.base.injection

object AppInjector {

    private lateinit var appComponent: AppComponent

    fun get(): AppComponent = appComponent

    fun set(appComponent: AppComponent) {
        AppInjector.appComponent = appComponent
    }
}
