import org.gradle.kotlin.dsl.kotlin

plugins {
    id("com.android.application")
    kotlin("android")
    id("detekt")
}

apply(from = "$rootDir/buildSrc/src/main/groovy/base_android_module.gradle")

apply(from = "$rootDir/buildSrc/src/main/groovy/base_compose_dependencies.gradle")
apply(from = "$rootDir/buildSrc/src/main/groovy/base_android_dependencies.gradle")
apply(from = "$rootDir/buildSrc/src/main/groovy/base_android_testing_dependencies.gradle")