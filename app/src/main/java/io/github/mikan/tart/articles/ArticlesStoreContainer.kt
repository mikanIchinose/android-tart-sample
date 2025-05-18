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
            state<ArticlesState> {
                error<Exception> {
                    nextState(ArticlesState.Error(error.message ?: "Unknown error"))
                }
            }
            state<ArticlesState.Idle> {
                enter {
                    nextState(ArticlesState.Loading)
                    val articles = articleRepository.getArticles().map { it.toArticle() }
                    nextState(ArticlesState.Success(articles))
                }
            }
            state<ArticlesState.Success> {
                action<ArticlesUiAction.Click> {
                    event(ArticlesEvent.NavigateToDetail(action.itemId))
                }
                action<ArticlesUiAction.AddLike> {
                    articleRepository.addLike(action.itemId)
                    val articles = articleRepository.getArticles().map { it.toArticle() }
                    nextState(state.copy(articles))
                }
                action<ArticlesUiAction.RemoveLike> {
                    articleRepository.removeLike(action.itemId)
                    val articles = articleRepository.getArticles().map { it.toArticle() }
                    nextState(state.copy(articles))
                }
            }
        }
}
