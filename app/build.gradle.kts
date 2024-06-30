plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    //Room ksp
    id("com.google.devtools.ksp")

}

android {
    namespace = "cl.talentodigital.alkewallet"
    compileSdk = 34

    defaultConfig {
        applicationId = "cl.talentodigital.alkewallet"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }

}

dependencies {
    //Implementacion dependecias para manejo para API
    implementation (libs.kotlinx.coroutines.android)
        // Retrofit
    implementation (libs.retrofit)
        // Conversor
    implementation (libs.converter.gson)

    implementation(libs.logging.interceptor)

        // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
        //LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Dependencia de Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
}