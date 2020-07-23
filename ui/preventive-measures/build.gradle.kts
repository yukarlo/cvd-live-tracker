plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":common-android"))

    implementation(coreModule("base"))
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.GOOGLE_MATERIAL)
}
