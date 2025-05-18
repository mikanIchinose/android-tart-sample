package io.github.mikan.tart.article

import io.github.mikan.tart.core.network.model.Comment
import io.github.mikan.tart.core.network.model.Item
import io.yumemi.tart.compose.ViewStore
import io.yumemi.tart.core.Action
import io.yumemi.tart.core.Event
import io.yumemi.tart.core.State

typealias ArticleViewStore = ViewStore<ArticleState, ArticleAction, ArticleEvent>

sealed interface ArticleState : State {
    data object Idle : ArticleState
    data object Loading : ArticleState
    data class Error(val message: String) : ArticleState
    data class Success(
        val detail: ArticleDetail,
        val isLiked: Boolean,
        val isStocked: Boolean,
    ) : ArticleState
}

data class ArticleDetail(
    val id: String,
    val author: Author,
    val title: String,
    val body: String,
    val likesCount: Int,
    val commentsCount: Int,
    val stocksCount: Int,
)

data class Author(
    val photoUrl: String,
    val name: String?,
    val group: String?,
)

data class CommentUiModel(
    val id: String,
    val body: String,
    val user: Author,
)

fun Item.toArticleDetail(): ArticleDetail =
    ArticleDetail(
        id = id,
        author = Author(
            photoUrl = user.profileImageUrl,
            name = user.name,
            group = user.organization,
        ),
        title = title,
        body = body,
        likesCount = likesCount,
        commentsCount = commentsCount,
        stocksCount = stocksCount,
    )

fun Comment.toCommentUiModel(): CommentUiModel =
    CommentUiModel(
        id = id,
        user = Author(
            photoUrl = user.profileImageUrl,
            name = user.name,
            group = user.organization,
        ),
        body = body,
    )

sealed interface ArticleAction : Action

sealed interface ArticleUiAction : ArticleAction {
    data object ClickBack : ArticleUiAction
}

sealed interface ArticleEvent : Event {
    data object NavigateBack : ArticleEvent
}
