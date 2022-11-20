
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}



kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
//                implementation("androidx.constraintlayout:constraintlayout:2.1.4")
                // To use constraintlayout in compose
//                implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
//                implementation("org.jetbrains.compose.material3:material3-desktop:1.2.1")
//                implementation("androidx.compose.material3:material3-window-size-class:1.0.1")
                implementation(compose.desktop.currentOs)
                implementation("io.github.androidpoet:dropdown:1.0.1")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "demo"
            packageVersion = "1.0.0"
        }
    }
}
