plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.8.10"
}

repositories {
    mavenCentral()
}

dependencies {

    api(project(":parser"))


    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    testImplementation(project(":yaml-parser"))

    testImplementation("junit:junit:4.13.1")
    testImplementation("com.google.guava:guava:30.0-jre")
    testImplementation("org.skyscreamer:jsonassert:1.5.0")
    testImplementation("org.apache.commons:commons-lang3:3.7")

}