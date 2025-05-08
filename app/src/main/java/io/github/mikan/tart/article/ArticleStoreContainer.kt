package io.github.mikan.tart.article

import io.github.mikan.tart.domain.ArticleRepository
import io.yumemi.tart.core.Store
import javax.inject.Inject

class ArticleStoreContainer @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    fun build(): Store<ArticleState, ArticleAction, ArticleEvent> =
        Store(ArticleState.Idle) {
            state<ArticleState.Idle> {
                action<ArticleAction.LoadArticle> {
                    nextState(ArticleState.Loading)
                    val articleDetail =
                        articleRepository.getArticle(action.itemId)?.toArticleDetail()
                    val isLiked = articleRepository.isItemLiked(action.itemId)
                    val isStocked = articleRepository.isItemStock(action.itemId)
                    if (articleDetail != null) {
                        nextState(
                            ArticleState.Success(
                                detail = articleDetail,
                                isLiked = isLiked,
                                isStocked = isStocked,
                            )
                        )
                    } else {
                        nextState(ArticleState.Error("Article not found"))
                    }
                }
            }
            state<ArticleState.Success> {
                action<ArticleUiAction.ClickBack> {
                    event(ArticleEvent.NavigateBack)
                }
            }
        }
}