package com.santukis.buildsrc.modules

object Modules {
    const val Injection = ":shared:injection"
    const val ViewModels = ":shared:viewmodels"
    const val UseCases = ":shared:usecases"
    const val Entities = ":shared:entities"
    const val Repositories = ":shared:repositories"
    const val DataSources = ":shared:datasources"

    object Android {
        const val Theme = ":android:theme"
        const val Navigation = ":android:navigation"
        const val UiEvents = ":android:uiEvents"
        const val Widgets = ":android:widgets"
        const val Home = ":android:features:home"
        const val MovieDetail = ":android:features:movieDetail"
    }
}