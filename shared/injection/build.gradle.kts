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
            linkerOpts.add("-lsqlite3")

            export(project(Modules.ViewModel.coreViewModel))
            export(project(Modules.ViewModel.homeViewModel))
            export(project(Modules.ViewModel.movieDetailViewModel))
            export(project(Modules.ViewModel.moviesViewModel))
            export(project(Modules.ViewModel.configurationViewModel))
            export(project(Modules.entities))
            export(Shared.Moko.mvvmCore)
            export(Shared.Moko.mvvmFlow)
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.ViewModel.coreViewModel))
                api(project(Modules.ViewModel.homeViewModel))
                api(project(Modules.ViewModel.movieDetailViewModel))
                api(project(Modules.ViewModel.moviesViewModel))
                api(project(Modules.ViewModel.configurationViewModel))
                implementation(project(Modules.useCases))
                api(project(Modules.entities))
                implementation(project(Modules.repositories))
                api(project(Modules.dataSources))

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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
}