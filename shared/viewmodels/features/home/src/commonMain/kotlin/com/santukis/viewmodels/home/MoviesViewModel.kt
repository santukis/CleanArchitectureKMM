package com.santukis.viewmodels.home

import com.santukis.viewmodels.home.entities.MoviesState
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel

abstract class MoviesViewModel: ViewModel() {

    abstract val moviesState: CStateFlow<MoviesState>

    abstract fun loadHomeData()

}