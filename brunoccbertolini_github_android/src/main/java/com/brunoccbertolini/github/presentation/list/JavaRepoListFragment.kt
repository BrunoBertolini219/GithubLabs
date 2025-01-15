package com.brunoccbertolini.github.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunoccbertolini.base.util.base.BaseFragment
import com.brunoccbertolini.base.util.listners.EndlessRecyclerViewScrollListener
import com.brunoccbertolini.github.R
import com.brunoccbertolini.github.databinding.FragmentJavaRepoListBinding
import com.brunoccbertolini.github.di.GithubInjection
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import com.brunoccbertolini.github.util.showDialog
import javax.inject.Inject

class JavaRepoListFragment : BaseFragment<FragmentJavaRepoListBinding>(
    FragmentJavaRepoListBinding::inflate
) {
    @Inject
    lateinit var factory: JavaRepoListViewModel.Factory
    private val viewModel: JavaRepoListViewModel by viewModels { factory }
    private val repoListAdapter = JavaRepoListAdapter()

    private lateinit var endlessListener: EndlessRecyclerViewScrollListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GithubInjection.get().inject(this)
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
        loadData()
    }

    private fun setupListAdapter() = binding.run {
        val linearLayoutManager = LinearLayoutManager(context)
        repoListRv.apply {
            adapter = repoListAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }

        endlessListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(
                page: Int,
                totalItemsCount: Int,
                view: RecyclerView?
            ) {
                viewModel.handleIntent(JavaRepoListIntent.ActionLoadMoreRepoItems(page))
            }
        }
        endlessListener.setAdapter(repoListAdapter)
        repoListRv.addOnScrollListener(endlessListener)

        repoListAdapter.setOnRepoItemClickListener {
            viewModel.handleIntent(JavaRepoListIntent.ActionItemClick(it))
        }
    }

    private fun loadData() {
        viewModel.handleIntent(JavaRepoListIntent.ActionLoadRepoList)
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is JavaRepoListState.Error -> showError(state.errorMessage)
                is JavaRepoListState.Loading -> setLoading(state.isLoading)
                is JavaRepoListState.RepositoryListLoaded -> showRepoList(state.repoList)
                is JavaRepoListState.RepositoryItemClicked -> openRepositoryPulls(state.repoList)
            }
        }
    }

    private fun showError(errorMessage: String?) {
        context.showDialog(
            title = getString(R.string.repo_pr_open_request_title_error),
            msg = errorMessage ?: getString(R.string.repo_pr_open_request_msg_error),
            action = { loadData() },
            dismiss = {}
        )
    }

    private fun openRepositoryPulls(repoList: GithubRepoItemModel) {
        findNavController().navigate(
            JavaRepoListFragmentDirections.toRepoPullRequestsListFragment(
                repoList.repositoryFullName
            )
        )
    }

    private fun setLoading(loading: Boolean) = binding.repoListSrl.run {
        isRefreshing = loading
        isEnabled = loading
    }

    private fun showRepoList(repoList: List<GithubRepoItemModel>) {
        repoListAdapter.addItems(repoList)
    }
}