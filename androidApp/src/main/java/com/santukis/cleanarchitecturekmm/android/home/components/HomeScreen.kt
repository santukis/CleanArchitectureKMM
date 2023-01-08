package com.santukis.cleanarchitecturekmm.android.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.santukis.cleanarchitecturekmm.android.theme.MovieTheme
import com.santukis.cleanarchitecturekmm.android.theme.WhiteTransparent
import com.santukis.entities.configuration.Language
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

    Box {

        LazyRow(
            state = listState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState = listState),
            modifier = modifier
                .fillMaxWidth()
                .height(height = LocalConfiguration.current.screenWidthDp.dp)
        ) {
            items(nowPlayingMoviesState.movies.size) { index ->
                val movie = nowPlayingMoviesState.movies[index]

                Box {
                    AsyncImage(
                        model = movie.images.posterImage?.getUrl(PosterSize.W_342),
                        contentDescription = "",
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Transparent, Color.Black),
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
                            }
                        ,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        LazyRow(
            modifier =  Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
        ) {
            items(nowPlayingMoviesState.movies.size) { index ->
                val color = if (listState.firstVisibleItemIndex == index) Color.White else WhiteTransparent

                Icon(
                    imageVector = Icons.Filled.Circle,
                    contentDescription = "selected Image",
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .width(8.dp)
                    ,
                    tint = color
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
                        ),
                        titles = Titles(
                            title = "anyTitle",
                            original = "anyOriginalTitle",
                            originalLanguage = Language(iso = "es")
                        ),
                        rating = Rating(
                            average = 3.3,
                            count = 122
                        )
                    )
                ),
                errorMessage = "No Error MEssage"
            )
        )
    }
}