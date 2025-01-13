package com.brunoccbertolini.github.domain.repository

import com.brunoccbertolini.github.domain.entity.GithubRepoListDetail
import com.brunoccbertolini.github.domain.entity.GithubRepoListModel
import io.reactivex.Single

interface GithubRepository {
    fun getJavaRepoList(request: GithubRepoListDetail): Single<GithubRepoListModel>
}