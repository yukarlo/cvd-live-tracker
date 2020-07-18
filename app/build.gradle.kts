import com.android.build.gradle.internal.dsl.BaseFlavor

plugins {
    coroNowPlugin
    androidApplication
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
    daggerHilt
}

android {
    defaultConfig {
        buildConfigFieldFromGradleProperty("updateJsonChangelog")
    }
}

fun BaseFlavor.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".toUpperCase()
    buildConfigField("String", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Project
    implementation(project(":common-android"))

    // Core
    implementation(coreModule("base"))
    implementation(coreModule("dispatchers"))
    implementation(coreModule("domain-model"))
    implementation(coreModule("network"))
    implementation(coreModule("usecase"))

    // Stack
    implementation(stackModule("database"))
    implementation(stackModule("local-repository"))
    implementation(stackModule("remote-repository"))
    implementation(stackModule("usecase"))

    // UI or Feature
    implementation(featureModule("countries"))
    implementation(featureModule("home"))
    implementation(featureModule("preventive-measures"))
    implementation(featureModule("symptoms"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_UI_KTX)
    implementation(LibraryDependency.GOOGLE_MATERIAL)
    implementation(LibraryDependency.APP_UPDATER)

    addDaggerDependencies()
}