package com.example.ramadanapp.features.home.home_content.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeDataFromLocalUseCase
import com.example.ramadanapp.features.home.home_content.domain.models.Item
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentListViewModel @Inject constructor(
    private val getHomeDataFromLocalUseCase: GetHomeDataFromLocalUseCase
) : ViewModel() {

    private val _filteredVideos = MutableStateFlow<List<Item>>(emptyList())
    val filteredVideos: StateFlow<List<Item>> = _filteredVideos.asStateFlow()

    private val _contentListData = MutableStateFlow<Resource<RamadanResponse>>(Resource.Progress(true))

    init {
        fetchLocalContent()
    }

    private fun fetchLocalContent() {
        viewModelScope.launch {
            getHomeDataFromLocalUseCase().collect { localResource ->
                _contentListData.value = localResource
            }
        }
    }

    fun setCategoryFilter(category: String) {
        viewModelScope.launch {
            _contentListData.collectLatest { resource ->
                if (resource is Resource.Success) {
                    val allVideos = resource.model.items
                    Log.d("ViewModel", "All videos before filtering: ${allVideos.map { it.category }}")
                    Log.d("ViewModel", "Filtering for category: $category")

                    val filteredList = allVideos.filter { it.category == category }
                    _filteredVideos.value = filteredList

                    Log.d("ViewModel", "Filtered videos count: ${filteredList.size}")
                }
            }
        }
    }
}