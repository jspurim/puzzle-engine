pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "puzzle-engine"

include(":csp-model", ":app-shared", ":app-desktop", ":app-android")
