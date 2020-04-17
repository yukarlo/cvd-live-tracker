plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":core"))
    implementation(project(":common-android"))
    implementation(libModule("cases"))
    implementation(coreModule("network"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_LIVEDATA_KTX)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_VIEWMODEL_KTX)
    implementation(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_UI_KTX)
    implementation(LibraryDependency.GOOGLE_MATERIAL)

    addDaggerDependencies()
}
