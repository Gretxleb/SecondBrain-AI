pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "8.5.0"
        id("com.android.library") version "8.5.0"
        id("org.jetbrains.kotlin.android") version "1.9.20"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
        id("dagger.hilt.android.plugin") version "2.47"
        id("com.google.gms.google-services") version "4.4.0"
        id("com.google.firebase.crashlytics") version "2.9.8"
    }
}
rootProject.name = "SecondBrainAI"
include(
    ":app",
    ":core",
    ":data",
    ":domain",
    ":feature_auth",
    ":feature_notes",
    ":feature_ai",
    ":feature_search",
    ":feature_settings",
    ":feature_voice",
    ":feature_reminders"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}
