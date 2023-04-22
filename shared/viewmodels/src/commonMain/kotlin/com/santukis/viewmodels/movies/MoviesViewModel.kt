package com.santukis.viewmodels.movies

import com.santukis.viewmodels.movies.entities.MoviesState
import dev.icerock.moko.mvvm.flow.CStateFlow

interface MoviesViewModel {
    val moviesState: CStateFlow<MoviesState>
    fun loadHomeData()
}