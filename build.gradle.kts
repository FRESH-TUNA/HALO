import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.properties.*

plugins {
    kotlin("jvm") version "1.9.0"

    // for publishing
    id("maven-publish")
    id("signing")
}

group = "io.github.fresh-tuna"
version = "1.0.1-beta"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    gradlePluginPortal() // To use 'maven-publish' and 'signing' plugins in our own plugin
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


/**
 * new publishing codes
 */
// Stub secrets to let the project sync and build without the publication values set up
ext["signing.keyId"] = ""
ext["signing.password"] = ""
ext["signing.key"] = ""
ext["ossrhUsername"] = ""
ext["ossrhPassword"] = ""

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

fun getExtraString(name: String) = ext[name]?.toString()

fun groupId(): String = "io.github.fresh-tuna"

afterEvaluate {
    // Grabbing secrets from local.properties file or from environment variables, which could be used on CI
    val secretPropsFile = project.rootProject.file("local.properties")
    if (secretPropsFile.exists()) {
        secretPropsFile.reader().use {
            Properties().apply {
                load(it)
            }
        }.onEach { (name, value) ->
            ext[name.toString()] = value
        }
    } else {
        // Use system environment variables
        ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
        ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
        ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
        ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
        ext["signing.key"] = System.getenv("SIGNING_KEY")
    }

    // Set up Sonatype repository
    publishing {
        // Configure maven central repository
        repositories {
            maven {
                name = "sonatype"
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = getExtraString("ossrhUsername")
                    password = getExtraString("ossrhPassword")
                }
            }
        }

        // Configure all publications
        publications {
            create<MavenPublication>("staging") {
                groupId = groupId()
                artifactId = "halo"
                version = "1.0.1-beta"

                from(components.getByName("java"))

                artifact(sourcesJar.get())

                // Stub javadoc.jar artifact
                artifact(javadocJar.get())

                // Provide artifacts information requited by Maven Central
                pom {
                    name.set("HALO")
                    description.set("This project helps to create restful api (HAL style template) in java/kotlin")
                    url.set("https://github.com/FRESH-TUNA/HALO")

                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("fresh-tuna")
                            name.set("Dongwon Kim")
                            email.set("tunakim1004@gmail.com")
                        }
                    }
                    scm {
                        url.set("https://github.com/FRESH-TUNA/HALO.git")
                    }
                }
            }
        }
    }

    // Signing artifacts. Signing.* extra properties values will be used
    signing {
        useInMemoryPgpKeys(
            getExtraString("signing.keyId"),
            getExtraString("signing.key"),
            getExtraString("signing.password"),
        )
        sign(publishing.publications)
    }
}
