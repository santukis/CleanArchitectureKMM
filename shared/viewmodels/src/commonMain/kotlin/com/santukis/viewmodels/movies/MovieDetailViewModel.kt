package com.santukis.viewmodels.movies

import com.santukis.viewmodels.movies.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CStateFlow

interface MovieDetailViewModel {
    val movieDetailState: CStateFlow<MovieDetailState>
    fun loadMovie(movieId: String)
}