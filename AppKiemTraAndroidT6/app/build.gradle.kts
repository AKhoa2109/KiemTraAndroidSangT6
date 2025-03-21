plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.android.projectnhom"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.android.projectnhom"
        minSdk = 24
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.retrofit) // Retrofit core
    implementation(libs.converterGson) // Convert JSON sang Object Java
    implementation(libs.gson)
    implementation(libs.okhttp.logging)
    implementation(libs.glide)
    implementation(libs.viewpager2)
    implementation(libs.circleimageview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor(libs.glideCompiler)
}