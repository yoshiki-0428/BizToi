import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	id("org.flywaydb.flyway") version "6.0.1"

	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	java
	idea
}

apply(plugin = "idea")
idea {
	module {
		outputDir = file("build/classes/main")
		testOutputDir = file("build/classes/test")
	}
}

flyway {
	url = "jdbc:mysql://localhost:3307/BizToi"
	user = "biztoi"
	password = "biztoi"
	schemas = arrayOf("BizToi")
}

group = "com.yoshikiohashi"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

extra["springCloudVersion"] = "Hoxton.M1"

dependencies {
	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")

	// Spring
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-security")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")

	// db
	runtimeOnly("mysql:mysql-connector-java")
	implementation("org.springframework.boot:spring-boot-starter-jooq")

	// Other
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
	implementation("com.amazonaws:aws-java-sdk-cognitoidp")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.nimbusds:nimbus-jose-jwt:5.12")

	// Test tool
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "junit", module = "junit")
	}
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
