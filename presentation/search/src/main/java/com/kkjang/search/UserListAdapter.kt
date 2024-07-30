package com.kkjang.search

import android.view.View.inflate
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kkjang.core.base.ViewBindingListAdapter
import com.kkjang.domain.model.BookmarkUser
import com.kkjang.search.databinding.HolderUserBinding

class UserListAdapter(
    private val onItemClickListener: (item: BookmarkUser) -> Unit,
    private val onBookmarkChangedListener: (item: BookmarkUser, hasBookmark: Boolean) -> Unit
) : ViewBindingListAdapter<HolderUserBinding, BookmarkUser>(
    diffCallback = diffCallback,
    inflater = HolderUserBinding::inflate
) {
    override val onCreateViewHolder: HolderUserBinding.(() -> Int) -> Unit = { getItemPosition ->
        root.setOnClickListener {
            getItemPosition().takeIf { it != RecyclerView.NO_POSITION }
                ?.let(::getItem)
                ?.let(onItemClickListener)
        }
        cbBookmark.setOnCheckedChangeListener { _, isChecked ->
            getItemPosition().takeIf { it != RecyclerView.NO_POSITION }
                ?.let(::getItem)
                ?.takeIf { it.hasBookmark != isChecked }
                ?.let { onBookmarkChangedListener(it, isChecked) }
        }
    }

    override val bindItem: HolderUserBinding.(BookmarkUser, Int) -> Unit = { item, _ ->
        ivProfile.load(item.profileUrl)
        tvName.text = item.name
        tvWebUrl.text = item.webUrl
        cbBookmark.isChecked = item.hasBookmark
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BookmarkUser>() {
            override fun areItemsTheSame(oldItem: BookmarkUser, newItem: BookmarkUser): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BookmarkUser, newItem: BookmarkUser): Boolean {
                return oldItem == newItem
            }
        }
    }
}