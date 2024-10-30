plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.numbergame"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.numbergame"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


    buildFeatures {
        viewBinding = true // если планируете использовать ViewBinding
    }
}

dependencies {
    implementation(libs.appcompat)           // Библиотека AppCompat для совместимости старых версий Android
    implementation(libs.material)            // Материальные компоненты Google
    implementation(libs.activity)            // Activity KTX для расширенной работы с Activity
    implementation(libs.constraintlayout)    // ConstraintLayout для адаптивных интерфейсов

    // Тестирование
    testImplementation(libs.junit)                  // Библиотека для юнит-тестов
    androidTestImplementation(libs.ext.junit)       // Android расширение для JUnit
    androidTestImplementation(libs.espresso.core)   // Espresso для UI-тестирования
}
