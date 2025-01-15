package com.brunoccbertolini.github.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunoccbertolini.base.util.base.BaseFragment
import com.brunoccbertolini.github.R
import com.brunoccbertolini.github.databinding.FragmentRepoPrListBinding
import com.brunoccbertolini.github.di.GithubInjection
import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel
import com.brunoccbertolini.github.presentation.GithubActivity
import com.brunoccbertolini.github.util.openPullRequestWeb
import com.brunoccbertolini.github.util.showDialog
import javax.inject.Inject

class RepoPullRequestsListFragment : BaseFragment<FragmentRepoPrListBinding>(
    FragmentRepoPrListBinding::inflate
) {
    @Inject
    lateinit var factory: RepoPullRequestsListViewModel.Factory
    private val viewModel: RepoPullRequestsListViewModel by viewModels { factory }

    private val args by navArgs<RepoPullRequestsListFragmentArgs>()
    private val repoPrListAdapter by lazy { RepoPrListAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GithubInjection.get().inject(this)
        (requireActivity() as GithubActivity).setToolbar(
            title = args.fullName,
            displayUpEnabled = true
        )
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
        binding.repoListRv.apply {
            adapter = repoPrListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        repoPrListAdapter.setOnRepoItemClickListener {
            viewModel.handleIntent(RepoPullRequestsListIntent.ActionItemClick(it))
        }
    }

    private fun loadData() {
        viewModel.handleIntent(RepoPullRequestsListIntent.ActionLoadPrList(args.fullName))
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                is RepoPullRequestsListState.Error -> showError(state.errorMessage)
                is RepoPullRequestsListState.Loading -> setLoading(state.isLoading)
                is RepoPullRequestsListState.PullRequestListLoaded -> showRepoList(state.repoList)
                is RepoPullRequestsListState.PullRequestItemClicked -> openRepositoryPulls(state.repoList)
            }
        }
    }

    private fun openRepositoryPulls(repoPrList: RepositoryPrItemModel) {
        activity?.openPullRequestWeb(repoPrList.pullRequestUrl)
    }

    private fun setLoading(loading: Boolean) = binding.repoPrListSrl.run {
        isRefreshing = loading
        isEnabled = loading
    }

    private fun showError(errorMessage: String?) {
        context.showDialog(
            title = getString(R.string.repo_pr_open_request_title_error),
            msg = errorMessage ?: getString(R.string.repo_pr_open_request_msg_error),
            action = { loadData() },
            dismiss = {}
        )
    }

    private fun showRepoList(repoList: List<RepositoryPrItemModel>) {
        repoPrListAdapter.differ.submitList(repoList)
    }
}