package com.brunoccbertolini.base.injection

import com.brunoccbertolini.base.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideAdapter(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun providesClientBuilder(interceptors: @JvmSuppressWildcards(true) Set<Interceptor>): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        interceptors.forEach {
            builder.addInterceptor(it)
        }
        return builder
    }

    @Provides
    @ElementsIntoSet
    fun provideInterceptors(): Set<Interceptor> = setOf()

    @Provides
    @IntoSet
    fun provideAuthInterceptor(): Interceptor {

        return AuthInterceptor(token = BuildConfig.API_KEY) // key could come from an native module
    }

    @Provides
    @IntoSet
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl() = "https://api.github.com/" // Could come from an native module

    companion object {
        const val BASE_URL = "BASE_URL"
    }
}