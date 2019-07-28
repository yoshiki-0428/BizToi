import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.3.31"
	kotlin("plugin.spring") version "1.3.31"
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
}

extra["springCloudVersion"] = "Hoxton.M1"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//	implementation("org.springframework.cloud:spring-cloud-starter-aws")
//	implementation("org.springframework.cloud:spring-cloud-starter-aws-jdbc")
//	implementation("org.springframework.cloud:spring-cloud-starter-aws-messaging")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("mysql:mysql-connector-java")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "junit", module = "junit")
	}
	testImplementation("org.springframework.security:spring-security-test")
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
