package com.santukis.buildsrc.modules

object Modules {
    const val injection = ":shared:injection"
    const val useCases = ":shared:usecases"
    const val entities = ":shared:entities"
    const val repositories = ":shared:repositories"
    const val dataSources = ":shared:datasources"

    object Android {
        const val Theme = ":android:theme"
        const val Navigation = ":android:navigation"
        const val Widgets = ":android:widgets"
        const val Home = ":android:features:home"
        const val MovieDetail = ":android:features:movieDetail"
    }

    object ViewModel {
        const val coreViewModel = ":shared:viewmodels:core"
        const val homeViewModel = ":shared:viewmodels:features:home"
        const val movieDetailViewModel = ":shared:viewmodels:features:movieDetail"
        const val configurationViewModel = ":shared:viewmodels:configuration"
    }
}