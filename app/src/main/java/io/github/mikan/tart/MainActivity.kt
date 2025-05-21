package io.github.mikan.tart

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import dagger.hilt.android.AndroidEntryPoint
import io.github.mikan.tart.article.ArticleEvent
import io.github.mikan.tart.article.ArticleRoute
import io.github.mikan.tart.article.ArticleScreen
import io.github.mikan.tart.article.ArticleViewModelFactory
import io.github.mikan.tart.articles.ArticlesEvent
import io.github.mikan.tart.articles.ArticlesRoute
import io.github.mikan.tart.articles.ArticlesScreen
import io.github.mikan.tart.articles.ArticlesViewModel
import io.github.mikan.tart.ui.theme.TartTheme
import io.yumemi.tart.compose.rememberViewStore

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        setContent {
            TartTheme {
                val backStack = rememberNavBackStack(ArticlesRoute)
                NavDisplay(
                    entryDecorators = listOf(
                        rememberSceneSetupNavEntryDecorator(),
                        rememberSavedStateNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator(),
                    ),
                    backStack = backStack,
                    entryProvider = entryProvider {
                        entry<ArticlesRoute> {
                            val viewModel = hiltViewModel<ArticlesViewModel>()
                            val viewStore = rememberViewStore(viewModel.store)
                            viewStore.handle<ArticlesEvent.NavigateToDetail> {
                                backStack.add(ArticleRoute(it.itemId))
                            }
                            ArticlesScreen(
                                viewStore = viewStore,
                            )
                        }
                        entry<ArticleRoute> {
                            val viewModel = hiltViewModel { factory: ArticleViewModelFactory ->
                                factory.create(it.itemId)
                            }
                            val articleViewStore = rememberViewStore(viewModel.articleStore)
                            val commentsViewStore =
                                rememberViewStore(viewModel.commentsScore)
                            articleViewStore.handle<ArticleEvent.NavigateBack> {
                                backStack.removeLastOrNull()
                            }
                            ArticleScreen(
                                articleViewStore = articleViewStore,
                                commentsViewStore = commentsViewStore,
                            )
                        }
                    }
                )
            }
        }
    }
}

