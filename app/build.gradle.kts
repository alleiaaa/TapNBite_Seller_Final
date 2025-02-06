plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.tapnbiteseller"
    compileSdk = 35 

    defaultConfig {
        applicationId = "com.example.tapnbiteseller"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false // Consider enabling this for release builds
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
    implementation("androidx.cardview:cardview:1.0.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.fragment:fragment:1.6.1")
    implementation ("androidx.viewpager:viewpager:1.0.0")
    implementation ("com.google.android.material:material:1.9.0")
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}