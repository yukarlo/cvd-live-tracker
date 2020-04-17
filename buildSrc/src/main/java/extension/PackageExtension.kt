// Gradle currently does not allow loading in the "plugins" or "buildscript" blocks
// declarations not in the root module.
// For a better package structure this calls should be in a smaller "ext" package
// but at the moment we need this hack to be able to publicly expose them
//
// See https://github.com/gradle/gradle/issues/9270 for details.
@file:Suppress("PackageDirectoryMismatch")

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.coroNowPlugin: PluginDependencySpec
    get() = id(GradleCustomPlugin.CORONOW_PLUGIN)

val PluginDependenciesSpec.androidApplication: PluginDependencySpec
    get() = id(GradlePluginId.ANDROID_APPLICATION)

val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
    get() = id(GradlePluginId.ANDROID_LIBRARY)

val PluginDependenciesSpec.javaLibrary: PluginDependencySpec
    get() = id(GradlePluginId.JAVA_LIBRARY)

val PluginDependenciesSpec.kotlinAndroid: PluginDependencySpec
    get() = kotlin(GradlePluginId.KOTLIN_ANDROID)

val PluginDependenciesSpec.kotlinJvm: PluginDependencySpec
    get() = kotlin(GradlePluginId.KOTLIN_JVM)

val PluginDependenciesSpec.kotlin: PluginDependencySpec
    get() = id(GradlePluginId.KOTLIN)

val PluginDependenciesSpec.kotlinAndroidExtensions: PluginDependencySpec
    get() = kotlin(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)

val PluginDependenciesSpec.kotlinKapt: PluginDependencySpec
    get() = kotlin(GradlePluginId.KOTLIN_KAPT)
