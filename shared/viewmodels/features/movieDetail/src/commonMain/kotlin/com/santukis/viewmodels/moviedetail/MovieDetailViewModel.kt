package com.santukis.viewmodels.moviedetail

import com.santukis.viewmodels.moviedetail.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel

abstract class MovieDetailViewModel: ViewModel() {

    abstract val movieDetailState: CStateFlow<MovieDetailState>

    abstract fun loadMovie(movieId: String)

}