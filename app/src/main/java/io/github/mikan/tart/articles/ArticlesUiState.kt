package io.github.mikan.tart.articles

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import io.github.mikan.tart.core.network.model.Item
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

sealed interface ArticlesUiState {
    data object Loading : ArticlesUiState
    data class Error(val message: String) : ArticlesUiState
    data class Success(
        val articles: List<Article>,
        val isLoading: Boolean = false,
    ) : ArticlesUiState
}

data class Article(
    val id: String,
    val author: Author,
    val title: String,
    val likesCount: Int,
    val tags: List<String>,
    val createdAt: String,
    val updatedAt: String,
)

data class Author(
    val photoUrl: String,
    val name: String?,
    val group: String?,
)

fun Item.toArticle(): Article {
    return Article(
        id = id,
        author = Author(
            photoUrl = user.profileImageUrl,
            name = user.name,
            group = user.organization,
        ),
        title = title,
        likesCount = likesCount,
        tags = tags.map { it.name },
        createdAt = createdAt.formatDate(),
        updatedAt = updatedAt.formatDate(),
    )
}

private fun String.formatDate(): String {
    val instant = Instant.parse(this)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    return localDateTime.toJavaLocalDateTime().format(formatter)
}

class ArticleParameterProvider : CollectionPreviewParameterProvider<Article>(
    listOf(
        Article(
            id = "1",
            author = Author(
                photoUrl = "",
                name = null,
                group = null,
            ),
            title = "タイトル",
            likesCount = 0,
            tags = emptyList(),
            createdAt = "2025/01/01",
            updatedAt = "2025/01/10",
        ),
        Article(
            id = "1",
            author = Author(
                photoUrl = "",
                name = "山田太郎",
                group = "fuga株式会社",
            ),
            title = "タイトル",
            likesCount = 100,
            tags = listOf(
                "android",
                "compose"
            ),
            createdAt = "2025/01/01",
            updatedAt = "2025/01/10",
        ),
        Article(
            id = "1",
            author = Author(
                photoUrl = "",
                name = "山田太郎",
                group = "fuga株式会社",
            ),
            title = List(100) { "hoge" }.joinToString(""),
            likesCount = 10000,
            tags = listOf(
                "android",
                "compose",
                "vscode",
                "mcp",
                "hilt",
                "DevOps",
            ),
            createdAt = "2025/01/01",
            updatedAt = "2025/01/10",
        ),
    ),
)
