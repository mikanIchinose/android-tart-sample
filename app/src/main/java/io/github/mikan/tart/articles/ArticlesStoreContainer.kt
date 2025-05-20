package io.github.mikan.tart.articles

import io.github.mikan.tart.domain.ArticleRepository
import io.yumemi.tart.core.Store
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ArticlesStoreContainer @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    fun build(): Store<ArticlesState, ArticlesAction, ArticlesEvent> =
        Store(ArticlesState.Idle) {
            coroutineContext(dispatcher)
            state<ArticlesState.Idle> {
                enter {
                    nextState(ArticlesState.Loading)
                }
            }
            state<ArticlesState.Loading> {
                enter {
                    val articles = articleRepository.getArticles().map {
                        val isLike = articleRepository.isItemLiked(it.id)
                        it.toArticle(isLike)
                    }
                    nextState(ArticlesState.Success(articles))
                }
            }
            state<ArticlesState.Success> {
                action<ArticlesUiAction.Click> {
                    event(ArticlesEvent.NavigateToDetail(action.itemId))
                }
                action<ArticlesUiAction.AddLike> {
                    articleRepository.addLike(action.itemId)
                    val newArticles = state.articles.map {
                        if (it.id == action.itemId) {
                            it.copy(
                                likesCount = it.likesCount + 1,
                                isLike = true,
                            )
                        } else {
                            it
                        }
                    }
                    nextState(state.copy(newArticles))
                }
                action<ArticlesUiAction.RemoveLike> {
                    articleRepository.removeLike(action.itemId)
                    val newArticles = state.articles.map {
                        if (it.id == action.itemId) {
                            it.copy(
                                likesCount = it.likesCount - 1,
                                isLike = false,
                            )
                        } else {
                            it
                        }
                    }
                    nextState(state.copy(newArticles))
                }
            }
            // global error handler
            state<ArticlesState> {
                error<Exception> {
                    nextState(ArticlesState.Error(error.message ?: "Unknown error"))
                }
            }
        }
}
