package com.example.ramadanapp.features.home.home_content.ui.viewmodel.content_list

import com.example.ramadanapp.common.data.models.RamadanAppException
import com.example.ramadanapp.common.ui.viewmodel.ViewAction
import com.example.ramadanapp.common.ui.viewmodel.ViewEvent
import com.example.ramadanapp.common.ui.viewmodel.ViewState
import com.example.ramadanapp.features.home.home_content.domain.models.Item

interface ContentListContract {

    sealed class ContentListAction : ViewAction {
        data class GetItems(val category: String) : ContentListAction()

    }

    sealed class ContentListEvent : ViewEvent {
        data class ShowError(val exception: RamadanAppException) : ContentListEvent()

    }

    data class ContentListState(
        val isLoading: Boolean = false,
        val selectedCategory: String = "",
        val contentData: List<Item> =emptyList(),
        val filteredVideos: List<Item> = emptyList(),
        val exception: RamadanAppException? = null,
        val playingVideo: Item? = null,
        val action: ViewAction? = null
    ) : ViewState {
        companion object {
            fun initial() = ContentListState()
        }
    }
}