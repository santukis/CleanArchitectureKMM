import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.dependencies.iOS
import com.santukis.buildsrc.dependencies.Shared
import com.santukis.buildsrc.modules.Modules

plugins {
    id("base_multiplatform_module")
    kotlin("plugin.serialization") version "1.7.10"
    id("com.squareup.sqldelight")
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "movies"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.DataSources.core))
                implementation(project(Modules.Repositories.Sources.configuration))
                implementation(project(Modules.Repositories.Sources.movies))
                implementation(project(Modules.entities))

                implementation(Shared.Ktor.ktorCore)
                implementation(Shared.Ktor.ktorContentNegotiation)
                implementation(Shared.Ktor.ktorJson)

                implementation(Shared.Datastore.core)
                implementation(Shared.SQLDelight.runtime)

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
                implementation(Android.SQLDelight.androidDriver)
                implementation(Android.Kotlin.coroutinesAndroid)
            }
        }
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
                implementation(iOS.SQLDelight.iosDriver)
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
    namespace = "com.santukis.datasources.movies"
}