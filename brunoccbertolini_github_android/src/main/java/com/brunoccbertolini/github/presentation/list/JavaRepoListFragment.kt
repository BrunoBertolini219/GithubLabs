package com.brunoccbertolini.github.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunoccbertolini.base.util.base.BaseFragment
import com.brunoccbertolini.github.databinding.FragmentJavaRepoListBinding
import com.brunoccbertolini.github.di.GithubInjection
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import javax.inject.Inject

class JavaRepoListFragment : BaseFragment<FragmentJavaRepoListBinding>(
    FragmentJavaRepoListBinding::inflate
) {
    @Inject
    lateinit var factory: JavaRepoListViewModel.Factory
    private val viewModel: JavaRepoListViewModel by viewModels { factory }

    private val repoListAdapter by lazy { JavaRepoListAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val daggerComponent = GithubInjection.get()
        daggerComponent.inject(this)
        daggerComponent.injectViewModel()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        setupListAdapter()
        setObservers()
        viewModel.handleIntent(JavaRepoListIntent.ActionLoadRepoList)
    }

    private fun setupListAdapter() = binding.run {
        binding.repoListRv.apply {
            adapter = repoListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        repoListAdapter.setOnRepoItemClickListener {
            viewModel.handleIntent(JavaRepoListIntent.ActionItemClick(it))
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                is JavaRepoListState.Error -> {}
                is JavaRepoListState.Loading -> setLoading(state.isLoading)
                is JavaRepoListState.RepositoryListLoaded -> showRepoList(state.repoList)
                is JavaRepoListState.RepositoryItemClicked -> openRepositoryPulls(state.repoList)
            }
        }
    }

    private fun openRepositoryPulls(repoList: GithubRepoItemModel) {
        TODO("Not yet implemented")
    }

    private fun setLoading(loading: Boolean) = binding.repoListSrl.run {
        isRefreshing = loading
        isEnabled = loading
    }

    private fun showRepoList(repoList: List<GithubRepoItemModel>) {
        repoListAdapter.differ.submitList(repoList)
    }
}