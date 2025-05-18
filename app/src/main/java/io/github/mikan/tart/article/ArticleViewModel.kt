package io.github.mikan.tart.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    articleStoreContainer: ArticleStoreContainer,
    commentsScoreContainer: CommentsStoreContainer,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val itemId: String = savedStateHandle["itemId"]!!
    val articleStore = articleStoreContainer.build(itemId)
    val commentsScore = commentsScoreContainer.build(itemId)
    override fun onCleared() {
        articleStore.dispose()
        commentsScore.dispose()
    }
}
