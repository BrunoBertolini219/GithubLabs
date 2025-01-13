package com.brunoccbertolini.base.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit

inline fun <reified S> service(
    retrofit: Retrofit.Builder,
    client: OkHttpClient.Builder
): Lazy<S> = lazy {
    retrofit.client(client.build())
    retrofit.build().create(S::class.java)
}