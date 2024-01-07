pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "puzzle-engine"

include(":app-shared", ":app-desktop", ":app-android", ":app-web")
include(":csp-model", ":csp-solve")
