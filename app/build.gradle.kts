plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "net.oldbigbuddha.vocaloidranking"
        minSdkVersion( 21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.ktxCore)
    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.AndroidX.cardView)
    implementation(Dependencies.material)

    implementation(Dependencies.Fuel.core)
    implementation(Dependencies.Fuel.android)

    implementation(Dependencies.Moshi.core)
    implementation(Dependencies.Moshi.kotlin)

    implementation(Dependencies.KotlinX.Serialization.runtime)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.Android.runner)
    androidTestImplementation(Dependencies.Test.Android.espresso)
}
