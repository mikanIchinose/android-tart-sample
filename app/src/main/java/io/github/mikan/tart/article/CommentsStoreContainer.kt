package io.github.mikan.tart.article

import io.github.mikan.tart.domain.ArticleRepository
import io.yumemi.tart.core.Store
import javax.inject.Inject

class CommentsStoreContainer @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    fun build(itemId: String): Store<CommentsState, CommentsAction, CommentsEvent> =
        Store(CommentsState.Idle) {
            state<CommentsState.Idle> {
                enter {
                    nextState(CommentsState.Loading)
                }
            }
            state<CommentsState.Loading> {
                enter {
                    val comments = articleRepository.getComments(itemId)
                        .map { it.toState() }
                        .reversed()
                    nextState(CommentsState.Success(comments))
                }
            }
            state<CommentsState> {
                error<Exception> {
                    nextState(CommentsState.Error(error.message ?: "Unknown error"))
                }
            }
        }
}
