package com.fgr.neonews.hilt.module

import android.app.Application
import com.fgr.neonews.BuildConfig
import com.fgr.neonews.hilt.qualifier.ApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TokenModule {
    @Provides
    @ViewModelScoped
    @ApiKey
    fun provideApiKey(appContext: Application): String {
        return BuildConfig.NEWS_API_KEY
    }
}