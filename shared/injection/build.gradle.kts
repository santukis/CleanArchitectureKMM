import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.dependencies.Shared
import com.santukis.buildsrc.modules.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("dev.icerock.moko.kswift")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        name = "MultiPlatformLibrary"
        summary = "MovieDatabase"
        homepage = "https://github.com/santukis/CleanArchitectureKMM"
        version = "1.0"
        ios.deploymentTarget = "15.0"
        podfile = project.file("../../iosApp/Podfile")

        framework {
            baseName = "MultiPlatformLibrary"
            isStatic = false

            export(project(Modules.ViewModels))
            export(project(Modules.Entities))
            export(Shared.Moko.mvvmCore)
            export(Shared.Moko.mvvmFlow)
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.ViewModels))
                implementation(project(Modules.UseCases))
                api(project(Modules.Entities))
                implementation(project(Modules.Repositories))
                implementation(project(Modules.DataSources))

                implementation(Shared.Kodein.kodein)
                implementation(Shared.Kotlin.coroutinesCore)
                implementation(Shared.Ktor.ktorCore)

                implementation(Shared.Datastore.core)

                api(Shared.Moko.mvvmCore)
                api(Shared.Moko.mvvmFlow)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Android.Kodein.kodein)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.santukis.injection"
    compileSdk = 33
    defaultConfig {
        minSdk = 23
        targetSdk = 33
    }
}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
}