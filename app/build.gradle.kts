plugins {
    id("com.android.application")
}


android {
    namespace = "com.xxxy.no2.yulidressing"
    compileSdk = 33
    // 在MobSDK的扩展中注册SMSSDK的相关信息


    defaultConfig {
        applicationId = "com.xxxy.no2.yulidressing"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }

}


dependencies {
    implementation ("com.google.code.gson:gson:2.8.5")
    implementation ("com.squareup.okhttp3:okhttp:3.14.9")
    implementation ("com.squareup.okio:okio:3.0.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation("de.hdodenhof:circleimageview:2.2.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("com.google.firebase:firebase-components:17.1.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}



