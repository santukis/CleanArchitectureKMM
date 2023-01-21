package com.santukis.cleanarchitecturekmm.android.movies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.santukis.cleanarchitecturekmm.android.core.components.SectionContent
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.PosterSize

@Composable
fun MovieSectionContent(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    sectionTitle: String,
    onMovieClick: (Movie) -> Unit
) {

    SectionContent(
        modifier = modifier,
        items = movies,
        sectionRow = {
            Text(
                text = sectionTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "",
                tint = Color.White
            )
        },
        itemRow = { movie ->

            AsyncImage(
                modifier = Modifier
                    .fillParentMaxWidth(0.4f)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onMovieClick(movie) },
                model = movie.images.posterImage?.getUrl(PosterSize.W_342),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
    )
}