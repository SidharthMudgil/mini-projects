plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.bhaskarblur.webtalk"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.bhaskarblur.webtalk"
        minSdk = 26
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
    buildFeatures {
        viewBinding  = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.0")
    implementation ("com.mesibo.api:webrtc:1.0.5")
    implementation ("com.google.android.flexbox:flexbox:3.0.0")
    // implementation 'me.bendik.simplerangeview:simplerangeview:0.2.0'
    implementation("com.google.android.material:material:1.7.0")
    implementation("com.google.firebase:firebase-messaging-directboot:23.1.0")
    implementation ("com.google.firebase:firebase-messaging:23.1.0")
    implementation ("com.google.firebase:firebase-core:21.1.1")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation("com.vanniktech:android-image-cropper:4.5.0")
    //implementation 'com.theartofdev.edmodo:android-image-cropper:2.8+'
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")// compulsory
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") //for retrofit conversion
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("jp.wasabeef:picasso-transformations:2.4.0")
    implementation ("androidx.multidex:multidex:2.0.1")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    // Retrofit & OkHttp
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation ("com.squareup.okhttp3:okhttp:4.9.2")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    val lifecycle_version = "2.6.1"
    val arch_version = "2.1.0"
    implementation("androidx.core:core:1.10.1")

    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.19")
    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-android-compiler:2.44")
    kapt ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")
    implementation ("com.google.dagger:dagger-android-support:2.42")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
