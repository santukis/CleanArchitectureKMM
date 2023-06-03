import com.santukis.buildsrc.modules.Modules

plugins {
    id("base_android_library_module")
}

android {
    namespace = "com.santukis.core"
}

dependencies {
    implementation(project(Modules.ViewModel.core))
    implementation(project(Modules.entities))

    implementation(project(Modules.Android.theme))
    implementation(project(Modules.Android.Navigation.core))
    implementation(project(Modules.Android.Widgets.core))
}