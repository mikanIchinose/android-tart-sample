package io.github.mikan.tart

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.mikan.tart.article.ArticleEvent
import io.github.mikan.tart.article.ArticleRoute
import io.github.mikan.tart.article.ArticleScreen
import io.github.mikan.tart.article.ArticleViewModel
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
                val navController = rememberNavController()
                NavHost(navController, ArticlesRoute) {
                    composable<ArticlesRoute> {
                        val viewModel: ArticlesViewModel = hiltViewModel()
                        val viewStore = rememberViewStore(viewModel.store)
                        viewStore.handle<ArticlesEvent.NavigateToDetail> {
                            navController.navigate(ArticleRoute(it.itemId))
                        }
                        ArticlesScreen(
                            viewStore = viewStore,
                        )
                    }
                    composable<ArticleRoute> { backStackEntry ->
                        val viewModel: ArticleViewModel = hiltViewModel()
                        val articleViewStore = rememberViewStore(viewModel.articleStore)
                        val commentsViewStore = rememberViewStore(viewModel.commentsScore)
                        articleViewStore.handle<ArticleEvent.NavigateBack> {
                            navController.popBackStack()
                        }
                        ArticleScreen(
                            articleViewStore = articleViewStore,
                            commentsViewStore = commentsViewStore,
                        )
                    }
                }
            }
        }
    }
}

