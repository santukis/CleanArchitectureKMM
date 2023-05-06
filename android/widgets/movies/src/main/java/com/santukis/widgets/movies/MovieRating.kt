package com.santukis.widgets.movies

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santukis.entities.configuration.Language
import com.santukis.entities.movies.Images
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.Rating
import com.santukis.entities.movies.Titles

@Composable
fun MovieRating(movie: Movie) {
    if (movie.isRated()) {

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
                fontSize = 12.sp
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 360,
    heightDp = 640
)
@Composable
fun MovieRatingPreview() {
    MovieRating(
        movie = Movie(
            id = 1,
            imdbId = "imdbId",
            images = Images(),
            titles = Titles(
                title = "Movie Title",
                original = "Movie Original Title",
                originalLanguage = Language( "English", "en")
            ),
            rating = Rating(
                average = 8.5,
                count = 12345
            ),
            overview = "Movie Overview",
        )
    )
}