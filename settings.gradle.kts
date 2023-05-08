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
include(":shared:usecases:features:configuration")
include(":shared:usecases:features:movies")

include(":shared:entities")
include(":shared:repositories")
include(":shared:datasources")
include(":shared:injection")

//Viewmodels
include(":shared:viewmodels:core")
include(":shared:viewmodels:features:home")
include(":shared:viewmodels:features:movieDetail")
include(":shared:viewmodels:features:configuration")
include(":shared:viewmodels:features:movies")
include(":shared:viewmodels:features:movies")
