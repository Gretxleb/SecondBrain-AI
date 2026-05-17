plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.secondbrain.ai.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
        targetSdk = 35
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.coroutines.core)
    implementation(libs.androidx.coroutines.android)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.security.crypto)
    implementation(libs.timber)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
