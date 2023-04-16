plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.8.10"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":parser"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    api("com.charleskorn.kaml:kaml:0.53.0")

}