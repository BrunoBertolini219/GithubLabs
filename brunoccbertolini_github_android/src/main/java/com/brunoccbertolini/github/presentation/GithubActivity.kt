package com.brunoccbertolini.github.presentation

import android.os.Bundle
import android.widget.Toast
import com.brunoccbertolini.base.util.base.BaseActivity
import com.brunoccbertolini.github.databinding.ActivityGithubBinding

class GithubActivity : BaseActivity<ActivityGithubBinding>(
    ActivityGithubBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(context, "Github Activity", Toast.LENGTH_LONG).show()
    }
}