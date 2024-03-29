package com.santukis.widgets

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun LazyListState.OnEndReached(
    threshold: Int = 3,
    onEndReached : (Int) -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - threshold
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .collect { reached ->
                if (reached) {
                    onEndReached(layoutInfo.totalItemsCount)
                }
            }
    }
}

@Composable
fun LazyListState.ScrollListener(
    onScrollStateChanged : (Boolean) -> Unit
) {
    val isScrolling = remember {
        derivedStateOf {
            isScrollInProgress
        }
    }

    LaunchedEffect(isScrolling) {
        snapshotFlow { isScrolling.value }
            .distinctUntilChanged()
            .collect { isScrolling ->
                onScrollStateChanged(isScrolling)
            }
    }
}