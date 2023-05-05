package com.santukis.home.stateholders

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.delay

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun rememberNowPlayingContentState(
    listState: LazyListState = rememberLazyListState(),
    flingBehavior: FlingBehavior = rememberSnapperFlingBehavior(lazyListState = listState),
    dotColorHolder: DotColorHolder = rememberDotColorState(),
    listSize: Int = 0
) = remember(
    listState,
    flingBehavior,
    dotColorHolder,
    listSize
) {
    NowPlayingContentHolder(
        listState = listState,
        flingBehavior = flingBehavior,
        dotColorHolder = dotColorHolder,
        listSize = listSize
    )
}

class NowPlayingContentHolder(
    val listState: LazyListState,
    val flingBehavior: FlingBehavior,
    private val dotColorHolder: DotColorHolder,
    private val listSize: Int
) {


    suspend fun startScrollAnimation() {
        while (true) {
            delay(5000)

            if (listState.firstVisibleItemIndex == listSize - 1) {
                listState.scrollToItem(0)

            } else {
                listState.animateScrollToItem(listState.firstVisibleItemIndex + 1)
            }
        }
    }

    fun getColorForPosition(position: Int): Color =
        if (listState.firstVisibleItemIndex == position) {
            dotColorHolder.selectedColor

        } else {
            dotColorHolder.unselectedColor
        }
}

