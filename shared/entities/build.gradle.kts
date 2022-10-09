import com.santukis.buildsrc.dependencies.Shared
import com.santukis.buildsrc.modules.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../../iosApp/Podfile")

        framework {
            baseName = "entities"
        }
    }

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
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}
