package com.santukis.cleanarchitecturekmm.android.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.santukis.cleanarchitecturekmm.android.theme.MovieTheme
import com.santukis.entities.movies.Movie
import com.santukis.viewmodels.movies.MovieViewModel
import com.santukis.viewmodels.movies.entities.MoviesState
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
fun HomeScreen(
    movieViewModel: MovieViewModel
) {
    val highlightsMovieState = movieViewModel.nowPlayingMoviesState.collectAsState()

    HomeContent(
        modifier = Modifier,
        highlightMoviesState = highlightsMovieState.value
    )
}

@Composable
fun HomeContent(
    modifier: Modifier,
    highlightMoviesState: MoviesState
) {
    Column {
        HighlightContent(
            modifier,
            highlightMoviesState
        )
    }
}

@Composable
@OptIn(ExperimentalSnapperApi::class)
private fun HighlightContent(
    modifier: Modifier,
    highlightMoviesState: MoviesState
) {
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState = listState),
        modifier = modifier.fillMaxWidth()
    ) {
        items(highlightMoviesState.movies.size) { index ->
            val movie = highlightMoviesState.movies[index]

            Box() {
                AsyncImage(
                    model = "",
                    contentDescription = "",
                    modifier = Modifier
                        .fillParentMaxSize(),
                )


            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun HomeContentPreview() {
    MovieTheme {
        HomeContent(
            modifier = Modifier,
            highlightMoviesState = MoviesState(
                movies = listOf(
                    Movie(
                        id = 11,
                        imdbId = "anyImdbID"
                    )
                ),
                errorMessage = "No Error MEssage"
            )
        )
    }
}