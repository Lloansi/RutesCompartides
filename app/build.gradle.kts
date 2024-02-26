plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.rutescompartidesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rutescompartidesapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.preference:preference-ktx:1.2.1")
    val voyagerVersion = "1.0.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Extended icons

    implementation("androidx.compose.material:material-icons-extended-android:1.6.0")

    // Collect Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Dagger-Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    // Map
    implementation("com.google.maps.android:maps-compose:1.0.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("org.osmdroid:osmdroid-android:6.1.16")
    implementation ("org.osmdroid:osmdroid-mapsforge:6.1.16")

    // Voyager
    // Navigator
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    // Transitions
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    // BottomNavigation
    implementation("com.github.PratikFagadiya:AnimatedSmoothBottomNavigation-JetpackCompose:1.1.1")
    implementation("com.canopas.compose-animated-navigationbar:bottombar:1.0.1")

    // Gif animation
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("io.coil-kt:coil-gif:2.5.0")

    // Activity animations
    implementation("com.github.AtifSayings:Animatoo:1.0.1")

    // CameraX
    val cameraxVersion = "1.3.0-rc01"

    implementation("androidx.camera:camera-core:$cameraxVersion")
    implementation("androidx.camera:camera-camera2:$cameraxVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
    implementation("androidx.camera:camera-video:$cameraxVersion")

    implementation("androidx.camera:camera-view:$cameraxVersion")
    implementation("androidx.camera:camera-extensions:$cameraxVersion")
}