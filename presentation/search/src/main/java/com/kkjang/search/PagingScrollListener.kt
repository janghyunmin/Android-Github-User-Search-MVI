package com.kkjang.search

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingScrollListener<out T : RecyclerView.Adapter<*>>(
    private val action: () -> Unit
) : RecyclerView.OnScrollListener() {

    @Suppress("UNCHECKED_CAST")
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
        val adapter = recyclerView.adapter as? T ?: return
        val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
        val itemCount = adapter.itemCount

        if (itemCount > 0 && (itemCount - lastVisibleItemPosition) < pagingThreadHold) {
            action.invoke()
        }
    }

    companion object {
        private const val pagingThreadHold = 5
    }
}