package com.example.ramadanapp.common.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IRamadanViewModel<Action : ViewAction, Event : ViewEvent, State : ViewState> {

    val singleEvent: Flow<Event>

    val viewState: StateFlow<State>

    /**
     * Must be called in [kotlinx.coroutines.Dispatchers.Main], otherwise it will throw an exception.
     *
     * If you want to process an intent from other [kotlinx.coroutines.CoroutineDispatcher],
     * use `withContext(Dispatchers.Main.immediate) { processIntent(intent) }`.
     */
    fun processIntent(action: Action)
}