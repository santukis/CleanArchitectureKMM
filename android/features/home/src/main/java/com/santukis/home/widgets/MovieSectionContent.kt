package com.santukis.home.widgets

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.santukis.core.entities.getSectionTitle
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.PosterSize
import com.santukis.home.navigation.arguments.ToMovieDetailDestinationArguments
import com.santukis.home.navigation.arguments.ToMoviesDestinationArguments
import com.santukis.navigation.NavigationArguments
import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.widgets.SectionContent

@Composable
fun MovieSectionContent(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    section: MovieSection,
    navigateTo: (NavigationArguments) -> Unit
) {
    SectionContent(
        modifier = modifier,
        items = movies,
        sectionRow = {
            Text(
                text = section.getSectionTitle(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Icon(
                modifier = Modifier
                    .clickable {
                        navigateTo(
                            ToMoviesDestinationArguments(section = section)
                        )
                    },
                imageVector = Icons.Filled.Add,
                contentDescription = ""
            )
        },
        itemRow = { movie ->

            AsyncImage(
                modifier = Modifier
                    .fillParentMaxWidth(0.4f)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable {
                        navigateTo(
                            ToMovieDetailDestinationArguments(
                                movieId = movie.id
                            )
                        )
                    },
                model = movie.images.posterImage?.getUrl(PosterSize.W_342),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
    )
}
