plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("com.android.application:com.android.application.gradle.plugin:8.0.0")
    implementation("com.android.library:com.android.library.gradle.plugin:8.0.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    implementation("dev.icerock.moko:kswift-gradle-plugin:0.6.1")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.0")
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}