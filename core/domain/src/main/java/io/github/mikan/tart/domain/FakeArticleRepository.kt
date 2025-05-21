package io.github.mikan.tart.domain

import io.github.mikan.tart.core.network.model.Comment
import io.github.mikan.tart.core.network.model.Item
import io.github.mikan.tart.core.network.model.User

class FakeArticleRepository(
    var shouldSucceed: Boolean = true
) : ArticleRepository {
    private val fakeUser = User(
        description = null,
        facebookId = null,
        followeesCount = 0,
        followersCount = 0,
        githubLoginName = null,
        id = "id",
        itemsCount = 1,
        linkedinId = null,
        location = null,
        name = "山田太郎",
        organization = "fuga株式会社",
        permanentId = 0,
        profileImageUrl = "",
        teamOnly = false,
        twitterScreenName = null,
        websiteUrl = null,
    )
    private val articles = mutableListOf(
        Item(
            renderedBody = "renderedBody",
            body = "body",
            coediting = false,
            commentsCount = 0,
            createdAt = "2025-01-01T00:00:00+09:00",
            id = "1",
            likesCount = 1,
            `private` = false,
            reactionsCount = 0,
            stocksCount = 0,
            tags = emptyList(),
            title = "title",
            updatedAt = "2025-01-01T00:00:00+09:00",
            url = "url",
            user = fakeUser,
            pageViewsCount = 0,
            slide = false,
            organizationUrlName = "organizationUrlName",
        ),
        Item(
            renderedBody = "renderedBody",
            body = "body",
            coediting = false,
            commentsCount = 0,
            createdAt = "2025-01-01T00:00:00+09:00",
            id = "2",
            likesCount = 1,
            `private` = false,
            reactionsCount = 0,
            stocksCount = 0,
            tags = emptyList(),
            title = "title",
            updatedAt = "2025-01-01T00:00:00+09:00",
            url = "url",
            user = fakeUser,
            pageViewsCount = 0,
            slide = false,
            organizationUrlName = "organizationUrlName",
        ),
        Item(
            renderedBody = "renderedBody",
            body = "body",
            coediting = false,
            commentsCount = 0,
            createdAt = "2025-01-01T00:00:00+09:00",
            id = "3",
            likesCount = 1,
            `private` = false,
            reactionsCount = 0,
            stocksCount = 0,
            tags = emptyList(),
            title = "title",
            updatedAt = "2025-01-01T00:00:00+09:00",
            url = "url",
            user = fakeUser,
            pageViewsCount = 0,
            slide = false,
            organizationUrlName = "organizationUrlName",
        ),
    )

    override suspend fun getArticles(): List<Item> {
        if (!shouldSucceed) throw Exception("Failed to get articles")
        return articles
    }

    override suspend fun getArticle(itemId: String): Item? {
        if (!shouldSucceed) throw Exception("Failed to get article")
        return articles.find { it.id == itemId }
    }

    override suspend fun addLike(itemId: String) {
        if (!shouldSucceed) throw Exception("Failed to add like")
        val index = articles.indexOfFirst { it.id == itemId }
        val newItem = articles
            .find { it.id == itemId }
            ?.let { it.copy(likesCount = it.likesCount + 1) }
        if (newItem != null) {
            articles[index] = newItem
        }
    }

    override suspend fun removeLike(itemId: String) {
        if (!shouldSucceed) throw Exception("Failed to remove like")
        val index = articles.indexOfFirst { it.id == itemId }
        val newItem = articles
            .find { it.id == itemId }
            ?.let { it.copy(likesCount = it.likesCount - 1) }
        if (newItem != null) {
            articles[index] = newItem
        }
    }

    override suspend fun isItemStock(itemId: String): Boolean {
        if (!shouldSucceed) throw Exception("Failed to check stock")
        return false
    }

    override suspend fun isItemLiked(itemId: String): Boolean {
        if (!shouldSucceed) throw Exception("Failed to check like")
        return false
    }

    override suspend fun getComments(itemId: String): List<Comment> {
        if (!shouldSucceed) throw Exception("Failed to get comments")
        TODO("Not yet implemented")
    }
}
