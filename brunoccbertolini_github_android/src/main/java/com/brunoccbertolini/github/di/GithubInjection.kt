package com.brunoccbertolini.github.di

import com.brunoccbertolini.base.injection.AppInjector

object GithubInjection {
    private lateinit var component: GithubComponent

    fun get(): GithubComponent {
        if (!::component.isInitialized) {
            component = DaggerGithubComponent.builder().appComponent(AppInjector.get()).build()
        }
        return component
    }
}