plugins {
    java
    kotlin("jvm").version("1.8.0")
}

dependencies {

    val jacksonVersion = "2.9.8"
    val jsonOverlayVersion = "4.0.4"

    implementation("com.reprezen.jsonoverlay:jsonoverlay:$jsonOverlayVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("javax.mail:javax.mail-api:1.6.1")
    implementation("com.sun.mail:javax.mail:1.6.1")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    testImplementation("junit:junit:4.12")
    testImplementation("com.google.guava:guava:19.0")
    testImplementation("org.skyscreamer:jsonassert:1.5.0")
    testImplementation("org.apache.commons:commons-lang3:3.7")
}

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    mavenCentral()
}