import com.santukis.buildsrc.dependencies.Shared
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
                implementation(Shared.Kotlin.coroutinesCore)
                implementation(project(Modules.entities))
                implementation(project(Modules.UseCases.core))
                implementation(project(Modules.UseCases.configuration))
                implementation(project(Modules.UseCases.movies))
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
    namespace = "com.santukis.repositories"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
