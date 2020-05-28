// Gradle currently does not allow loading in the "plugins" or "buildscript" blocks
// declarations not in the root module.
// For a better package structure this calls should be in a smaller "ext" package
// but at the moment we need this hack to be able to publicly expose them
//
// See https://github.com/gradle/gradle/issues/9270 for details.
@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

fun RepositoryHandler.addDefaults() {
    addDefaultRepositories(this)
}

fun addDefaultRepositories(handler: RepositoryHandler) {
    handler.apply {
        mavenCentral()
        jcenter()
        google()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://jitpack.io")
    }
}