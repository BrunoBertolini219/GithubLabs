package com.brunoccbertolini.github.di

import com.brunoccbertolini.base.injection.AppComponent
import com.brunoccbertolini.base.injection.RuntimeScope
import com.brunoccbertolini.github.presentation.list.JavaRepoListFragment
import dagger.Component

@RuntimeScope
@Component(
    modules = [GithubModule::class],
    dependencies = [AppComponent::class]
)
interface GithubComponent {
    fun injetct(target: JavaRepoListFragment)
}