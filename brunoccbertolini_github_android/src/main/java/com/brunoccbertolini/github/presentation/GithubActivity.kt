package com.brunoccbertolini.github.presentation

import android.os.Bundle
import com.brunoccbertolini.base.util.base.BaseActivity
import com.brunoccbertolini.github.R
import com.brunoccbertolini.github.databinding.ActivityGithubBinding

class GithubActivity : BaseActivity<ActivityGithubBinding>(
    ActivityGithubBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    private fun setNavigation() {
        supportFragmentManager.findFragmentById(R.id.github_nav_host)
    }
}