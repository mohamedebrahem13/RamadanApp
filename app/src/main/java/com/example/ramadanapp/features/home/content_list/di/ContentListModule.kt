package com.example.ramadanapp.features.home.content_list.di

import com.example.ramadanapp.common.domain.repository.remote.INetworkProvider
import com.example.ramadanapp.features.home.content_list.data.repository.ContentListRepository
import com.example.ramadanapp.features.home.content_list.data.repository.remote.ContentListRemoteDS
import com.example.ramadanapp.features.home.content_list.domain.intractor.GetPlaylistUseCase
import com.example.ramadanapp.features.home.content_list.domain.repository.IContentListRepository
import com.example.ramadanapp.features.home.content_list.domain.repository.remote.IContentListRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ContentListModule {
    @Provides
    fun provideGetPlaylistUseCase(repository: IContentListRepository): GetPlaylistUseCase {
        return GetPlaylistUseCase(repository)
    }
    @Provides
    fun provideContentListRepository(
        remoteDS: IContentListRemoteDS
    ): IContentListRepository {
        return ContentListRepository(remoteDS)
    }
    @Provides
    fun provideContentListRemoteDS(
        networkProvider: INetworkProvider
    ): IContentListRemoteDS {
        return ContentListRemoteDS(networkProvider)
    }
}