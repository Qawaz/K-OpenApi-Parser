plugins {
    java
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {

    val jacksonVersion = property("jackson.version") as String

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("com.wakaztahir:kate:1.0.0")
    testImplementation("junit:junit:4.13.1")

}