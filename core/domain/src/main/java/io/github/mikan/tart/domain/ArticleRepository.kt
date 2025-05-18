package io.github.mikan.tart.domain

import io.github.mikan.tart.core.network.model.Comment
import io.github.mikan.tart.core.network.model.Item

interface ArticleRepository {
    suspend fun getArticles(): List<Item>
    suspend fun getArticle(itemId: String): Item?
    suspend fun addLike(itemId: String)
    suspend fun removeLike(itemId: String)
    suspend fun isItemStock(itemId: String): Boolean
    suspend fun isItemLiked(itemId: String): Boolean
    suspend fun getComments(itemId: String): List<Comment>
}
