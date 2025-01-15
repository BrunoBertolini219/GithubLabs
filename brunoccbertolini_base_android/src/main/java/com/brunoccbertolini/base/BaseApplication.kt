package com.brunoccbertolini.base

import android.app.Application
import com.brunoccbertolini.base.injection.AppInjector
import com.brunoccbertolini.base.injection.DaggerAppComponent

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setDagger()
    }

    private fun setDagger() {
        AppInjector.set(
            DaggerAppComponent.builder()
                .context(this)
                .build()
        )
        AppInjector.get().inject(this)
    }
}