package com.fgr.neonews.hilt.module

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.fgr.neonews.data.remote.NewsApi
import com.fgr.neonews.data.retrofit.createRetrofit
import com.fgr.neonews.hilt.qualifier.ApiKey
import com.fgr.neonews.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @ViewModelScoped
    fun provideNewsRepository(
        @ApiKey apiKey: String,
        appContext: Application,
    ) = NewsRepository(
        appContext = appContext,
        newsApi = createRetrofit().create(NewsApi::class.java),
        apiKey = apiKey,
    )
}