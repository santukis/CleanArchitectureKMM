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
        podfile = project.file("../../../iosApp/Podfile")

        framework {
            baseName = "MultiPlatformLibrary"
            isStatic = false
            linkerOpts.add("-lsqlite3")

            export(project(Modules.ViewModel.core))
            export(project(Modules.ViewModel.home))
            export(project(Modules.ViewModel.movieDetail))
            export(project(Modules.ViewModel.movies))
            export(project(Modules.ViewModel.configuration))
            export(project(Modules.entities))
            export(Shared.Moko.mvvmCore)
            export(Shared.Moko.mvvmFlow)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.Injection.core))
                implementation(project(Modules.Injection.Providers.kodein))
                implementation(project(Modules.Injection.Providers.koin))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                api(project(Modules.ViewModel.core))
                api(project(Modules.ViewModel.configuration))
                api(project(Modules.ViewModel.home))
                api(project(Modules.ViewModel.movies))
                api(project(Modules.ViewModel.movieDetail))
                api(project(Modules.entities))

                api(Shared.Moko.mvvmCore)
                api(Shared.Moko.mvvmFlow)
            }
        }
    }
}

android {
    namespace = "com.santukis.injection.provider"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
}