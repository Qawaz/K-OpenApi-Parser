plugins {
    kotlin("jvm")
}

dependencies {

    val jacksonVersion = property("jackson.version") as String

    implementation(project(":json-overlay"))
    implementation("com.wakaztahir:kate:1.0.1")

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")

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