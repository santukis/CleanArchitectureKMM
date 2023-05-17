import com.santukis.buildsrc.dependencies.Shared
import com.santukis.buildsrc.modules.Modules

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

apply(from = "$rootDir/shared/gradle/configuration/base_multiplatform_module.gradle")

kotlin {
    sourceSets {
        val commonMain by getting

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Shared.Test.junit4)
            }
        }
    }
}

android {
    namespace = "com.santukis.entities"
}
