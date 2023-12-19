package com.fgr.neonews.ui.screen.detail_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.neonews.UiState
import com.fgr.neonews.data.room.table.NewsTable
import com.fgr.neonews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNewsViewModel
@Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _isFavorite = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
    val isFavorite
        get() = _isFavorite.asStateFlow()

    fun isFavorite(id: Long) {
        _isFavorite.tryEmit(UiState.Loading)
        viewModelScope.launch {
            try {
                _isFavorite.tryEmit(UiState.Success(newsRepository.isFavorite(id)))
            } catch (e: Exception) {
                _isFavorite.tryEmit(UiState.Error(e.message.toString()))
            }
        }
    }

    fun insertFavorite(
        news: NewsTable,
        callback: (success: Boolean, message: String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                newsRepository.insertFavorite(news).also {
                    callback(true, "Berhasil menambahkan ke favorite")
                }
            } catch (e: Exception) {
                callback(false, e.message.toString())
            }
        }
    }

    fun deleteFavorite(
        id: Long,
        callback: (success: Boolean, message: String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                newsRepository.deleteFavoriteById(id).also {
                    callback(true, "Berhasil menghapus dari favorite")
                }
            } catch (e: Exception) {
                callback(false, e.message.toString())
            }
        }
    }
}