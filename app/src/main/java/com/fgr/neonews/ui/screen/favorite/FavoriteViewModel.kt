package com.fgr.neonews.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.fgr.neonews.UiState
import com.fgr.neonews.data.room.table.NewsTable
import com.fgr.neonews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel
@Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _favoriteNewsState = MutableStateFlow<UiState<List<NewsTable>>>(UiState.Empty)
    val favoriteNewsState: StateFlow<UiState<List<NewsTable>>>
        get() = _favoriteNewsState

    init {
        getFavoriteNews()
    }

    fun getFavoriteNews() {
        _favoriteNewsState.tryEmit(UiState.Loading)
        try {
            newsRepository.getAllFavorite().observeForever {
                _favoriteNewsState.tryEmit(UiState.Success(it))
            }
        } catch (e: Exception) {
            _favoriteNewsState.tryEmit(UiState.Error(e.message.toString()))
        }
    }
}