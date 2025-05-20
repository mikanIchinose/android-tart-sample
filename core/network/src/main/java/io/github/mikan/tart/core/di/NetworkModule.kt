package io.github.mikan.tart.core.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mikan.tart.core.network.BuildConfig
import io.github.mikan.tart.core.network.infrastructure.ApiClient
import io.github.mikan.tart.core.network.remote.TeamApi
import io.github.mikan.tart.core.network.remote.UserApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideTeamApi(
        apiClient: ApiClient
    ): TeamApi = apiClient.createService(TeamApi::class.java)

    @Singleton
    @Provides
    fun provideUserApi(
        apiClient: ApiClient
    ): UserApi = apiClient.createService(UserApi::class.java)

    @Singleton
    @Provides
    fun provideApiClient(): ApiClient =
        ApiClient(
            authName = "Bearer",
            bearerToken = BuildConfig.QiitaAccessToken,
        )
            .setLogger { Log.d("ApiClient", it) }
}
