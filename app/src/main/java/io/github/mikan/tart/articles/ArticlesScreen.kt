package io.github.mikan.tart.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.github.mikan.tart.ui.PreviewContainer
import kotlinx.serialization.Serializable

@Serializable
object ArticlesRoute

@Composable
fun ArticlesScreen(
    viewStore: ArticlesViewStore
) {
    when (val state = viewStore.state) {
        ArticlesState.Idle -> {}
        is ArticlesState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is ArticlesState.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = state.message)
            }
        }

        is ArticlesState.Success -> {
            // Show articles list
            ArticleList(
                articles = state.articles,
                onUiAction = { viewStore.dispatch(it) },
            )
        }
    }
}

@Composable
private fun ArticleList(
    articles: List<Article>,
    onUiAction: (ArticlesUiAction) -> Unit,
) {
    Scaffold { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 12.dp),
            contentPadding = innerPadding,
        ) {
            item { Spacer(Modifier.height(4.dp)) }
            items(articles, { it.id }) { article ->
                ArticleItem(
                    article = article,
                    onClickItem = { onUiAction(ArticlesUiAction.Click(article.id)) },
                    onAddLike = { onUiAction(ArticlesUiAction.AddLike(article.id)) },
                    onRemoveLike = { onUiAction(ArticlesUiAction.RemoveLike(article.id)) },
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ArticleItem(
    article: Article,
    onClickItem: () -> Unit,
    onAddLike: () -> Unit,
    onRemoveLike: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClickItem,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = article.author.photoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(8.dp))
                AuthorText(
                    author = article.author,
                    modifier = Modifier.weight(1f)
                )
                if (article.isLike) {
                    IconButton(
                        onClick = onRemoveLike,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                        )
                    }
                } else {
                    IconButton(
                        onClick = onAddLike,
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                        )
                    }
                }
            }
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                article.tags.forEach {
                    Text(
                        text = it,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(horizontal = 4.dp)
                    )
                }
            }
            Text(
                text = "最終更新日: ${article.updatedAt}",
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = "投稿日: ${article.createdAt}",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
fun AuthorText(
    author: Author,
    modifier: Modifier = Modifier,
) {
    val text = when (author.name.isNullOrBlank()) {
        false if (!author.group.isNullOrBlank()) -> "${author.name} in ${author.group}"
        false -> author.name
        true -> null
    }
    if (text != null) {
        Text(
            text = text,
            modifier = modifier,
        )
    } else {
        Box(modifier)
    }
}

// preview
@Preview
@Composable
private fun ArticleItemPreview(
    @PreviewParameter(ArticleParameterProvider::class)
    article: Article,
) = PreviewContainer(Modifier
    .background(MaterialTheme.colorScheme.surface)
    .padding(16.dp)) {
    ArticleItem(
        article = article,
        onClickItem = {},
        onAddLike = {},
        onRemoveLike = {},
    )
}
