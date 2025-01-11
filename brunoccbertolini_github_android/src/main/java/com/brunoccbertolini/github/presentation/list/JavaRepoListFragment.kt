package com.brunoccbertolini.github.presentation.list

import android.os.Bundle
import android.view.View
import com.brunoccbertolini.base.util.base.BaseFragment
import com.brunoccbertolini.github.databinding.FragmentJavaRepoListBinding
import com.brunoccbertolini.github.di.GithubInjection

class JavaRepoListFragment : BaseFragment<FragmentJavaRepoListBinding>(
    FragmentJavaRepoListBinding::inflate
) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        GithubInjection.get().injetct(this)
    }
}