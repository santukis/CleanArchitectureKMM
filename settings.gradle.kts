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
include(":android:navigation")
include(":android:features:home")
include(":android:widgets")
include(":android:theme")
include(":android:features:movieDetail")

include(":shared:usecases")
include(":shared:entities")
include(":shared:repositories")
include(":shared:datasources")
include(":shared:injection")

//Viewmodels
include(":shared:viewmodels:core")
include(":shared:viewmodels:features:home")
include(":shared:viewmodels:features:movieDetail")
include(":shared:viewmodels:configuration")
include(":android:features:movies")
include(":shared:viewmodels:features:movies")
include(":shared:viewmodels:features:movies")
