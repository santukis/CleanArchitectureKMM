package com.santukis.viewmodels.moviedetail

import com.santukis.viewmodels.moviedetail.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CStateFlow

interface MovieDetailViewModel {
    val movieDetailState: CStateFlow<MovieDetailState>
    fun loadMovie(movieId: String)
}