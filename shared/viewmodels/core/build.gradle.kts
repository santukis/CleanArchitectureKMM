import com.santukis.buildsrc.modules.Modules
import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.dependencies.Shared

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Shared.Kotlin.coroutinesCore)
                api(Shared.Moko.mvvmCore)
                api(Shared.Moko.mvvmFlow)

                implementation(project(Modules.UseCases.core))
                implementation(project(Modules.entities))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(Android.Moko.mvvmCompose)
                implementation(Android.Androidx.core)
                implementation(Android.Androidx.lifecycleViewModel)
                implementation(Android.Androidx.lifecycleViewModelCompose)
            }
        }
    }
}

android {
    namespace = "com.santukis.viewmodels.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}