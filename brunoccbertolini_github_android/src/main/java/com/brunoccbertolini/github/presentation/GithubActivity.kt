package com.brunoccbertolini.github.presentation

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.github_nav_host).navigateUp()
    }

    private fun setNavigation() {
        supportFragmentManager.findFragmentById(R.id.github_nav_host).let {
            it?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
                setToolbar(destination.label?.toString())
            }
        }
    }

    fun setToolbar(
        title: String?,
        displayUpEnabled: Boolean = false
    ) {
        binding.toolbar.apply {
            if (!title.isNullOrEmpty()) this.title = title

            setSupportActionBar(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(displayUpEnabled)
    }
}