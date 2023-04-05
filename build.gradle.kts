plugins {
    java
    kotlin("jvm")
}

dependencies {

    val jacksonVersion = property("jackson.version") as String

    api(project(":parser"))
    implementation(project(":json-overlay"))

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("javax.mail:javax.mail-api:1.6.1")
    implementation("com.sun.mail:javax.mail:1.6.1")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    testImplementation("junit:junit:4.13.1")
    testImplementation("com.google.guava:guava:30.0-jre")
    testImplementation("org.skyscreamer:jsonassert:1.5.0")
    testImplementation("org.apache.commons:commons-lang3:3.7")
}

allprojects {
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        mavenCentral()
        maven("https://maven.pkg.github.com/Qawaz/K-OpenApi-Parser") {
            name = "GitHubPackages"
            try {
                credentials {
                    username = System.getenv("GPR_USER").toString()
                    password = System.getenv("GPR_API_KEY").toString()
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}