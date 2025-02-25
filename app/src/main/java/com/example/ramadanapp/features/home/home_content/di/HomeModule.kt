package com.example.ramadanapp.features.home.home_content.di

import com.example.ramadanapp.common.data.repository.local.AppDatabase
import com.example.ramadanapp.common.domain.repository.remote.INetworkProvider
import com.example.ramadanapp.features.home.home_content.data.repository.HomeRepository
import com.example.ramadanapp.features.home.home_content.data.repository.local.HomeLocalDS
import com.example.ramadanapp.features.home.home_content.data.repository.local.RamadanResponseDao
import com.example.ramadanapp.features.home.home_content.data.repository.remote.HomeRemoteDS
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeDataFromLocalUseCase
import com.example.ramadanapp.features.home.home_content.domain.intractor.GetHomeDataFromRemoteUseCase
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository
import com.example.ramadanapp.features.home.home_content.domain.repository.local.IHomeLocalDS
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
    fun provideHomeLocalDS(
        dao: RamadanResponseDao
    ): IHomeLocalDS {
        return HomeLocalDS(dao)
    }


    @Provides
    fun provideHomeRepository(
        remoteDataSource: IHomeRemoteDS,
        localDataSource: IHomeLocalDS

    ): IHomeRepository {
        return HomeRepository(remoteDataSource,localDataSource)
    }
    @Provides
    fun provideGetHomeDataFromRemoteUseCase(
        homeRepository: IHomeRepository
    ): GetHomeDataFromRemoteUseCase {
        return GetHomeDataFromRemoteUseCase(homeRepository)
    }
    @Provides
    fun provideGetHomeDataFromLocalUseCase(
        homeRepository: IHomeRepository
    ): GetHomeDataFromLocalUseCase {
        return GetHomeDataFromLocalUseCase(homeRepository)
    }
    @Provides
    fun provideRamadanResponseDao(database: AppDatabase): RamadanResponseDao {
        return database.ramadanResponseDao()
    }
}
