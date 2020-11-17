plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    androidSafeArgs
    kotlinKapt
}

android {
    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.GOOGLE_MATERIAL)
    implementation(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_UI_KTX)
    implementation(LibraryDependency.WILLIAM_CHART)
    api(LibraryDependency.CIRCULAR_IMAGE_VIEW)

    implementation(LibraryDependency.Compose.UI)
    implementation(LibraryDependency.Compose.FOUNDATION)
    implementation(LibraryDependency.Compose.LAYOUT)
    implementation(LibraryDependency.Compose.MATERIAL)
    implementation(LibraryDependency.Compose.RUNTIME)
    implementation(LibraryDependency.Compose.UI_TOOLING)
    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.3.3.1")

    addDaggerDependencies()
}
