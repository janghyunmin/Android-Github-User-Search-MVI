package com.kkjang.search

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged

@AndroidEntryPoint
class Search : MVIFragment<FragmentSearchBinding, SearchIntent, SearchState, SearchEffect>(
    inflater = FragmentSearchBinding::inflate
) {
    override val viewModel: SearchViewModel by viewModels()
    private val userListAdapter by lazy {
        UserListAdapter(
            onItemClickListener = { item ->
                val deepLink = DeepLink.Detail(item.id)
                deepLink(deepLink)
            },
            onBookmarkChangedListener = { item, hasBookmark ->
                setIntent(SearchIntent.BookmarkChanged(item.id, hasBookmark))
            }
        )
    }

    override fun initView() = binding {
        editQuery.doOnTextChanged { text, _, _, _ ->
            text?.toString()?.let(SearchIntent::QueryChanged)?.let(::setIntent)
        }
        with(rvUser) {
            adapter = userListAdapter
            addOnScrollListener(PagingScrollListener<UserListAdapter> {
                setIntent(SearchIntent.LoadMore)
            })
        }
        setIntent(SearchIntent.Sync)
    }

    override fun processState(state: SearchState) = binding {
        editQuery.setTextIfNewWithSelection(state.query)
        userListAdapter.submitList(state.bookmarkUserList)
        pbLoading.isVisible = state.isLoading
    }

    override fun processEffect(effect: SearchEffect) = when (effect) {
        is SearchEffect.ShowToastMessageEffect -> {
            Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
        }
    }
}