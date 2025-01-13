package com.brunoccbertolini.github.data.remote.service

import com.brunoccbertolini.github.data.remote.model.GithubRepoListRequest
import com.brunoccbertolini.github.data.remote.model.GithubRepoListResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface GithubService {
    //https://api.github.com/search/repositories?q=language:Kotlin&sort=stars&page=1
    @GET("search/repositories")
    fun getJavaRepoList(@QueryMap params: Map<String, String>): Single<GithubRepoListResponse>

    //https://api.github.com/repos/<criador>/<repositório>/pulls
    //suspend fun getRepositoryPulls(@Body request): response

}