plugins {
    java
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.0"))
    testImplementation(platform("io.cucumber:cucumber-bom:7.6.0"))

    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("io.rest-assured:rest-assured:5.1.1")
    testImplementation("com.fasterxml.jackson.core:jackson-core:2.13.3")
    testImplementation("com.fasterxml.jackson.core:jackson-annotations:2.13.3")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")

    testImplementation("io.github.bonigarcia:selenium-jupiter:4.3.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.4.0")
    testImplementation("org.junit.platform:junit-platform-console:1.9.0")

    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

repositories {
    mavenLocal()
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}
