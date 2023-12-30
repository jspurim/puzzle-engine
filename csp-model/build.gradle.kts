import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

group = "com.github.jspurim.csp"
version = "1.0-SNAPSHOT"

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        jvmToolchain(17)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.6")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
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