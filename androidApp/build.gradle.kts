import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.dependencies.Shared
import com.santukis.buildsrc.modules.Modules

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.santukis.cleanarchitecturekmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.santukis.cleanarchitecturekmm.android"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(Modules.Injection))
    implementation(project(Modules.ViewModels))
    implementation(project(Modules.Entities))

    implementation(Android.Androidx.appcompat)
    implementation(Android.Androidx.core)
    implementation(Android.Androidx.activityCompose)
    implementation(Android.Androidx.composeRuntime)
    implementation(Android.Androidx.composeUi)
    implementation(Android.Androidx.composeUiTooling)
    implementation(Android.Androidx.composeFoundation)
    implementation(Android.Androidx.composeMaterial)
    implementation(Android.Androidx.composeMaterialIcons)
    implementation(Android.Androidx.composeMaterialIconsExtended)
    implementation(Android.Androidx.composeConstraintLayout)
    implementation(Android.Androidx.composeUtil)
    implementation(Android.Androidx.lifecycleViewModelCompose)
    implementation(Android.Coil.coil)
    implementation(Android.Snapper.snapper)

    implementation(Shared.Kodein.kodein)
}