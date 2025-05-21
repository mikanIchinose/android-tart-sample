package io.github.mikan.tart.article

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = ArticleViewModelFactory::class)
class ArticleViewModel @AssistedInject constructor(
    articleStoreContainer: ArticleStoreContainer,
    commentsScoreContainer: CommentsStoreContainer,
    @Assisted itemId: String,
) : ViewModel() {
    val articleStore by lazy { articleStoreContainer.build(itemId) }
    val commentsScore by lazy { commentsScoreContainer.build(itemId) }

    override fun onCleared() {
        articleStore.dispose()
        commentsScore.dispose()
    }
}

@AssistedFactory
interface ArticleViewModelFactory {
    fun create(itemId: String): ArticleViewModel
}