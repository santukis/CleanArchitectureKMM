import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.dependencies.Shared
import com.santukis.buildsrc.dependencies.iOS
import com.santukis.buildsrc.modules.Modules
import java.util.Properties

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.7.10"
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.Repositories))
                api(project(Modules.Entities))

                implementation(Shared.Ktor.ktorCore)
                implementation(Shared.Ktor.ktorCio)
                implementation(Shared.Ktor.ktorLogging)
                implementation(Shared.Ktor.ktorAuth)
                implementation(Shared.Ktor.ktorContentNegotiation)
                implementation(Shared.Ktor.ktorJson)

                implementation(Shared.Datastore.core)

                implementation(Shared.Kotlin.coroutinesCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

                implementation(Shared.Test.ktorMock)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Android.Ktor.ktorOkhttp)

                implementation(Android.Kotlin.coroutinesAndroid)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(iOS.Ktor.ktorDarwin)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.santukis.datasources"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        val movieDatabaseProperties = Properties()
        file(path = "movie_database.properties").inputStream().use { stream ->
            movieDatabaseProperties.load(stream)
        }

        buildConfigField(
            type = "String",
            name = "MOVIE_DATABASE_SECRET",
            value = movieDatabaseProperties.getProperty("MOVIE_DATABASE_SECRET")
        )
    }
}
