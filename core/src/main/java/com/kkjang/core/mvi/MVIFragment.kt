package com.kkjang.core.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.kkjang.core.base.BaseFragment

abstract class MVIFragment<BINDING : ViewBinding, I : ViewIntent, S : ViewState, E : ViewEffect>(
    inflater: (LayoutInflater, ViewGroup?, Boolean) -> BINDING
) : BaseFragment<BINDING>(inflater), MviView<I, S, E> {
    abstract val viewModel: MVIViewModel<I, S, E>

    override fun setIntent(intent: I) {
        viewModel.dispatch(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initCollect()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                processState(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.effect.collect {
                processEffect(it)
            }
        }
    }
}