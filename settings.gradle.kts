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

include(":androidApp")
include(":shared:viewmodels")
include(":shared:usecases")
include(":shared:entities")
include(":shared:repositories")
include(":shared:datasources")