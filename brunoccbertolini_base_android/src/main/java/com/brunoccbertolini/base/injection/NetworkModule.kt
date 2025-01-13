package com.brunoccbertolini.base.injection

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        @Named(BASE_URL) baseUrl: String,
        converter: Converter.Factory,
        adapter: CallAdapter.Factory
    ): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converter)
    }

    @Provides
    @Singleton
    @Named(COROUTINES_RETROFIT)
    fun provideCoroutineRetrofitBuilder(
        @Named(BASE_URL) baseUrl: String,
        converter: Converter.Factory,
    ): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
    }

    @Provides
    @Singleton
    fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideAdapter(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun providesClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl() = "https://api.github.com/" // Could come from an native module

    companion object {
        const val BASE_URL = "BASE_URL"
        const val COROUTINES_RETROFIT = "COROUTINES_RETROFIT"
    }
}