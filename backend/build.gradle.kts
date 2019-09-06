import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("kapt") version "1.3.50"
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
	maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

val compileKotlin: KotlinCompile by tasks

kapt {
	useBuildCache = true
	arguments {
		arg("doma.resources.dir", compileKotlin.destinationDir)
	}
}

extra["springCloudVersion"] = "Hoxton.M1"

dependencies {

	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
	implementation("com.nimbusds:nimbus-jose-jwt:5.12")
	implementation("com.amazonaws:aws-java-sdk-cognitoidp")
	compile("org.seasar.doma.boot:doma-spring-boot-starter:1.1.1")
	kapt("org.seasar.doma:doma:2.25.1")
	implementation("org.seasar.doma:doma:2.25.1")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("mysql:mysql-connector-java")
	annotationProcessor("org.projectlombok:lombok")
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

tasks.register("copyDomaResources", Sync::class){
	from("src/main/resources")
	into(compileKotlin.destinationDir)
	include("doma.compile.config")
	include("META-INF/**/*.sql")
	include("META-INF/**/*.script")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	dependsOn(tasks.getByName("copyDomaResources"))
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
