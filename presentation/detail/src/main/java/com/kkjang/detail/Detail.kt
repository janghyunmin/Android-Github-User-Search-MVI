package com.kkjang.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import com.kkjang.core.mvi.MVIFragment
import com.kkjang.detail.databinding.FragmentDetailBinding

@AndroidEntryPoint
class Detail : MVIFragment<FragmentDetailBinding, DetailIntent, DetailState, DetailEffect>(
    inflater = FragmentDetailBinding::inflate
) {
    private val args: DetailArgs by navArgs()
    override val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setIntent(DetailIntent.Refresh(args.id))
    }

    override fun initView() = binding {
        cbBookmark.setOnCheckedChangeListener { _, isChecked ->
            setIntent(DetailIntent.BookmarkChanged(isChecked))
        }
    }

    override fun processState(state: DetailState) = binding {
        ivProfile.load(state.profileUrl)
        cbBookmark.isChecked = state.hasBookmark
        tvName.text = state.name
        tvWebUrl.text = state.webUrl
    }

    override fun processEffect(effect: DetailEffect) = Unit
}