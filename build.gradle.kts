plugins {
    id("com.squareup.sqldelight").version("1.5.5").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}