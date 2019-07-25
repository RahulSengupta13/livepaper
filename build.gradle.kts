buildscript {
    val kotlinVersion = "1.3.40"
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:3.4.2")
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0")
        classpath (kotlin("gradle-plugin", kotlinVersion))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
