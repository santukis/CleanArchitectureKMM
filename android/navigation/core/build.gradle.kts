import com.santukis.buildsrc.modules.Modules

plugins {
    id("base_android_library_module")
}

android {
    namespace = "com.santukis.navigation"
}

dependencies {
    implementation(project(Modules.ViewModel.core))
}