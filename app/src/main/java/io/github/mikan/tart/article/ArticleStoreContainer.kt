package io.github.mikan.tart.article

import io.github.mikan.tart.domain.ArticleRepository
import io.yumemi.tart.core.Store
import javax.inject.Inject

class ArticleStoreContainer @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    fun build(itemId: String): Store<ArticleState, ArticleAction, ArticleEvent> = TODO()
}