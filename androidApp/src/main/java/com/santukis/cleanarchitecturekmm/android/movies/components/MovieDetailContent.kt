package com.santukis.cleanarchitecturekmm.android.movies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.santukis.cleanarchitecturekmm.android.core.components.SectionContent
import com.santukis.cleanarchitecturekmm.android.core.components.YoutubeVideoPlayer
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent
import com.santukis.cleanarchitecturekmm.android.core.events.RequestDecorFitsSystemWindowsChange
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
