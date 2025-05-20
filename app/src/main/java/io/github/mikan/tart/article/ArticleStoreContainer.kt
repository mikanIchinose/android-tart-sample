package io.github.mikan.tart.article

import io.github.mikan.tart.domain.ArticleRepository
import io.yumemi.tart.core.Store
import javax.inject.Inject

class ArticleStoreContainer @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    fun build(itemId: String): Store<ArticleState, ArticleAction, ArticleEvent> =
        Store(ArticleState.Idle) {
            state<ArticleState.Idle> {
                enter {
                    nextState(ArticleState.Loading)
                }
            }
            state<ArticleState.Loading> {
                enter {
                    val articleDetail =
                        articleRepository.getArticle(itemId)?.toArticleDetail()
                    val isLiked = articleRepository.isItemLiked(itemId)
                    val isStocked = articleRepository.isItemStock(itemId)
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
            state<ArticleState> {
                error<Exception> {
                    nextState(ArticleState.Error(error.message ?: "Unknown error"))
                }
            }
        }
}