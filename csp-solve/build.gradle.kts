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
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        jvmToolchain(17)
    }
    js(IR) {
        browser()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":csp-model"))
                implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.6")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.framework.datatest)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }
    }
}

android {
    namespace = "com.github.jspurim.csp.solve"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}
