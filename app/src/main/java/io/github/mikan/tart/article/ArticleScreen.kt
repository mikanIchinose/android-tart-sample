package io.github.mikan.tart.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.github.mikan.tart.R
import io.github.mikan.tart.ui.PreviewContainer
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class ArticleRoute(val itemId: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    articleViewStore: ArticleViewStore,
    commentsViewStore: CommentsViewStore,
) {
    when (val articleState = articleViewStore.state) {
        ArticleState.Idle -> {}
        is ArticleState.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = articleState.message)
            }
        }

        ArticleState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is ArticleState.Success -> {
            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            Scaffold(
                floatingActionButton = {
                    ArticleFab(
                        {
                            scope.launch { sheetState.expand() }
                        },
                        articleState.detail.commentsCount,
                    )
                }
            ) { innerPadding ->
                if (sheetState.currentValue != SheetValue.Hidden) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            scope.launch { sheetState.hide() }
                        },
                        sheetState = sheetState,
                    ) {
                        commentsViewStore.render<CommentsState.Success> {
                            ArticleComments(state.comments)
                        }
                    }
                }
                ArticleDetail(
                    detail = articleState.detail,
                    onUiAction = { articleViewStore.dispatch(it) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun ArticleFab(
    onClick: () -> Unit,
    commentsCount: Int,
) {
    if (commentsCount > 0) {
        FloatingActionButton(onClick) {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = Color.White,
                    ) {
                        Text("$commentsCount")
                    }
                },
            ) {
                Icon(
                    painterResource(R.drawable.ic_chat),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticleDetail(
    detail: ArticleDetail,
    onUiAction: (ArticleUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.padding(horizontal = 16.dp)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Spacer(Modifier.height(48.dp))
            Row {
                AsyncImage(
                    model = null,
                    contentDescription = null,
                )
                Text(
                    text = detail.title,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = detail.body,
            )
        }
        Button({ onUiAction(ArticleUiAction.ClickBack) }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun ArticleComments(
    comments: List<CommentState>,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(comments, { it.id }) {
            Card(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    it.body,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ArticleDetailPreview() = PreviewContainer {
    val detail = ArticleDetail(
        id = "1",
        author = Author(
            photoUrl = "",
            name = "山田太郎",
            group = "fuga株式会社",
        ),
        title = "Jetpack Compose入門：Android UI開発の新しいカタチ",
        body = """
        # Jetpack Compose入門：Android UI開発の新しいカタチ

        Jetpack Composeは、Android向けの最新UIツールキットで、従来のXMLレイアウトに代わり、KotlinコードだけでUIを記述できます。宣言的UIという考え方に基づいており、状態管理とUIの描画をシンプルに統合できます。

        たとえば、`@Composable`アノテーションを使うことで、関数がUI要素を表現できるようになります。以下のようなシンプルなコードでテキスト表示が可能です。

        ```kotlin
        @Composable
        fun Greeting(name: String) {
            Text(text = "Hello, World!")
        }
        ```
        
        また、rememberやmutableStateOfといったステート管理も容易で、ユーザー操作に応じた動的UI更新もスムーズに行えます。Composeは今後のAndroid開発の主流になるとされており、これから学ぶ価値の高い技術です。
        
        他にも「状態管理」「ナビゲーション」「アニメーション」など、Composeの細かいテーマで分けて記事化することも可能です。特定のトピックで書きたい内容はありますか？
        """.trimIndent(),
        likesCount = 10,
        commentsCount = 10,
        stocksCount = 10,
    )
    ArticleDetail(
        detail = detail,
        onUiAction = {},
    )
}

@Preview
@Composable
private fun ArticleCommentsPreview() = PreviewContainer {
    val comments = listOf(
        CommentState(
            id = "1",
            body = "コメント1",
        ),
        CommentState(
            id = "2",
            body = "コメント2",
        ),
    )
    ArticleComments(comments)
}
