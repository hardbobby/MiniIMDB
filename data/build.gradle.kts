plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)

    //Coroutines
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)

    api(Dependencies.runTimeRoom)
    implementation(Dependencies.extensionRoom)
    annotationProcessor(Dependencies.compilerRoom)
    kapt(Dependencies.compilerRoom)

    // Hilt
    implementation(Dependencies.hiltDagger)
    kapt(Dependencies.hiltDaggerCompiler)
    kapt(Dependencies.hiltCompiler)

}
