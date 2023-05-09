pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CleanArchitectureKMM"

include(":android:app")
include(":android:theme")
include(":android:navigation:core")
include(":android:navigation:destinations")
include(":android:navigation:graphs")
include(":android:widgets:core")
include(":android:widgets:movies")
include(":android:widgets:video")
include(":android:features:home")
include(":android:features:core")
include(":android:features:movieDetail")
include(":android:features:movies")

//Usecases
include(":shared:usecases:core")
include(":shared:usecases:features:configuration:usecases")
include(":shared:usecases:features:configuration:outputs")
include(":shared:usecases:features:movies:usecases")
include(":shared:usecases:features:movies:outputs")

include(":shared:repositories:core")
include(":shared:repositories:features:configuration")
include(":shared:repositories:features:movies")

include(":shared:entities")
include(":shared:datasources")
include(":shared:injection")

//Viewmodels
include(":shared:viewmodels:core")
include(":shared:viewmodels:features:home")
include(":shared:viewmodels:features:movieDetail")
include(":shared:viewmodels:features:configuration")
include(":shared:viewmodels:features:movies")
include(":shared:viewmodels:features:movies")
