package io.github.mikan.tart.article

import io.github.mikan.tart.core.network.model.Comment
import io.yumemi.tart.compose.ViewStore
import io.yumemi.tart.core.Action
import io.yumemi.tart.core.Event
import io.yumemi.tart.core.State

typealias CommentsViewStore = ViewStore<CommentsState, CommentsAction, CommentsEvent>

sealed interface CommentsState : State {
    data object Idle : CommentsState
    data object Loading : CommentsState
    data class Error(val message: String) : CommentsState
    data class Success(
        val comments: List<CommentState>,
    ) : CommentsState
}

data class CommentState(
    val id: String,
    val body: String,
)

fun Comment.toState() = CommentState(
    id = id,
    body = body,
)

sealed interface CommentsAction : Action
sealed interface CommentsEvent : Event