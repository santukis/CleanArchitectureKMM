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
import com.santukis.entities.movies.*
import com.santukis.viewmodels.movies.MovieViewModel
import com.santukis.viewmodels.movies.entities.MoviesState
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
fun HomeScreen(
    movieViewModel: MovieViewModel
) {
    val nowPlayingMoviesState = movieViewModel.nowPlayingMoviesState.collectAsState()

    HomeContent(
        modifier = Modifier,
        nowPlayingMoviesState = nowPlayingMoviesState.value
    )
}

@Composable
fun HomeContent(
    modifier: Modifier,
    nowPlayingMoviesState: MoviesState
) {
    Column {
        NowPlayingContent(
            modifier,
            nowPlayingMoviesState
        )
    }
}

@Composable
@OptIn(ExperimentalSnapperApi::class)
private fun NowPlayingContent(
    modifier: Modifier,
    nowPlayingMoviesState: MoviesState
) {
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState = listState),
        modifier = modifier.fillMaxWidth()
    ) {
        items(nowPlayingMoviesState.movies.size) { index ->
            val movie = nowPlayingMoviesState.movies[index]

            Box() {
                AsyncImage(
                    model = movie.images.posterImage?.getUrl(PosterSize.W_342),
                    contentDescription = "",
                    modifier = Modifier
                        .fillParentMaxWidth(),
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
            nowPlayingMoviesState = MoviesState(
                movies = listOf(
                    Movie(
                        id = 11,
                        imdbId = "anyImdbID",
                        images = Images(
                            backdropImage = BackdropImage(path = "/evaFLqtswezLosllRZtJNMiO1UR.jpg"),
                            posterImage = PosterImage(path = "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg")
                        )
                    )
                ),
                errorMessage = "No Error MEssage"
            )
        )
    }
}