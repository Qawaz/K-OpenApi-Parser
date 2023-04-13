plugins {
    java
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.8.10"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    api(project(":json-overlay"))

}