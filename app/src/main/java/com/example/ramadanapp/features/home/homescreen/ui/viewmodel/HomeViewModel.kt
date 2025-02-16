package com.example.ramadanapp.features.home.homescreen.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.features.home.homescreen.domain.intractor.GetHomeVideosUseCase
import com.example.ramadanapp.features.home.homescreen.domain.models.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeVideosUseCase: GetHomeVideosUseCase
) : ViewModel() {

    private val _homeVideos = MutableStateFlow<Resource<List<Video>>>(Resource.Progress(true))
    val homeVideos: StateFlow<Resource<List<Video>>> = _homeVideos.asStateFlow()

    private val _groupedVideos = MutableStateFlow<Map<String, List<Video>>>(emptyMap())
    val groupedVideos: StateFlow<Map<String, List<Video>>> = _groupedVideos
    init {
        fetchHomeVideos()
    }

    private fun fetchHomeVideos() {
        viewModelScope.launch {
            getHomeVideosUseCase()
                .collect { resource ->
                    _homeVideos.value = resource
                    if (resource is Resource.Success) {
                        _groupedVideos.value = resource.model.groupBy { it.category }
                    }
                }
        }
    }

}