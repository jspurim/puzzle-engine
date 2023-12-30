plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "com.github.jspurim.template"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



android {
    namespace = "com.github.jspurim.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.github.jspurim.puzzleengine.app.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":app-shared"))
    implementation(libs.androidx.activity.compose)
    testImplementation(kotlin("test"))
}