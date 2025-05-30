plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "io.github.mikan.tart"
    compileSdk = 35

    defaultConfig {
        applicationId = "io.github.mikan.tart"
        minSdk = 26
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-XXLanguage:+WhenGuards")
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.domain)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidxLifecycleViewModelCompose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.tartCore)
    implementation(libs.tartCompose)
    implementation(libs.coilCompose)
    implementation(libs.coilNetworkOkhttp)
    implementation(libs.composeMarkdown)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidxNavigationCompose)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.kotlinxSerializationJson)
    implementation(libs.hiltAndroid)
    implementation(libs.kotlinxDateTime)
    ksp(libs.hiltCompiler)
    testImplementation(libs.coroutinesTest)
    testImplementation(kotlin("test"))
}
