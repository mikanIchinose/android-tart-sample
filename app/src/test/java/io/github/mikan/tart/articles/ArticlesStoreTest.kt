package io.github.mikan.tart.articles

import io.github.mikan.tart.domain.FakeArticleRepository
import io.yumemi.tart.core.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ArticlesStoreTest {
    val testDispatcher = UnconfinedTestDispatcher()
    lateinit var repository: FakeArticleRepository
    lateinit var store: Store<ArticlesState, ArticlesAction, ArticlesEvent>

    @Test
    fun articlesStore_loadArticles() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository()
        store = ArticlesStoreContainer(repository, Dispatchers.Unconfined).build()
        val expected = ArticlesState.Success(
            articles = listOf(
                Article(
                    id = "1",
                    author = Author(
                        photoUrl = "",
                        name = "山田太郎",
                        group = "fuga株式会社",
                    ),
                    title = "title",
                    likesCount = 1,
                    tags = emptyList(),
                    createdAt = "2025/01/01",
                    updatedAt = "2025/01/01",
                ),
                Article(
                    id = "2",
                    author = Author(
                        photoUrl = "",
                        name = "山田太郎",
                        group = "fuga株式会社",
                    ),
                    title = "title",
                    likesCount = 1,
                    tags = emptyList(),
                    createdAt = "2025/01/01",
                    updatedAt = "2025/01/01",
                ),
                Article(
                    id = "3",
                    author = Author(
                        photoUrl = "",
                        name = "山田太郎",
                        group = "fuga株式会社",
                    ),
                    title = "title",
                    likesCount = 1,
                    tags = emptyList(),
                    createdAt = "2025/01/01",
                    updatedAt = "2025/01/01",
                ),
            )
        )
        // Act
        store.state // trigger initial state
        // NOTE: ↑ こういう暗黙的知見があるからMVIフレームワークには依存したくないよね
        // Assert
        assertEquals(expected, store.currentState)
    }

    @Test
    fun articlesStore_clickArticle() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository()
        store = ArticlesStoreContainer(repository, Dispatchers.Unconfined).build()
        var capturedEvent: ArticlesEvent? = null
        store.collectEvent {
            capturedEvent = it
        }
        // Act
        store.dispatch(ArticlesUiAction.Click("1"))
        // Assert
        assertEquals(ArticlesEvent.NavigateToDetail("1"), capturedEvent)
    }

    @Test
    fun articlesStore_addLike() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository()
        store = ArticlesStoreContainer(repository, Dispatchers.Unconfined).build()
        // Act
        store.dispatch(ArticlesUiAction.AddLike("1"))
        // Assert
        assertEquals(
            2,
            (store.currentState as ArticlesState.Success).articles.first { it.id == "1" }.likesCount
        )
    }

    @Test
    fun articlesStore_removeLike() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository()
        store = ArticlesStoreContainer(repository, Dispatchers.Unconfined).build()
        // Act
        store.dispatch(ArticlesUiAction.RemoveLike("1"))
        // Assert
        assertEquals(
            0,
            (store.currentState as ArticlesState.Success).articles.first { it.id == "1" }.likesCount
        )
    }

    @Test
    fun articlesStore_loadError() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository(false)
        store = ArticlesStoreContainer(repository, Dispatchers.Unconfined).build()
        val expected = ArticlesState.Error("Failed to get articles")
        // Act
        store.state
        // Assert
        assertEquals(expected, store.currentState)
    }
}
