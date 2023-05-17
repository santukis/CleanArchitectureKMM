import com.santukis.buildsrc.modules.Modules

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

apply(from = "$rootDir/shared/gradle/configuration/base_multiplatform_module.gradle")

kotlin {
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
}