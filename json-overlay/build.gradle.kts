import java.util.Properties;
import java.io.FileInputStream;

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
    implementation("com.github.javaparser:javaparser-core:3.5.7")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.eclipse.xtend:org.eclipse.xtend.lib:2.17.1")
    testImplementation("junit:junit:4.12")

}