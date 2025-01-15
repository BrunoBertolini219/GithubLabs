package com.brunoccbertolini.base.util.intent.github

import android.content.Context
import android.content.Intent

object Github {
    private const val PACKAGE_NAME = "com.brunoccbertolini.github.presentation.GithubActivity"

    fun createIntent(context: Context) = Intent(context, Class.forName(PACKAGE_NAME))
}