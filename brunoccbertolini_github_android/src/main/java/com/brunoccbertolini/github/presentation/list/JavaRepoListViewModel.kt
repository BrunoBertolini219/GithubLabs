package com.brunoccbertolini.github.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunoccbertolini.base.util.base.BaseFactory
import com.brunoccbertolini.base.util.singleSubscribe
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import com.brunoccbertolini.github.domain.entity.GithubRepoListDetail
import com.brunoccbertolini.github.domain.usecase.GetRepoListUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class JavaRepoListViewModel(
    private val getRepoListUseCase: GetRepoListUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _state = MutableLiveData<JavaRepoListState>()
    val state: LiveData<JavaRepoListState> = _state
    private var hasNextPage = true
    private var currentPage = 1

    fun handleIntent(intent: JavaRepoListIntent) {
        when (intent) {
            JavaRepoListIntent.ActionLoadRepoList -> getJavaRepositoriesList()
            is JavaRepoListIntent.ActionItemClick -> handleRepoClicked(intent.repoItem)
            is JavaRepoListIntent.ActionLoadMoreRepoItems -> TODO()
        }
    }

    private fun getJavaRepositoriesList(page: Int = currentPage) {
        compositeDisposable.add(
            getRepoListUseCase(
                GithubRepoListDetail(
                    page = page,
                    language = REPOSITORY_LANGUAGE,
                    sort = SORT_BY
                )
            ).singleSubscribe(
                onLoading = { _state.value = JavaRepoListState.Loading(it) },
                onSuccess = { repoList ->
                    hasNextPage = repoList.hasNextPage
                    currentPage += 1
                    _state.value = JavaRepoListState.RepositoryListLoaded(repoList.items)
                },
                onError = { _state.value = JavaRepoListState.Error(it.message) }
            )
        )
    }

    private fun handleRepoClicked(repoItem: GithubRepoItemModel) {
        _state.value = JavaRepoListState.RepositoryItemClicked(repoItem)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    class Factory @Inject constructor(
        getRepoListUseCase: GetRepoListUseCase
    ) : BaseFactory({ JavaRepoListViewModel(getRepoListUseCase) })

    companion object {
        private const val REPOSITORY_LANGUAGE = "Java"
        private const val SORT_BY = "stars"
    }
}