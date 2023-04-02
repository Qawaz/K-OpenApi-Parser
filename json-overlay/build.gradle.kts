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
    implementation("commons-cli:commons-cli:1.4")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    testImplementation("junit:junit:4.13.1")

}