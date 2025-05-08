package io.github.mikan.tart.article

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    articleStoreContainer: ArticleStoreContainer
) : ViewModel() {
    val store = articleStoreContainer.build()
}