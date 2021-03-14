import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    id("org.jetbrains.compose") version "0.4.0-build173"
    id("com.squareup.sqldelight") version "1.4.4"
}

group = "me.zohaib"
version = "1.0.1"


sqldelight {
    database("Database") {
        packageName = "$group.${project.name}.model"
    }
}

compose.desktop {
    application {
        mainClass = "ComposeNotesDesktop"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = project.name
        }
    }
}
repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(compose.desktop.currentOs)

    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    // Persistence
    implementation("com.squareup.sqldelight:sqlite-driver:1.4.4")
    implementation("com.squareup.sqldelight:coroutines-extensions-jvm:1.4.4")

}




tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
    @Suppress("SuspiciousCollectionReassignment")
    kotlinOptions.freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn", "-Xinline-classes")
    kotlinOptions.useIR = true
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ComposeNotesDesktop"
        }
    }
}