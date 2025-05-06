package io.github.mikan.tart.articles

import io.github.mikan.tart.domain.ArticleRepository
import io.yumemi.tart.core.Store
import javax.inject.Inject

class ArticlesStoreContainer @Inject constructor(
    private val articleRepository: ArticleRepository,
) {
    fun build(): Store<ArticlesState, ArticlesAction, ArticlesEvent> =
        Store(ArticlesState.Idle) {
            state<ArticlesState.Idle> {
                action<ArticlesAction.LoadArticles> {
                    nextState(ArticlesState.Loading)
                    val articles = articleRepository.getArticles().map { it.toArticle() }
                    nextState(ArticlesState.Success(articles))
                }
            }
            state<ArticlesState.Success> {
                error<Exception> {
                    nextState(ArticlesState.Error(error.message ?: "Unknown error"))
                }
            }
            state<ArticlesState.Success> {
                action<ArticlesUiAction.Click> {
                    event(ArticlesEvent.NavigateToDetail(action.itemId))
                }
                action<ArticlesUiAction.AddLike> {
                    articleRepository.addLike(action.itemId)
                }
                action<ArticlesUiAction.RemoveLike> {
                    articleRepository.removeLike(action.itemId)
                }
            }
        }
}
