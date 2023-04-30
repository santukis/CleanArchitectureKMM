package com.santukis.movies.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.santukis.entities.movies.PosterSize
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.navigation.destination.arguments.MovieDetailDestinationArguments
import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.viewmodels.movies.events.OnSectionEndReached
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.movies.entities.MoviesState
import com.santukis.widgets.OnEndReached

@Composable
fun MoviesContent(
    modifier: Modifier,
    section: MovieSection,
    moviesState: MoviesState,
    onUiEvent: (OnUiEvent) -> Unit = {},
    navigateTo: (DestinationArguments) -> Unit = {},
) {
    val listState = rememberLazyListState()

    listState.OnEndReached {
        onUiEvent(OnSectionEndReached(section))
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = moviesState.movies,
            key = { movie -> movie.id }
        ) { movie ->
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .clickable {
                        navigateTo(
                            MovieDetailDestinationArguments(
                                movieId = movie.id
                            )
                        )
                    },
            ) {
                Box {
                    AsyncImage(
                        model = movie.images.posterImage?.getUrl(PosterSize.W_342),
                        contentDescription = "",
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxHeight(0.4f)
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
                            .padding(vertical = 8.dp, horizontal = 8.dp),
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

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = movie.overview,
                    color = Color.White
                )
            }
        }
    }
}