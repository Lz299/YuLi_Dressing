// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
}
allprojects {
    repositories {

        //build.gradle 的Project下导入
        maven { url= uri("https://jitpack.io") }
    }
}

