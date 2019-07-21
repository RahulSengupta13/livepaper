plugins {
    kotlin("jvm")
}


dependencies {
    compile(kotlin("stdlib"))
    implementation("com.squareup.okhttp3:okhttp:3.14.2")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.14.2")
    implementation("com.squareup.retrofit2:converter-gson:2.6.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.5.0")
}