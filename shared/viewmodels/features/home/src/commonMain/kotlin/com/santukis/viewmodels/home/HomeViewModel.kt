package com.santukis.viewmodels.home

import com.santukis.viewmodels.home.entities.HomeState
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel

abstract class HomeViewModel: ViewModel() {

    abstract val homeState: CStateFlow<HomeState>

    abstract fun loadHomeData()

}