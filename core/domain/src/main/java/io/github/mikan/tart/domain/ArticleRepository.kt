package io.github.mikan.tart.domain

import io.github.mikan.tart.core.network.model.Item
import io.github.mikan.tart.core.network.remote.UserApi

class ArticleRepository(
    private val userApi: UserApi,
) {
    suspend fun getArticles(): List<Item> {
        val items = userApi.getItems().body().orEmpty()
        return items
    }

    suspend fun isItemStock(itemId: String): Boolean {
        return userApi.isItemStock(itemId).isSuccessful
    }
}