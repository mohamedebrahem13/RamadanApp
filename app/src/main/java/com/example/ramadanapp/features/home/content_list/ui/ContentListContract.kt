package com.example.ramadanapp.features.home.content_list.ui

import com.example.ramadanapp.common.data.models.RamadanAppException
import com.example.ramadanapp.common.ui.viewmodel.ViewAction
import com.example.ramadanapp.common.ui.viewmodel.ViewEvent
import com.example.ramadanapp.common.ui.viewmodel.ViewState
import com.example.ramadanapp.features.home.content_list.domain.models.VideoItem

interface ContentListContract {

    sealed class ContentListAction : ViewAction {
        data class GetItems(val playListId: String) : ContentListAction()

    }

    sealed class ContentListEvent : ViewEvent {
        data class ShowError(val exception: RamadanAppException) : ContentListEvent()

    }

    data class ContentListState(
        val isLoading: Boolean = false,
        val selectedList: String = "",
        val contentData: List<VideoItem> =emptyList(),
        val filteredVideos: List<VideoItem> = emptyList(),
        val exception: RamadanAppException? = null,
        val playingVideo: VideoItem? = null,
        val action: ViewAction? = null
    ) : ViewState {
        companion object {
            fun initial() = ContentListState()
        }
    }
}