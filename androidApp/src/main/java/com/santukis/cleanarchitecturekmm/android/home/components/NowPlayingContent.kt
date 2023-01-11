package com.santukis.cleanarchitecturekmm.android.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
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
import com.santukis.cleanarchitecturekmm.android.theme.WhiteTransparent
import com.santukis.entities.movies.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
@OptIn(ExperimentalSnapperApi::class)
fun NowPlayingContent(
    modifier: Modifier,
    nowPlayingMovies: List<Movie>
) {
    val listState = rememberLazyListState()

    Box {
        LazyRow(
            state = listState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState = listState),
            modifier = modifier
                .fillMaxWidth()
                .height(height = (LocalConfiguration.current.screenHeightDp * 0.7).dp),
        ) {
            items(nowPlayingMovies.size) { index ->
                val movie = nowPlayingMovies[index]

                Box {
                    AsyncImage(
                        model = movie.images.posterImage?.getUrl(PosterSize.W_342),
                        contentDescription = "",
                        modifier = Modifier
                            .fillParentMaxWidth()
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
                        modifier = modifier
                            .fillParentMaxWidth()
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