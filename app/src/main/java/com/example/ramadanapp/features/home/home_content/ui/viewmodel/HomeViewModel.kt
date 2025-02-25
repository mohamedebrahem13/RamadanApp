package com.example.ramadanapp.features.home.home_content.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeDataFromLocalUseCase
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeDataFromRemoteUseCase
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeDataFromRemoteUseCase: GetHomeDataFromRemoteUseCase,
    private val getHomeDataFromLocalUseCase: GetHomeDataFromLocalUseCase
) : ViewModel() {

    private val _homeData = MutableStateFlow<Resource<RamadanResponse>>(Resource.Progress(true))
    val homeData: StateFlow<Resource<RamadanResponse>> = _homeData.asStateFlow()

    init {
        fetchHomeData()
    }

    private fun fetchHomeData() {
        viewModelScope.launch {
            getHomeDataFromLocalUseCase().collect { localResource ->
                when (localResource) {
                    is Resource.Progress -> _homeData.value = Resource.Progress(true)

                    is Resource.Success -> {
                        if (localResource.model.sections.isNotEmpty() || localResource.model.items.isNotEmpty()) {
                            // Local data exists, use it
                            _homeData.value = localResource
                        } else {
                            // Local data is empty, fetch from remote
                            fetchFromRemote()
                        }
                    }

                    is Resource.Failure -> {
                        // If local data fails, try fetching from remote
                        fetchFromRemote()
                    }
                }
            }
        }
    }

    private fun fetchFromRemote() {
        viewModelScope.launch {
            getHomeDataFromRemoteUseCase().collect { remoteResource ->
                _homeData.value = remoteResource
            }
        }
    }
}