package com.kkjang.core.mvi


interface MviView<I : ViewIntent, S : ViewState, E : ViewEffect> {
    fun setIntent(intent: I)

    fun processState(state: S)

    fun processEffect(effect: E)
}