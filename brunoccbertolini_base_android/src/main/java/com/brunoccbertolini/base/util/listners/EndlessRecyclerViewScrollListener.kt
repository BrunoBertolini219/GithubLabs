package br.com.nightking.base.ui.common

import androidx.recyclerview.widget.RecyclerView
import br.com.nightking.base.ui.base.RecyclerViewAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener {
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 5

    // The current offset index of data you have loaded
    var currentPage = 0
        private set

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0

    // True if we are still waiting for the last set of data to load.
    private var loading = true

    // Sets the starting page index
    private val startingPageIndex = 0
    var mLayoutManager: RecyclerView.LayoutManager
    var mAdapter: RecyclerViewAdapter? = null
    fun setAdapter(adapter: RecyclerViewAdapter?) {
        mAdapter = adapter
    }

    constructor(layoutManager: LinearLayoutManager) {
        mLayoutManager = layoutManager
    }

    constructor(layoutManager: GridLayoutManager) {
        mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    constructor(layoutManager: StaggeredGridLayoutManager) {
        mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    /**
    This happens many times a second during a scroll, so be wary of the code you place here.
    We are given a few useful parameters to help us work out if we need to load some more data,
    but first we check if we are waiting for the previous load to finish.
    */
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mAdapter!!.displayableItemsCount

        if (dy == 0) return

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> {
                lastVisibleItemPosition =
                    (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItemPosition =
                    (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }
        }


        if (isTotalItemCountZero(totalItemCount)) {
            invalidateAndReset(totalItemCount)
        }

        if (checkIfLoadingAndIfDatasetCountChanged(totalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (checkIfLoadingAndIfLastItemWasShown(
                    lastVisibleItemPosition,
                    totalItemCount
            )
        ) {
            currentPage++
            onLoadMore(
                    currentPage,
                    totalItemCount,
                    view
            )
            loading = true
        }
    }

    private fun checkIfLoadingAndIfLastItemWasShown(
            lastVisibleItemPosition: Int,
            totalItemCount: Int
    ) = !loading && lastVisibleItemPosition + visibleThreshold > totalItemCount

    private fun checkIfLoadingAndIfDatasetCountChanged(totalItemCount: Int) =
        loading && totalItemCount > previousTotalItemCount

    private fun invalidateAndReset(totalItemCount: Int) {
        currentPage = startingPageIndex
        previousTotalItemCount = totalItemCount
        if (totalItemCount == 0) {
            loading = true
        }
    }

    private fun isTotalItemCountZero(totalItemCount: Int) =
        totalItemCount < previousTotalItemCount

    fun resetState() {
        currentPage = startingPageIndex
        previousTotalItemCount = 0
        loading = true
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)
}