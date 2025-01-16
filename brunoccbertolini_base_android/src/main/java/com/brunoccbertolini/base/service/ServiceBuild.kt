package com.brunoccbertolini.base.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

inline fun <reified S> service(
    retrofit: Retrofit.Builder,
    client: OkHttpClient.Builder,
    vararg interceptor: Interceptor
): Lazy<S> = lazy {
    interceptor.forEach {
        client.addInterceptor(it)
    }
    retrofit.client(client.build())
    retrofit.build().create(S::class.java)
}