import com.santukis.buildsrc.modules.Modules
import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.dependencies.Shared

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
                api(Shared.Moko.mvvmCore)
                api(Shared.Moko.mvvmFlow)

                implementation(project(Modules.UseCases))
                api(project(Modules.Entities))
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
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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
    namespace = "com.santukis.viewmodels"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}