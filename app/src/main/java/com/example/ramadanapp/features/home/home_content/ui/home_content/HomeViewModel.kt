package com.example.ramadanapp.features.home.home_content.ui.home_content

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.common.ui.viewmodel.RamadanViewModel
import com.example.ramadanapp.common.ui.viewmodel.ViewAction
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeDataFromLocalUseCase
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeDataFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeDataFromRemoteUseCase: GetHomeDataFromRemoteUseCase,
    private val getHomeDataFromLocalUseCase: GetHomeDataFromLocalUseCase
) : RamadanViewModel<HomeContract.HomeAction, HomeContract.HomeEvent, HomeContract.HomeState>(
    HomeContract.HomeState.initial()
) {

    init {
        fetchHomeData()
    }

    private fun fetchHomeData() {
        viewModelScope.launch {
            getHomeDataFromLocalUseCase().collect { localResource ->
                when (localResource) {
                    is Resource.Progress -> setState(oldViewState.copy(isLoading = localResource.loading))

                    is Resource.Success -> {
                        if (localResource.model.sections.isNotEmpty() || localResource.model.items.isNotEmpty()) {
                            // Use local data
                            setState(oldViewState.copy(isLoading = false, homeData = localResource.model))
                        } else {
                            // No local data, fetch from remote
                            fetchFromRemote()
                        }
                    }

                    is Resource.Failure -> {
                        // Local data failed, fetch from remote
                        fetchFromRemote()
                    }
                }
            }
        }
    }

    private fun fetchFromRemote() {
        viewModelScope.launch {
            getHomeDataFromRemoteUseCase.invoke (this){ resource ->
                when (resource) {
                    is Resource.Progress -> setState(oldViewState.copy(isLoading = resource.loading))

                    is Resource.Success -> {
                        setState(oldViewState.copy(isLoading = false, homeData = resource.model))
                        Log.d("HomeViewModel", "Fetched data from remote: ${resource.model}")
                    }

                    is Resource.Failure -> {
                        setState(oldViewState.copy( exception = resource.exception))
                        sendEvent(HomeContract.HomeEvent.ShowError(resource.exception))
                    }
                }
            }
        }
    }

    override fun onActionTrigger(action: ViewAction?) {
        when (action) {
            is HomeContract.HomeAction.SelectCategory -> {
                sendEvent(HomeContract.HomeEvent.NavigateToCategory(action.categoryTitle))
            }
        }
    }

    override fun clearState() {
        setState(HomeContract.HomeState.initial())
    }
}