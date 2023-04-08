plugins {
    java
    kotlin("jvm")
    id("maven-publish")
}

dependencies {

    val jacksonVersion = property("jackson.version") as String

    implementation(project(":json-overlay"))
    implementation("com.wakaztahir:kate:1.0.7")

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")

}

fun RepositoryHandler.githubPackages(){
    maven("https://maven.pkg.github.com/Qawaz/K-OpenApi-Parser") {
        name = "GithubPackages"
        try {
            credentials {
                username = (System.getenv("GPR_USER")).toString()
                password = (System.getenv("GPR_API_KEY")).toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

subprojects {
    apply(plugin = "maven-publish")
    afterEvaluate {
        publishing {
            publications {
                register<MavenPublication>("release") {
                    from(components["kotlin"])
                }
            }
            repositories {
                githubPackages()
            }
        }
    }
}

allprojects {
    group = "com.wakaztahir.openapi"
    version = property("version") as String
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        mavenCentral()
        githubPackages()
    }
}