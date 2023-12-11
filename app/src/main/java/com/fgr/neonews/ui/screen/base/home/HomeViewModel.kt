package com.fgr.neonews.ui.screen.base.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.neonews.UiState
import com.fgr.neonews.data.listener.ApiListener
import com.fgr.neonews.data.response.ArticlesItem
import com.fgr.neonews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    // 1
    private val _localNewsState = MutableStateFlow<UiState<List<ArticlesItem>>>(UiState.Empty)
    val localNewsState get() = _localNewsState

    // 2
    private val _aboardNewsState = MutableStateFlow<UiState<List<ArticlesItem>>>(UiState.Empty)
    val aboardNewsState
        get() = _aboardNewsState

    // 3
    private val _palestineNewsState = MutableStateFlow<UiState<List<ArticlesItem>>>(UiState.Empty)
    val palestineNewsState
        get() = _palestineNewsState

    // 4
    private val _sportNewsState = MutableStateFlow<UiState<List<ArticlesItem>>>(UiState.Empty)
    val sportNewsState
        get() = _sportNewsState

    init {
        viewModelScope.launch {
            val localNewsDeferred = async { getLocalNews() }
            val aboardNewsDeferred = async { getAboardNews() }
            val palestineNewsDeferred = async { getPalestineNews() }
            val sportNewsDeferred = async { getSportNews() }

            // Wait for all async tasks to complete
            localNewsDeferred.await()
            aboardNewsDeferred.await()
            palestineNewsDeferred.await()
            sportNewsDeferred.await()
        }
    }

    suspend fun getLocalNews() {
        _localNewsState.tryEmit(UiState.Loading)
        newsRepository.getLocalNews(
            apiListener = object : ApiListener<List<ArticlesItem>> {
                override fun onSuccess(data: List<ArticlesItem>) {
                    _localNewsState.tryEmit(UiState.Success(data))
                }

                override fun onFailure(message: String) {
                    _localNewsState.tryEmit(UiState.Error(message))
                }

            }
        )
    }
    suspend fun getAboardNews() {
        _aboardNewsState.tryEmit(UiState.Loading)
        newsRepository.getAbroadNews(
            apiListener = object : ApiListener<List<ArticlesItem>> {
                override fun onSuccess(data: List<ArticlesItem>) {
                    _aboardNewsState.tryEmit(UiState.Success(data))
                }

                override fun onFailure(message: String) {
                    _aboardNewsState.tryEmit(UiState.Error(message))
                }

            }
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getPalestineNews() {
        _palestineNewsState.tryEmit(UiState.Loading)
        newsRepository.getPalestineNews(
            apiListener = object : ApiListener<List<ArticlesItem>> {
                override fun onSuccess(data: List<ArticlesItem>) {
                    _palestineNewsState.tryEmit(UiState.Success(data))
                }

                override fun onFailure(message: String) {
                    _palestineNewsState.tryEmit(UiState.Error(message))
                }

            }
        )
    }
    suspend fun getSportNews() {
        _sportNewsState.tryEmit(UiState.Loading)
        newsRepository.getSportNews(
            apiListener = object : ApiListener<List<ArticlesItem>> {
                override fun onSuccess(data: List<ArticlesItem>) {
                    _sportNewsState.tryEmit(UiState.Success(data))
                }

                override fun onFailure(message: String) {
                    _sportNewsState.tryEmit(UiState.Error(message))
                }

            }
        )
    }
}