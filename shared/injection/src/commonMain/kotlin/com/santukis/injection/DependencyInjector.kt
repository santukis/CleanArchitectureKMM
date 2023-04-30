package com.santukis.injection

import com.santukis.viewmodels.home.MoviesViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel

interface DependencyInjector {
    fun moviesViewModel(platformDependencies: Any? = null): MoviesViewModel

    fun movieDetailViewModel(platformDependencies: Any? = null): MovieDetailViewModel
}

expect fun initializeDependencyInjector(moduleDependencies: Any? = null)

expect fun getDependencyInjector(): DependencyInjector
