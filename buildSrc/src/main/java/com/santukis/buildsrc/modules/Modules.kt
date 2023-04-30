package com.santukis.buildsrc.modules

object Modules {
    const val injection = ":shared:injection"
    const val useCases = ":shared:usecases"
    const val entities = ":shared:entities"
    const val repositories = ":shared:repositories"
    const val dataSources = ":shared:datasources"

    object Android {
        const val theme = ":android:theme"
        const val navigation = ":android:navigation"
        const val widgets = ":android:widgets"
        const val core = ":android:features:core"
        const val home = ":android:features:home"
        const val movies = ":android:features:movies"
        const val movieDetail = ":android:features:movieDetail"
    }

    object ViewModel {
        const val coreViewModel = ":shared:viewmodels:core"
        const val homeViewModel = ":shared:viewmodels:features:home"
        const val movieDetailViewModel = ":shared:viewmodels:features:movieDetail"
        const val moviesViewModel = ":shared:viewmodels:features:movies"
        const val configurationViewModel = ":shared:viewmodels:configuration"
    }
}