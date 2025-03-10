package com.example.ramadanapp.common.di

import com.example.ramadanapp.common.data.repository.remote.RamadanApiService
import com.example.ramadanapp.common.data.repository.remote.RetrofitNetworkProvider
import com.example.ramadanapp.common.data.repository.remote.call_factory.RamadanAppCallAdapterFactory
import com.example.ramadanapp.common.data.repository.remote.converter.ExceptionConverter
import com.example.ramadanapp.common.data.repository.remote.converter.IExceptionConverter
import com.example.ramadanapp.common.domain.repository.remote.INetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitNetwork(apiService: RamadanApiService): INetworkProvider {
        return RetrofitNetworkProvider(apiService)
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory,
        ramadanAppCallAdapterFactory: RamadanAppCallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(ramadanAppCallAdapterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesRamadanApiService(retrofit: Retrofit): RamadanApiService =
        retrofit.create(RamadanApiService::class.java)

    @Provides
    @Singleton
    fun provideCallAdapter(exceptionConverter: IExceptionConverter): RamadanAppCallAdapterFactory {
        return RamadanAppCallAdapterFactory.create(exceptionConverter)
    }

    @Provides
    @Singleton
    fun provideExceptionConverter(): IExceptionConverter {
        return ExceptionConverter()
    }

}