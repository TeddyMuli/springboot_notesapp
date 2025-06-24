plugins {
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Core Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-web")           // Web MVC
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")     // ORM - includes Hibernate
	implementation("org.springframework.boot:spring-boot-starter-validation")   // Bean validation

	// SQLite Database Dependencies
	implementation("org.xerial:sqlite-jdbc:3.46.1.3")
	implementation("org.postgresql:postgresql")
	implementation("org.hibernate.orm:hibernate-community-dialects:6.6.1.Final") // SQLite dialect for Hibernate

	// Development Tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")           // Hot reload

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Optional: H2 for testing (if you want a different test database)
	testImplementation("com.h2database:h2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}