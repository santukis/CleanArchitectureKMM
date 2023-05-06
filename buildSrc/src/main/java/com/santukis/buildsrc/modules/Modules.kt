package com.santukis.buildsrc.modules

object Modules {
    const val injection = ":shared:injection"
    const val useCases = ":shared:usecases"
    const val entities = ":shared:entities"
    const val repositories = ":shared:repositories"
    const val dataSources = ":shared:datasources"

    object Android {
        const val theme = ":android:theme"

        object Navigation {
            const val core = ":android:navigation:core"
            const val destinations = ":android:navigation:destinations"
            const val graphs = ":android:navigation:graphs"
        }

        object Features {
            const val core = ":android:features:core"
            const val home = ":android:features:home"
            const val movies = ":android:features:movies"
            const val movieDetail = ":android:features:movieDetail"
        }

        object Widgets {
            const val core = ":android:widgets:core"
            const val movies = ":android:widgets:movies"
            const val video = ":android:widgets:video"
        }
    }

    object ViewModel {
        const val core = ":shared:viewmodels:core"
        const val home = ":shared:viewmodels:features:home"
        const val movieDetail = ":shared:viewmodels:features:movieDetail"
        const val movies = ":shared:viewmodels:features:movies"
        const val configuration = ":shared:viewmodels:features:configuration"
    }
}