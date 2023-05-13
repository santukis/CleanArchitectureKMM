package com.santukis.injection

import com.santukis.viewmodels.home.HomeViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel

interface DependencyInjector {
    fun homeViewModel(platformDependencies: Any? = null): HomeViewModel

    fun movieDetailViewModel(platformDependencies: Any? = null): MovieDetailViewModel

    fun moviesViewModel(platformDependencies: Any? = null): MoviesViewModel
}
