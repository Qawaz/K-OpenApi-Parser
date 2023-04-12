plugins {
    kotlin("jvm")
    id("maven-publish")
    kotlin("plugin.serialization") version "1.8.10"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    testImplementation("junit:junit:4.13.1")

}