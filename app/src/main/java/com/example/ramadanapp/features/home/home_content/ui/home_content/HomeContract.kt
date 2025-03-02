package com.example.ramadanapp.features.home.home_content.ui.home_content

import com.example.ramadanapp.common.data.models.RamadanAppException
import com.example.ramadanapp.common.ui.viewmodel.ViewAction
import com.example.ramadanapp.common.ui.viewmodel.ViewEvent
import com.example.ramadanapp.common.ui.viewmodel.ViewState
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse


interface HomeContract {

    sealed class HomeAction : ViewAction {
        data class SelectCategory(val categoryTitle: String) : HomeAction()
    }

    sealed class HomeEvent : ViewEvent {
        data class NavigateToCategory(val categoryTitle: String) : HomeEvent()
        data class ShowError(val exception: RamadanAppException) : HomeEvent()
    }

    data class HomeState(
        val isLoading: Boolean = false,
        val homeData: RamadanResponse? = null,
        val exception: RamadanAppException?= null,
        val action: ViewAction? = null
    ) : ViewState {
        companion object {
            fun initial() = HomeState()
        }
    }
}
