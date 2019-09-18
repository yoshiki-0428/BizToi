import com.rohanprabhu.gradle.plugins.kdjooq.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.util.GenerationTool
import org.jooq.util.jaxb.*
import org.jooq.util.jaxb.Target

plugins {
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	id("org.flywaydb.flyway") version "6.0.1"
	id("com.rohanprabhu.kotlin-dsl-jooq") version "0.3.1"

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

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

flyway {
	url = "jdbc:mysql://localhost:3307/BizToi"
	user = "biztoi"
	password = "biztoi"
	schemas = arrayOf("BizToi")
}

jooqGenerator {
	jooqEdition = JooqEdition.OpenSource
	jooqVersion = "3.10.1"
	configuration("primary", sourceSet = sourceSets["main"]) {

		val conf = jooqCodegenConfiguration {
			jdbc = Jdbc()
					.withDriver("com.mysql.cj.jdbc.Driver")
					.withUsername("biztoi")
					.withPassword("biztoi")
					.withUrl("jdbc:mysql://localhost:3307/BizToi")
					.withSchema("BizToi")

			generator = Generator()
					.withName("org.jooq.util.DefaultGenerator")
					.withStrategy(Strategy().withName("org.jooq.util.DefaultGeneratorStrategy"))
					.withDatabase(
							Database()
									.withInputSchema("BizToi")
					)
					.withGenerate(
							Generate()
									.withRelations(true)
									.withDeprecated(true)
									.withRecords(true)
									.withDaos(true)
									.withInterfaces(false)
									.withImmutablePojos(true)
									.withFluentSetters(true)
					)
					.withTarget(
							Target()
									.withPackageName("com.yoshikiohashi.biztoi")
									.withDirectory("${project.buildDir}/generated/jooq/primary")
					)
		}
		configuration = conf
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
	jcenter()
	maven("https://repo.spring.io/milestone")
	maven("https://oss.sonatype.org/content/repositories/snapshots")
	maven("https://plugins.gradle.org/m2/")
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
	implementation("org.jooq:jooq")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("mysql:mysql-connector-java")
	jooqGeneratorRuntime("mysql:mysql-connector-java")

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
