import com.santukis.buildsrc.modules.Modules

plugins {
    id("base_android_library_module")
}

android {
    namespace = "com.santukis.movies"
}

dependencies {
    implementation(project(Modules.Injection.provider))
    implementation(project(Modules.ViewModel.core))
    implementation(project(Modules.ViewModel.movies))
    implementation(project(Modules.entities))

    implementation(project(Modules.Android.theme))
    implementation(project(Modules.Android.Features.core))
    implementation(project(Modules.Android.Widgets.core))
    implementation(project(Modules.Android.Widgets.movies))
}