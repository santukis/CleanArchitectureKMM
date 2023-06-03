import com.santukis.buildsrc.modules.Modules
import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.dependencies.Shared

plugins {
    id("base_multiplatform_module")
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "configuration"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Shared.Kotlin.coroutinesCore)
                api(Shared.Moko.mvvmCore)
                api(Shared.Moko.mvvmFlow)

                implementation(project(Modules.ViewModel.core))
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
    namespace = "com.santukis.viewmodels.configuration"
}