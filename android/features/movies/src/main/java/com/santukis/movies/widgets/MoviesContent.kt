package com.santukis.movies.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.navigation.destination.arguments.MovieDetailDestinationArguments
import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.movies.entities.MoviesState
import com.santukis.viewmodels.movies.events.OnSectionEndReached
import com.santukis.widgets.OnEndReached
import com.santukis.widgets.movies.MovieHeader
import com.santukis.widgets.movies.MoviePoster

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
        verticalArrangement = Arrangement.spacedBy(24.dp)
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
                    MoviePoster(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxHeight(0.4f),
                        movie = movie
                    )

                    MovieHeader(
                        modifier =  Modifier
                            .align(Alignment.BottomStart)
                            .padding(vertical = 8.dp),
                        movie = movie
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = movie.overview,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}