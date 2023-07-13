val santukisCredentials = java.util.Properties()
file("santukis.properties").inputStream().let { stream ->
    santukisCredentials.load(stream)
}

val santukisUrl = santukisCredentials.getProperty("URL")
val santukisUserName = System.getenv("USERNAME") ?: santukisCredentials.getProperty("USERNAME")
val santukisToken = System.getenv("TOKEN") ?: santukisCredentials.getProperty("TOKEN")

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
        maven {
            url = uri(santukisUrl)
            credentials {
                username = santukisUserName
                password = santukisToken
            }
        }
    }
}

rootProject.name = "CleanArchitectureKMM"

include(":android:app")
include(":android:theme")
include(":android:navigation")
include(":android:widgets:core")
include(":android:widgets:movies")
include(":android:widgets:video")
include(":android:features:home")
include(":android:features:core")
include(":android:features:movieDetail")
include(":android:features:movies")

include(":shared:injection:core")
include(":shared:injection:provider")
include(":shared:injection:providers:kodein")
include(":shared:injection:providers:koin")

include(":shared:viewmodels:core")
include(":shared:viewmodels:features:home")
include(":shared:viewmodels:features:movieDetail")
include(":shared:viewmodels:features:configuration")
include(":shared:viewmodels:features:movies")
include(":shared:viewmodels:features:movies")

include(":shared:usecases:core")
include(":shared:usecases:features:configuration:usecases")
include(":shared:usecases:features:configuration:outputs")
include(":shared:usecases:features:movies:usecases")
include(":shared:usecases:features:movies:outputs")

include(":shared:entities")

include(":shared:repositories:core")
include(":shared:repositories:features:configuration:repositories")
include(":shared:repositories:features:configuration:sources")
include(":shared:repositories:features:movies:repositories")
include(":shared:repositories:features:movies:sources")

include(":shared:datasources:core")
include(":shared:datasources:features:configuration")
include(":shared:datasources:features:movies")
