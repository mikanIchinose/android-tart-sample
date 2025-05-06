package io.github.mikan.tart

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.mikan.tart.articles.ArticlesAction
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
                val viewModel: ArticlesViewModel = viewModel()
                val viewStore = rememberViewStore(viewModel.store)
                LaunchedEffect(Unit) {
                    viewStore.dispatch(ArticlesAction.LoadArticles)
                }
                ArticlesScreen(
                    state = viewStore.state,
                    onUiAction = { viewStore.dispatch(it) },
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TartTheme {
        Greeting("Android")
    }
}