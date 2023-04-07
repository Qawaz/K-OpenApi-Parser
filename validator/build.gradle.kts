plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {

    val jacksonVersion = property("jackson.version") as String

    api(project(":parser"))

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")

    testImplementation("junit:junit:4.13.1")
    testImplementation("com.google.guava:guava:30.0-jre")
    testImplementation("org.skyscreamer:jsonassert:1.5.0")
    testImplementation("org.apache.commons:commons-lang3:3.7")

}