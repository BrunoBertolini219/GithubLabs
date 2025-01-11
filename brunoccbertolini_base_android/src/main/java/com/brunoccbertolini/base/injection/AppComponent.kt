package com.brunoccbertolini.base.injection

import android.content.Context
import com.brunoccbertolini.base.BaseApplication
import com.brunoccbertolini.base.ui.SplashActivity
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class
])
interface AppComponent {
    fun inject(target: BaseApplication)
    fun inject(target: SplashActivity)

    fun provideRetrofitBuilder(): Retrofit.Builder
    fun provideClient(): OkHttpClient.Builder

    @Named(NetworkModule.COROUTINES_RETROFIT)
    fun provideCoroutineRetrofitBuilder(): Retrofit.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}
