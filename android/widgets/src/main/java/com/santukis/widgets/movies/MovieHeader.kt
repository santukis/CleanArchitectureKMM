package com.santukis.widgets.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santukis.entities.movies.Movie

@Composable
fun MovieHeader(
    modifier: Modifier,
    movie: Movie
) {
    Column(
        modifier = modifier
            .padding(
                vertical = 32.dp,
                horizontal = 8.dp
            ),
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

        MovieRating(movie = movie)
    }
}