package io.github.mikan.tart.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mikan.tart.domain.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ArticlesUiState>(ArticlesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getArticles() {
        viewModelScope.launch {
            try {
                val articles = articleRepository.getArticles().map { it.toArticle() }
                _uiState.value = ArticlesUiState.Success(articles)
            } catch (e: Exception) {
                _uiState.value = ArticlesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}