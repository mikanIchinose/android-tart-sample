package io.github.mikan.tart.domain

import io.github.mikan.tart.core.network.model.Item
import io.github.mikan.tart.core.network.remote.UserApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val userApi: UserApi,
) {
    suspend fun getArticles(): List<Item> {
        val items = userApi.getItems().body().orEmpty()
        return items
    }

    suspend fun getArticle(itemId: String): Item? {
        val item = userApi.getItem(itemId).body()
        return item
    }

    suspend fun addLike(itemId: String) {
        userApi.createItemLike(itemId)
    }

    suspend fun removeLike(itemId: String) {
        userApi.deleteItemLike(itemId)
    }

    suspend fun isItemStock(itemId: String): Boolean {
        return userApi.isItemStock(itemId).isSuccessful
    }
}