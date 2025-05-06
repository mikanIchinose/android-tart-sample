package io.github.mikan.tart.articles

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    articlesStateContainer: ArticlesStoreContainer,
) : ViewModel() {
    val store = articlesStateContainer.build()
}