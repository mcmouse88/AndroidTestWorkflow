plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.mcmouse88.hrenapplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mcmouse88.hrenapplication"
        minSdk = 24
        targetSdk = 33
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.airbnb.android:lottie:6.0.1")
    testImplementation("io.mockk:mockk:1.12.4")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines
    val coroutine_version = "1.7.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")

    // LifeCycle
    val lifecycle_version = "2.6.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    testImplementation("app.cash.turbine:turbine:0.7.0")

    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")

    val koinVersion = "3.5.0"
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")

    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48")

    val navVersion = "2.6.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
}

tasks.register("updateVersionCode") {

}