plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dev.icerock.moko.kswift")
    id("detekt")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}