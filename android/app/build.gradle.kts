import com.santukis.buildsrc.modules.Modules

plugins {
    id("base_android_application_module")
}

android {
    namespace = "com.santukis.cleanarchitecturekmm.android"

    defaultConfig {
        applicationId = "com.santukis.cleanarchitecturekmm.android"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(Modules.Injection.provider))
    implementation(project(Modules.ViewModel.core))

    implementation(project(Modules.Android.theme))
    implementation(project(Modules.Android.Navigation.core))
    implementation(project(Modules.Android.Navigation.graphs))
    implementation(project(Modules.Android.Navigation.destinations))
    implementation(project(Modules.Android.Widgets.core))
}