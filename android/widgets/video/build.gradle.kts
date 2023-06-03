import com.santukis.buildsrc.dependencies.Android

plugins {
    id("base_android_library_module")
}

android {
    namespace = "com.santukis.widgets.video"
}

dependencies {
    implementation(Android.Media.exoplayer)
    implementation(Android.Media.exoplayerUi)
    implementation(Android.Media.youtubePlayer)
}