// Gradle currently does not allow loading in the "plugins" or "buildscript" blocks
// declarations not in the root module.
// For a better package structure this calls should be in a smaller "ext" package
// but at the moment we need this hack to be able to publicly expose them
//
// See https://github.com/gradle/gradle/issues/9270 for details.
@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

fun DependencyHandler.addPluginDependencies() {
    classpath(dependencyNotation = GradlePlugin.ANDROID_TOOLS_BUILD_GRADLE)
    classpath(kotlin(module = GradlePlugin.KOTLIN_GRADLE_PLUGIN, version = LibraryVersion.KOTLIN))
    classpath(dependencyNotation = LibraryDependency.NAVIGATION_SAFE_ARGS)
    classpath(dependencyNotation = LibraryDependency.SQL_DELIGHT)
    classpath(dependencyNotation = GradlePlugin.DAGGER_HILT_ANDROID_GRADLE_PLUGIN)
}

fun DependencyHandler.addDaggerDependencies() {
    implement(LibraryDependency.DAGGER_HILT_ANDROID)
    implement(LibraryDependency.DAGGER_HILT)
    implement(LibraryDependency.DAGGER_HILT_LIFECYCLE_VIEWMODEL)
    kapt(LibraryDependency.DAGGER_HILT_ANDROID_COMPILER)
    kapt(LibraryDependency.DAGGER_HILT_COMPILER)
}

fun DependencyHandler.coreModule(moduleNotation: String) =
    project(":core:$moduleNotation")

fun DependencyHandler.libModule(moduleNotation: String) =
    project(":coronow-stack:$moduleNotation")

fun DependencyHandler.featureModule(moduleNotation: String) =
    project(":ui:$moduleNotation")

private fun DependencyHandler.implement(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

private fun DependencyHandler.classpath(dependencyNotation: Any): Dependency? =
    add("classpath", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)
