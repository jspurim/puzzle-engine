import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

group = "com.github.jspurim.csp"
version = "1.0-SNAPSHOT"

kotlin {
    androidTarget()
    jvm("desktop") {
        jvmToolchain(17)
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.github.jspurim.csp.model"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}