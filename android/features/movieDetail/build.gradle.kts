import com.santukis.buildsrc.modules.Modules

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "$rootDir/android/gradle/configuration/base_android_library_module.gradle")

android {
    namespace = "com.santukis.moviedetail"
}

dependencies {
    implementation(project(Modules.ViewModel.core))
    implementation(project(Modules.ViewModel.movieDetail))
    implementation(project(Modules.entities))

    implementation(project(Modules.Android.theme))
    implementation(project(Modules.Android.Features.core))
    implementation(project(Modules.Android.Navigation.core))
    implementation(project(Modules.Android.Widgets.core))
    implementation(project(Modules.Android.Widgets.movies))
    implementation(project(Modules.Android.Widgets.video))
}