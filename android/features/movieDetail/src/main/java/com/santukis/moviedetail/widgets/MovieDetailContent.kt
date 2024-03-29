package com.santukis.moviedetail.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.moviedetail.entities.MovieDetailState
import com.santukis.widgets.SectionContent
import com.santukis.widgets.video.YoutubeVideoPlayer

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
            text = movieDetailState.movie?.titles?.title ?: "No Title"
        )
    }

}
