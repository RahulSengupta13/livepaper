plugins {
    kotlin("jvm")
}


dependencies {
    compile(kotlin("stdlib"))
    api("com.squareup.okhttp3:okhttp:3.14.2")
    api("com.google.code.gson:gson:2.8.5")
    api("com.squareup.retrofit2:retrofit:2.6.0")
    api("com.squareup.okhttp3:logging-interceptor:3.14.2")
    api("com.squareup.retrofit2:converter-gson:2.6.0")
    api("com.squareup.retrofit2:converter-scalars:2.6.0")
    api("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
}