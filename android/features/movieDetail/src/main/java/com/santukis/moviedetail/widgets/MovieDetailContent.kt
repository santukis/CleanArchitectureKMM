package com.santukis.moviedetail.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.santukis.widgets.SectionContent
import com.santukis.widgets.YoutubeVideoPlayer
import com.santukis.viewmodels.events.OnUiEvent
import com.santukis.viewmodels.movies.entities.MovieDetailState

@Composable
fun MovieDetailContent(
    modifier: Modifier,
    movieDetailState: MovieDetailState,
    onUiEvent: (OnUiEvent) -> Unit = {}
) {

    Column(
        modifier = modifier
    ) {

        SectionContent(
            modifier = Modifier.fillMaxWidth(),
            items = movieDetailState.videos,
            sectionRow = { }
        ) { video ->
            YoutubeVideoPlayer(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .aspectRatio(16 / 9f),
                videoId = video.url
            )
        }

        Text(
            text = movieDetailState.movie?.titles?.title ?: "No Title",
            color = Color.White
        )
    }

}
