package com.brunoccbertolini.base.ui

import android.os.Bundle
import com.brunoccbertolini.base.util.base.BaseActivity
import com.brunoccbertolini.base.databinding.ActivitySplashBinding
import com.brunoccbertolini.base.injection.AppInjector
import com.brunoccbertolini.base.util.intent.github.Github

class SplashActivity : BaseActivity<ActivitySplashBinding>(
    inflate = ActivitySplashBinding::inflate,
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppInjector.get().inject(this)
        showSplashAnimation()
    }

    private fun openGitRepoList() {
        this.startActivity(
            Github.createIntent(
                context = this@SplashActivity
            )
        )
        finish()
    }

    private fun showSplashAnimation() {
        Thread.sleep(SPLASH_DURATION)
        openGitRepoList()
    }

    companion object {
        const val SPLASH_DURATION = 2000L
    }
}