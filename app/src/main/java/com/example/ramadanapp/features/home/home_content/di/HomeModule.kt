package com.example.ramadanapp.features.home.home_content.di

import com.example.ramadanapp.common.domain.repository.remote.INetworkProvider
import com.example.ramadanapp.features.home.home_content.data.repository.HomeRepository
import com.example.ramadanapp.features.home.home_content.data.repository.remote.HomeRemoteDS
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeVideosUseCase
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository
import com.example.ramadanapp.features.home.home_content.domain.repository.remote.IHomeRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideHomeRemoteDS(
        networkProvider: INetworkProvider
    ): IHomeRemoteDS {
        return HomeRemoteDS(networkProvider = networkProvider)
    }

    @Provides
    fun provideHomeRepository(
        remoteDataSource: IHomeRemoteDS
    ): IHomeRepository {
        return HomeRepository(remoteDataSource)
    }
    @Provides
    fun provideGetHomeVideosUseCase(
        homeRepository: IHomeRepository
    ): GetHomeVideosUseCase {
        return GetHomeVideosUseCase(homeRepository)
    }
}
