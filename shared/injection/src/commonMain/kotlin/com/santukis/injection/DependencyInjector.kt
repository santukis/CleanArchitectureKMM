package com.santukis.injection

import com.santukis.viewmodels.movies.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel

interface DependencyInjector {

    fun moviesViewModel(platformDependencies: Any? = null): MoviesViewModel

    fun movieDetailViewModel(platformDependencies: Any? = null): MovieDetailViewModel
}

expect fun getDependencyInjector(moduleDependencies: Any? = null): DependencyInjector