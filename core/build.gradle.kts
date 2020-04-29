plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
}

dependencies {
    implementation(coreModule("network"))
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)

    addDaggerDependencies()
}
