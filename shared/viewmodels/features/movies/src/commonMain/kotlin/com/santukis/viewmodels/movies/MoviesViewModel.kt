package com.santukis.viewmodels.movies

import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.movies.entities.MoviesState
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel

abstract class MoviesViewModel: ViewModel() {

    abstract val moviesState: CStateFlow<MoviesState>

    abstract fun loadSectionMovies(section: MovieSection)

    abstract fun onUiEvent(event: OnUiEvent)
}