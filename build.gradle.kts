import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.8.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.graalvm.buildtools.native") version "0.9.20"
}

group = "com.severett"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

apply(plugin = "kotlin")
apply(plugin = "kotlin-jpa")
apply(plugin = "kotlin-spring")
apply(plugin = "kotlinx-serialization")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

tasks {
    val jvmVersion = "17"
    val kotlinJvmTarget = "JVM_$jvmVersion"
    withType<JavaCompile> {
        sourceCompatibility = jvmVersion
        targetCompatibility = jvmVersion
    }
    withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.valueOf(kotlinJvmTarget))
        }
    }
    withType<BootJar> {
        manifest {
            attributes["Build-Jdk-Spec"] = jvmVersion
        }
    }
    test {
        useJUnitPlatform()
    }
}

dependencies {
    val kotlinCoroutinesVersion: String by project
    //// Production Dependencies
    // Implementation
    implementation(kotlin("stdlib"))
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    // Runtime
    runtimeOnly(kotlin("reflect"))
    runtimeOnly("com.h2database:h2:2.1.214")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinCoroutinesVersion")
    //// Testing Dependencies
    // Implementation
    testImplementation(kotlin("test"))
}
