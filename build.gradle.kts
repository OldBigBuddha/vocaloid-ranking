// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Kotlin.version}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Dependencies.Kotlin.version}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://kotlin.bintray.com/kotlinx")

    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}