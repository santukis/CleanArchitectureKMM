package com.santukis.viewmodels.home

import com.santukis.viewmodels.home.entities.MoviesState
import dev.icerock.moko.mvvm.flow.CStateFlow

interface MoviesViewModel {
    val moviesState: CStateFlow<MoviesState>
    fun loadHomeData()
}