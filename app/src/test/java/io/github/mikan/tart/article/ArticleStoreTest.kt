package io.github.mikan.tart.article

import io.github.mikan.tart.domain.FakeArticleRepository
import io.yumemi.tart.core.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleStoreTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: FakeArticleRepository
    private lateinit var store: Store<ArticleState, ArticleAction, ArticleEvent>
    private val testItemId = "1"

    @Test
    fun articleStore_loadArticle() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository()
        store = ArticleStoreContainer(repository, Dispatchers.Unconfined).build(testItemId)

        // Act
        store.state // トリガー初期状態

        // Assert
        val currentState = store.currentState
        val expectedState = ArticleState.Success(
            detail = ArticleDetail(
                id = "1",
                title = "title",
                body = "body",
                likesCount = 1,
                commentsCount = 0,
                stocksCount = 0,
                author = Author(
                    photoUrl = "",
                    name = "山田太郎",
                    group = "fuga株式会社"
                )
            ),
            isLiked = false,
            isStocked = false
        )
        assertEquals(expectedState, currentState)
    }

    @Test
    fun articleStore_clickBack() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository()
        store = ArticleStoreContainer(repository, Dispatchers.Unconfined).build(testItemId)
        var capturedEvent: ArticleEvent? = null
        store.collectEvent {
            capturedEvent = it
        }

        // 初期状態に遷移させる
        store.state

        // Act
        store.dispatch(ArticleUiAction.ClickBack)

        // Assert
        assertEquals(ArticleEvent.NavigateBack, capturedEvent)
    }

    @Test
    fun articleStore_loadError() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository(shouldSucceed = false)
        store = ArticleStoreContainer(repository, Dispatchers.Unconfined).build(testItemId)

        // Act
        store.state // トリガー初期状態

        // Assert
        val currentState = store.currentState
        assertIs<ArticleState.Error>(currentState)
        assertTrue(currentState.message.isNotEmpty())
    }

    @Test
    fun articleStore_articleNotFound() = runTest(testDispatcher) {
        // Arrange
        repository = FakeArticleRepository()
        store = ArticleStoreContainer(repository, Dispatchers.Unconfined).build("non-existent-id")

        // Act
        store.state // トリガー初期状態

        // Assert
        val currentState = store.currentState
        val expected = ArticleState.Error("Article not found")
        assertEquals(expected, currentState)
    }
}
