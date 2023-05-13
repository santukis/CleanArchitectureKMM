import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.dependencies.Shared
import com.santukis.buildsrc.modules.Modules

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "koin"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.Injection.core))
                implementation(project(Modules.ViewModel.core))
                implementation(project(Modules.ViewModel.home))
                implementation(project(Modules.ViewModel.movieDetail))
                implementation(project(Modules.ViewModel.movies))
                implementation(project(Modules.ViewModel.configuration))
                implementation(project(Modules.UseCases.core))
                implementation(project(Modules.UseCases.configuration))
                implementation(project(Modules.UseCases.movies))
                implementation(project(Modules.UseCases.Outputs.configuration))
                implementation(project(Modules.UseCases.Outputs.movies))
                implementation(project(Modules.entities))
                implementation(project(Modules.Repositories.core))
                implementation(project(Modules.Repositories.configuration))
                implementation(project(Modules.Repositories.movies))
                implementation(project(Modules.Repositories.Sources.configuration))
                implementation(project(Modules.Repositories.Sources.movies))
                implementation(project(Modules.DataSources.core))
                implementation(project(Modules.DataSources.configuration))
                implementation(project(Modules.DataSources.movies))

                implementation(Shared.Koin.koin)
                implementation(Shared.Kotlin.coroutinesCore)
                implementation(Shared.Ktor.ktorCore)

                implementation(Shared.Datastore.core)

                implementation(Shared.Moko.mvvmCore)
                implementation(Shared.Moko.mvvmFlow)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Android.Koin.koin)
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
        }
    }
}

android {
    namespace = "com.santukis.injection.koin"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}