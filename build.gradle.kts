/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * User Manual available at https://docs.gradle.org/5.6.2/userguide/tutorial_java_projects.html
 */
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.bundling.BootWar
import org.springframework.boot.gradle.tasks.run.BootRun
import com.github.jengelman.gradle.plugins.shadow.ShadowExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the java plugin to add support for Java

    //application
    id("org.springframework.boot")  version "2.2.2.RELEASE"
    id("com.github.johnrengelman.shadow") version "4.0.1" apply false
}

allprojects {
    group = "com.eascapeco.sinemapr"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java-library")

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

project("scinemapr.core") {
    dependencies {
        "api"("org.apache.commons:commons-lang3:3.9")
        "compileOnly"("org.projectlombok:lombok")
        "annotationProcessor"("org.projectlombok:lombok")
    }

    tasks.getByName<Jar>("jar") {
        enabled = true
    }

}


project("scinemapr.api") {
    dependencies {
        "api"(project(":scinemapr.core"))

        "api"("org.springframework.boot:spring-boot-starter-web")
        "api"("org.springframework.boot:spring-boot-starter-aop")
        "api"("org.springframework.data:spring-data-commons")
        "api"("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.0")
        "api"("org.mariadb.jdbc:mariadb-java-client:2.5.0")
        "api"("org.springframework.boot:spring-boot-starter-security")
        "api"("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.1")
        "api"("io.jsonwebtoken:jjwt-api:0.11.2")
        "runtime"("io.jsonwebtoken:jjwt-impl:0.11.2")
        "runtime"("io.jsonwebtoken:jjwt-jackson:0.11.2")
        "compileOnly"("org.projectlombok:lombok")
        "annotationProcessor"("org.projectlombok:lombok")

        "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    tasks.getByName<Jar>("jar") {
        enabled = true
    }

}

project("scinemapr.bo") {
    apply(plugin = "war")

    dependencies {
        "api"(project(":scinemapr.api"))

        "api"("org.springframework.boot:spring-boot-starter-thymeleaf")
        //"api"("org.springframework.boot:spring-boot-starter-security")
        "runtime"("org.springframework.boot:spring-boot-starter-tomcat")
        "compileOnly"("org.projectlombok:lombok")
        "annotationProcessor"("org.projectlombok:lombok")

        "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    tasks.getByName<BootJar>("bootJar") {
        mainClassName = "com.eascapeco.scinemapr.bo.BoApplication"
    }

    tasks.getByName<BootWar>("bootWar") {
        mainClassName = "com.eascapeco.scinemapr.bo.BoApplication"
    }
/*
    tasks.getByName<BootRun>("bootRun") {
        sourceResources(sourceSets["main"])
    }
 */
}

