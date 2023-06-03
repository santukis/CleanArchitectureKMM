import com.santukis.buildsrc.dependencies.Shared

plugins {
    id("base_multiplatform_module")
}

kotlin {
    sourceSets {
        val commonMain by getting

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Shared.Test.junit4)
            }
        }
    }
}

android {
    namespace = "com.santukis.entities"
}
