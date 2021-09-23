plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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

    implementation(project(":data"))
    implementation(project(":domain"))


    //Shimmer
    implementation(Dependencies.shimmer)
    //Coroutines
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)

    implementation(Dependencies.coreAndroidx)
    implementation(Dependencies.appCompactAndroidx)

    implementation(Dependencies.materialDesign)
    implementation(Dependencies.constraintLayout)

    // Hilt
    implementation(Dependencies.hiltDagger)
    implementation(Dependencies.legacySupport)
    kapt(Dependencies.hiltDaggerCompiler)
    implementation(Dependencies.hiltDaggerViewModel)
    kapt(Dependencies.hiltCompiler)

    // Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.okHttpInterceptor)

    // Fragment KTX
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.fragmentKtx)

    //LifeCycle Common
    implementation(Dependencies.lifeCycleCommonAndroidx)
    implementation(Dependencies.lifeCycleViewModel)

    //Glide
    implementation(Dependencies.bumptechGlideLibs)
    kapt(Dependencies.bumptechGlideCompilerLibs)
    implementation(Dependencies.glideTransformationLibs)

    //ViewPager
    implementation("androidx.viewpager2:viewpager2:1.1.0-alpha01")

}