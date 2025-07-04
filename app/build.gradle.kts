plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // Testing
    alias(libs.plugins.kover)

    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "kr.co.hs.data"
    compileSdk = 35

    defaultConfig {
        applicationId = "kr.co.hs.data"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    apply(plugin = libs.plugins.junit5.robolectric.get().pluginId)
}

dependencies {

    implementation(libs.ktor.serialization.kotlinx.json)

    // Clean Architecture
    implementation(project(":data"))

    // Testing
    androidTestImplementation(libs.ui.test.junit4.android)
    testImplementation(libs.ui.test.junit4.android)
    testImplementation(libs.ui.test.manifest)
    testImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}