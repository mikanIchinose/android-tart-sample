package io.github.mikan.tart.article

import io.github.mikan.tart.domain.ArticleRepository
import io.yumemi.tart.core.Store
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ArticleStoreContainer @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun build(itemId: String): Store<ArticleState, ArticleAction, ArticleEvent> =
        Store(ArticleState.Idle) {
            coroutineContext(dispatcher)

            state<ArticleState.Idle> {
                enter {
                    nextState(ArticleState.Loading)
                }
            }

            state<ArticleState.Loading> {
                enter {
                    val article = articleRepository.getArticle(itemId)
                    if (article != null) {
                        val isLiked = articleRepository.isItemLiked(itemId)
                        val isStocked = articleRepository.isItemStock(itemId)
                        nextState(
                            ArticleState.Success(
                                detail = article.toArticleDetail(),
                                isLiked = isLiked,
                                isStocked = isStocked
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

            // グローバルエラーハンドラー
            state<ArticleState> {
                error<Exception> {
                    nextState(ArticleState.Error(error.message ?: "Unknown error"))
                }
            }
        }
}