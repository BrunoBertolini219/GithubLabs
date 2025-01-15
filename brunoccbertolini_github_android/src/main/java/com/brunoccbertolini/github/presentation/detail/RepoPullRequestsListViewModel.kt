package com.brunoccbertolini.github.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunoccbertolini.base.util.base.BaseFactory
import com.brunoccbertolini.base.util.singleSubscribe
import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel
import com.brunoccbertolini.github.domain.usecase.GetRepositoryPrListUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RepoPullRequestsListViewModel(
    private val getRepositoryPrListUseCase: GetRepositoryPrListUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _state = MutableLiveData<RepoPullRequestsListState>()
    val state: LiveData<RepoPullRequestsListState> = _state

    fun handleIntent(intent: RepoPullRequestsListIntent) {
        when (intent) {
            is RepoPullRequestsListIntent.ActionLoadPrList -> getRepositoryPrList(intent.fullName)
            is RepoPullRequestsListIntent.ActionItemClick -> handleRepoClicked(intent.repoItem)
            is RepoPullRequestsListIntent.ActionLoadMoreRepoItems -> TODO()
        }
    }

    private fun getRepositoryPrList(fullName: String) {
        compositeDisposable.add(
            getRepositoryPrListUseCase(
                fullName = fullName
            ).singleSubscribe(
                onLoading = { _state.value = RepoPullRequestsListState.Loading(it) },
                onSuccess = { prList ->
                    _state.value = RepoPullRequestsListState.PullRequestListLoaded(prList)
                },
                onError = { _state.value = RepoPullRequestsListState.Error(it.message) }
            )
        )
    }

    private fun handleRepoClicked(repoItem: RepositoryPrItemModel) {
        _state.value = RepoPullRequestsListState.PullRequestItemClicked(repoItem)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    class Factory @Inject constructor(
        getRepositoryPrListUseCase: GetRepositoryPrListUseCase
    ) : BaseFactory({ RepoPullRequestsListViewModel(getRepositoryPrListUseCase) })
}