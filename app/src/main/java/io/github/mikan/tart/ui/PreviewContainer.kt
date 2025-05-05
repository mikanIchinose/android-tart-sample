package io.github.mikan.tart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import io.github.mikan.tart.ui.theme.TartTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PreviewContainer(
    content: @Composable () -> Unit = {},
) {
    val previewHandler = AsyncImagePreviewHandler {
        ColorImage(Color.Red.toArgb())
    }
    TartTheme {
        Surface(
            Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
        ) {
            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
                content()
            }
        }
    }
}

