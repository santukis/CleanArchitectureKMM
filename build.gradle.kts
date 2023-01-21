plugins {
    id("com.android.application").version("7.3.0").apply(false)
    id("com.android.library").version("7.3.0").apply(false)
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)
    kotlin("jvm").version("1.8.0").apply(false)
    id("dev.icerock.moko.kswift").version("0.6.1").apply(false)
    id("com.squareup.sqldelight").version("1.5.5").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}