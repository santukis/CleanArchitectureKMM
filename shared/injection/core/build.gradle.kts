import com.santukis.buildsrc.modules.Modules

plugins {
    kotlin("multiplatform")
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
                implementation(project(Modules.ViewModel.core))
                implementation(project(Modules.ViewModel.home))
                implementation(project(Modules.ViewModel.movieDetail))
                implementation(project(Modules.ViewModel.movies))
                implementation(project(Modules.ViewModel.configuration))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.santukis.injection"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}