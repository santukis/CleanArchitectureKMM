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

include(":android:androidApp")
include(":shared:viewmodels")
include(":shared:usecases")
include(":shared:entities")
include(":shared:repositories")
include(":shared:datasources")
include(":shared:injection")
include(":android:navigation")
include(":android:features:home")
include(":android:uiEvents")
include(":android:widgets")
include(":android:theme")
include(":android:features:movieDetail")
