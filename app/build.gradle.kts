plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "me.rahulsengupta.livepaper"
        minSdkVersion(24)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.40")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.2.0-alpha02")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.2.2")
    implementation("com.jakewharton.timber:timber:4.7.1")

    //paging
    implementation("androidx.paging:paging-runtime-ktx:2.1.0")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime:2.0.0")
    implementation("androidx.lifecycle:lifecycle-compiler:2.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0")

    //navigation
    implementation("androidx.navigation:navigation-runtime:2.1.0-beta02")
    implementation("androidx.navigation:navigation-runtime-ktx:2.1.0-beta02")
    implementation("androidx.navigation:navigation-fragment:2.1.0-beta02")
    implementation("androidx.navigation:navigation-fragment-ktx:2.1.0-beta02")
    implementation("androidx.navigation:navigation-ui:2.1.0-beta02")
    implementation("androidx.navigation:navigation-ui-ktx:2.1.0-beta02")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0-beta02")

    //material
    implementation("com.google.android.material:material:1.1.0-alpha08")
}
