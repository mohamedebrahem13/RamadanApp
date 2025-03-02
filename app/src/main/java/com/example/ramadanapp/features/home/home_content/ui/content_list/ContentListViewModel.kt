package com.example.ramadanapp.features.home.home_content.ui.content_list

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.common.ui.viewmodel.RamadanViewModel
import com.example.ramadanapp.common.ui.viewmodel.ViewAction
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeDataFromLocalUseCase
import com.example.ramadanapp.features.home.home_content.domain.models.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentListViewModel @Inject constructor(
    private val getHomeDataFromLocalUseCase: GetHomeDataFromLocalUseCase
) : RamadanViewModel<ContentListContract.ContentListAction, ContentListContract.ContentListEvent, ContentListContract.ContentListState>(
    ContentListContract.ContentListState.initial()
) {


    private fun fetchLocalContent(category: String) {
            getHomeDataFromLocalUseCase.invoke(viewModelScope) {resource ->
                when (resource) {
                    is Resource.Success ->{
                        Log.d("ContentListViewModel", "Fetched data from local: ${resource.model}")
                        setState(oldViewState.copy(contentData = resource.model.items))
                        setCategoryFilter(category)
                    }
                    is Resource.Progress -> setState(oldViewState.copy(isLoading = resource.loading))
                    is Resource.Failure -> sendEvent(
                        ContentListContract.ContentListEvent.ShowError(
                            resource.exception
                        )
                    )
                }

        }
 }

    override fun clearState() {
        ContentListContract.ContentListState.initial()
    }

    override fun onActionTrigger(action: ViewAction?) {
        when (action) {
            is ContentListContract.ContentListAction.GetItems -> {
                fetchLocalContent(action.category)
            }
        }
    }

    private fun setCategoryFilter(category: String) {
        val filteredList = oldViewState.contentData.filter { it.category == category }

        Log.d("ViewModel", "Filtering for category: $category, Videos: ${filteredList.size}")

        val firstVideo = filteredList.firstOrNull()  // ✅ Get first video to play

        setState(
            oldViewState.copy(
                filteredVideos = filteredList,
                selectedCategory = category,
                playingVideo = firstVideo  // ✅ Set the first video to play
            )
        )
    }
    fun setPlayingVideo(video: Item) {
        setState(
            oldViewState.copy(
                playingVideo = video,
                filteredVideos = oldViewState.filteredVideos.filter { it != video }
            )
        )
    }

}