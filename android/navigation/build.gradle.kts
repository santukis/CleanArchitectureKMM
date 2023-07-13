import com.santukis.buildsrc.modules.Modules

plugins {
    id("base_android_library_module")
}

android {
    namespace = "com.santukis.navigation.movies"
}

dependencies {
    implementation(project(Modules.Injection.provider))
    implementation(project(Modules.ViewModel.core))
    implementation(project(Modules.Android.Features.home))
    implementation(project(Modules.Android.Features.movies))
    implementation(project(Modules.Android.Features.movieDetail))
}