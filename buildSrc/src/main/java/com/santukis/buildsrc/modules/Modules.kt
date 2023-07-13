package com.santukis.buildsrc.modules

object Modules {
    const val entities = ":shared:entities"

    object Android {
        const val theme = ":android:theme"

        object Navigation {
            const val core = ":android:navigation"
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

    object Injection {
        const val core = ":shared:injection:core"
        const val provider = ":shared:injection:provider"

        object Providers {
            const val kodein = ":shared:injection:providers:kodein"
            const val koin = ":shared:injection:providers:koin"
        }
    }

    object ViewModel {
        const val core = ":shared:viewmodels:core"
        const val home = ":shared:viewmodels:features:home"
        const val movieDetail = ":shared:viewmodels:features:movieDetail"
        const val movies = ":shared:viewmodels:features:movies"
        const val configuration = ":shared:viewmodels:features:configuration"
    }

    object UseCases {
        const val core = ":shared:usecases:core"
        const val configuration = ":shared:usecases:features:configuration:usecases"
        const val movies = ":shared:usecases:features:movies:usecases"

        object Outputs {
            const val configuration = ":shared:usecases:features:configuration:outputs"
            const val movies = ":shared:usecases:features:movies:outputs"
        }
    }

    object Repositories {
        const val core = ":shared:repositories:core"
        const val configuration = ":shared:repositories:features:configuration:repositories"
        const val movies = ":shared:repositories:features:movies:repositories"

        object Sources {
            const val configuration = ":shared:repositories:features:configuration:sources"
            const val movies = ":shared:repositories:features:movies:sources"
        }
    }

    object DataSources {
        const val core = ":shared:datasources:core"
        const val configuration = ":shared:datasources:features:configuration"
        const val movies = ":shared:datasources:features:movies"
    }
}