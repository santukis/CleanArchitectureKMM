import com.santukis.buildsrc.modules.Modules

plugins {
    id("base_android_library_module")
}

android {
    namespace = "com.santukis.navigation.destinations"
}

dependencies {
    implementation(project(Modules.Injection.provider))
    implementation(project(Modules.ViewModel.core))
    implementation(project(Modules.ViewModel.home))
    implementation(project(Modules.ViewModel.movies))
    implementation(project(Modules.ViewModel.movieDetail))
    implementation(project(Modules.Android.Navigation.core))
    implementation(project(Modules.Android.Features.home))
    implementation(project(Modules.Android.Features.movies))
    implementation(project(Modules.Android.Features.movieDetail))
}