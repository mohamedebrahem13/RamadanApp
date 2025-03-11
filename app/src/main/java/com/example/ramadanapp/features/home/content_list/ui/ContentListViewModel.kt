package com.example.ramadanapp.features.home.content_list.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.common.ui.viewmodel.RamadanViewModel
import com.example.ramadanapp.common.ui.viewmodel.ViewAction
import com.example.ramadanapp.features.home.content_list.domain.intractor.GetPlaylistUseCase
import com.example.ramadanapp.features.home.content_list.domain.models.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentListViewModel @Inject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase
) : RamadanViewModel<ContentListContract.ContentListAction, ContentListContract.ContentListEvent, ContentListContract.ContentListState>(
    ContentListContract.ContentListState.initial()
) {


    private fun getListById(playListId: String) {
        getPlaylistUseCase(viewModelScope,playListId) {resource ->
                when (resource) {
                    is Resource.Success -> {
                        val videos = resource.model.videos
                        val firstVideo = videos.firstOrNull()
                        val filteredList = videos.toMutableList().apply { firstVideo?.let { remove(it) } }

                        setState(
                            oldViewState.copy(
                                contentData = videos, // Keep the full list
                                selectedList = playListId,
                                filteredVideos = filteredList,
                                playingVideo = firstVideo
                            )
                        )
                    }
                    is Resource.Progress -> setState(oldViewState.copy(isLoading = resource.loading))
                    is Resource.Failure ->{
                        Log.d("ContentListViewModel", "Error: ${resource.exception}")
                        sendEvent(
                            ContentListContract.ContentListEvent.ShowError(
                                resource.exception
                            )
                        )
                    }
                }

        }
 }

    override fun clearState() {
        ContentListContract.ContentListState.initial()
    }

    override fun onActionTrigger(action: ViewAction?) {
        when (action) {
            is ContentListContract.ContentListAction.GetItems -> {
                Log.d("ContentListViewModel", "Received action: ${action.playListId}")
                getListById(action.playListId)
            }
        }
    }

    fun setPlayingVideo(video: VideoItem) {
        val updatedVideos = oldViewState.contentData.toMutableList().apply {
            remove(video) // Remove newly selected video
        }
        setState(
            oldViewState.copy(
                playingVideo = video,
                filteredVideos = updatedVideos
            )
        )
    }


}