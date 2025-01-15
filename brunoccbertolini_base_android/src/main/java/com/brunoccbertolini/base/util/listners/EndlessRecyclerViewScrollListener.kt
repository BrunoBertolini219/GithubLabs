package com.brunoccbertolini.base.util.listners

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener {

    private var visibleThreshold = 5

    var currentPage = 1
        private set

    private var previousTotalItemCount = 0

    private var loading = true

    private val startingPageIndex = 1
    private var mLayoutManager: LinearLayoutManager
    private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        mAdapter = adapter
    }

    constructor(layoutManager: LinearLayoutManager) {
        mLayoutManager = layoutManager
    }

    /**
    This happens many times a second during a scroll, so be wary of the code you place here.
    We are given a few useful parameters to help us work out if we need to load some more data,
    but first we check if we are waiting for the previous load to finish.
    */
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mAdapter!!.itemCount

        if (dy == 0) return

        lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()


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

    private fun isTotalItemCountZero(totalItemCount: Int) = totalItemCount < previousTotalItemCount
    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)
}