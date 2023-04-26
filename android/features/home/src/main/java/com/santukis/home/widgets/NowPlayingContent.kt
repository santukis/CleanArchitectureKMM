package com.santukis.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.santukis.entities.movies.*
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.navigation.destination.arguments.MovieDetailDestinationArguments
import com.santukis.theme.WhiteTransparent
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.delay

@Composable
@OptIn(ExperimentalSnapperApi::class)
fun NowPlayingContent(
    modifier: Modifier,
    nowPlayingMovies: List<Movie>,
    navigateTo: (DestinationArguments) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(5000)

            if (listState.firstVisibleItemIndex == nowPlayingMovies.lastIndex) {
                listState.scrollToItem(0)

            } else {
                listState.animateScrollToItem(listState.firstVisibleItemIndex + 1)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = (LocalConfiguration.current.screenHeightDp * 0.7).dp),
    ) {
        LazyRow(
            state = listState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState = listState),
        ) {
            items(nowPlayingMovies.size) { index ->
                val movie = nowPlayingMovies[index]

                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            navigateTo(
                                MovieDetailDestinationArguments(
                                    movieId = movie.id
                                )
                            )
                        }
                ) {
                    AsyncImage(
                        model = movie.images.posterImage?.getUrl(PosterSize.W_342),
                        contentDescription = "",
                        modifier = modifier
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 0f,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(
                                        gradient,
                                        blendMode = BlendMode.Multiply
                                    )
                                }
                            },
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(vertical = 32.dp, horizontal = 8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = movie.titles.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.White,
                            maxLines = 2,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                imageVector = Icons.Filled.Star,
                                contentDescription = "",
                                tint = MaterialTheme.colors.secondary
                            )
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp),
                                text = movie.rating.getText(),
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
        ) {
            items(nowPlayingMovies.size) { index ->
                val color = if (listState.firstVisibleItemIndex == index) Color.White else WhiteTransparent

                Icon(
                    imageVector = Icons.Filled.Circle,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .width(8.dp),
                    tint = color
                )
            }
        }
    }
}