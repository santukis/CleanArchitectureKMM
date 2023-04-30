package com.santukis.viewmodels.home

import com.santukis.viewmodels.home.entities.MoviesState
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel

abstract class HomeViewModel: ViewModel() {

    abstract val moviesState: CStateFlow<MoviesState>

    abstract fun loadHomeData()

}