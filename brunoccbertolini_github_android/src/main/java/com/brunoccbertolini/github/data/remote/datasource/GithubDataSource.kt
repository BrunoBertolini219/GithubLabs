package com.brunoccbertolini.github.data.remote.datasource

import com.brunoccbertolini.base.service.service
import com.brunoccbertolini.github.data.remote.model.GithubRepoListResponse
import com.brunoccbertolini.github.data.remote.service.GithubService
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

interface GithubDataSource {
    fun getJavaRepoList(request: Map<String, String>): Single<GithubRepoListResponse>
}

class GithubDataSourceRemote @Inject constructor(
    retrofit: Retrofit.Builder,
    client: OkHttpClient.Builder
) : GithubDataSource {

    private val service by service<GithubService>(
        retrofit = retrofit,
        client = client
    )

    override fun getJavaRepoList(request: Map<String, String>) = service.getJavaRepoList(request)
}