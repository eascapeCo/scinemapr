pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "scinemapr"

/**
 * scinemapr.core
 * scinemapr.api
 * scinemapr.bo
 */
include("scinemapr.api", "scinemapr.bo", "scinemapr.core")