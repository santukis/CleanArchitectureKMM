import com.santukis.buildsrc.modules.Modules

plugins {
    id("base_multiplatform_module")
}

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