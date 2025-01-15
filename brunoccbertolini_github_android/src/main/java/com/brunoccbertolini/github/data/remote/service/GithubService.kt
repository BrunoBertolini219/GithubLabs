package com.brunoccbertolini.github.data.remote.service

import com.brunoccbertolini.github.data.remote.model.GithubRepoListResponse
import com.brunoccbertolini.github.data.remote.model.RepositoryPrItemResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GithubService {
    @GET("search/repositories")
    fun getJavaRepoList(@QueryMap params: Map<String, String>): Single<GithubRepoListResponse>

    @GET("repos/{full_name}/pulls")
    fun getRepositoryPulls(@Path("full_name", encoded = true) fullName: String): Single<List<RepositoryPrItemResponse>>
}