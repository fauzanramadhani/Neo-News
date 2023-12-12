package com.fgr.neonews.ui.screen.more_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.neonews.NewsCategories
import com.fgr.neonews.UiState
import com.fgr.neonews.data.listener.CallbackListener
import com.fgr.neonews.data.response.ArticlesItem
import com.fgr.neonews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreNewsViewModel
@Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _newsState = MutableStateFlow<UiState<List<ArticlesItem>>>(UiState.Empty)
    val newsState
        get() = _newsState

    fun getNews(
        newsCategories: String
    ) {
        _newsState.tryEmit(UiState.Loading)
        viewModelScope.launch {
            when (newsCategories) {
                NewsCategories.Abroad -> {
                    newsRepository.getAbroadNews(
                        pageSize = 20,
                        callbackListener = object : CallbackListener<List<ArticlesItem>> {
                            override fun onSuccess(data: List<ArticlesItem>) {
                                _newsState.tryEmit(UiState.Success(data))
                            }

                            override fun onFailure(message: String) {
                                _newsState.tryEmit(UiState.Error(message))
                            }
                        }
                    )
                }

                NewsCategories.PalestineWar -> {
                    newsRepository.getPalestineNews(
                        pageSize = 20,
                        callbackListener = object : CallbackListener<List<ArticlesItem>> {
                            override fun onSuccess(data: List<ArticlesItem>) {
                                _newsState.tryEmit(UiState.Success(data))
                            }

                            override fun onFailure(message: String) {
                                _newsState.tryEmit(UiState.Error(message))
                            }

                        }
                    )
                }

                NewsCategories.Sport -> {
                    newsRepository.getSportNews(
                        pageSize = 20,
                        callbackListener = object : CallbackListener<List<ArticlesItem>> {
                            override fun onSuccess(data: List<ArticlesItem>) {
                                _newsState.tryEmit(UiState.Success(data))
                            }

                            override fun onFailure(message: String) {
                                _newsState.tryEmit(UiState.Error(message))
                            }

                        }
                    )
                }

                else -> {
                    newsRepository.getLocalNews(
                        pageSize = 20,
                        callbackListener = object : CallbackListener<List<ArticlesItem>> {
                            override fun onSuccess(data: List<ArticlesItem>) {
                                _newsState.tryEmit(UiState.Success(data))
                            }

                            override fun onFailure(message: String) {
                                _newsState.tryEmit(UiState.Error(message))
                            }

                        }
                    )
                }
            }
        }
    }
}