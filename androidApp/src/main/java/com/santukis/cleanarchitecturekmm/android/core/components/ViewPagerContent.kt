package com.santukis.cleanarchitecturekmm.android.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.viewpager.widget.PagerAdapter
import com.santukis.entities.movies.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
@OptIn(ExperimentalSnapperApi::class)
fun <Item> ViewPagerContent(
    modifier: Modifier,
    items: List<Item>,
    pagingIndicator: @Composable LazyItemScope.(Boolean) -> Unit,
    content: @Composable LazyItemScope.(Item) -> Unit,
    orientation: ViewPagerOrientation = ViewPagerOrientation.Horizontal
) {
    val listState = rememberLazyListState()

    Box(
        modifier = modifier,
    ) {
        LazyRow(
            state = listState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState = listState),
        ) {
            items(items.size) { index ->
                content(items[index])
            }
        }

        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
        ) {
            items(items.size) { index ->
                pagingIndicator(listState.firstVisibleItemIndex == index)
            }
        }
    }
}

sealed class ViewPagerOrientation {
    object Vertical: ViewPagerOrientation()
    object Horizontal: ViewPagerOrientation()
}

sealed class PagingIndicatorAlignment {
    sealed class Vertical: PagingIndicatorAlignment() {
        object Top: Vertical()
        object Center: Vertical()
        object Bottom: Vertical()
    }

    sealed class Horizontal: PagingIndicatorAlignment() {
        object Start: Horizontal()
        object Center: Horizontal()
        object End: Horizontal()
    }
}