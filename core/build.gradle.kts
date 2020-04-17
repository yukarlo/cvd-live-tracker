plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
}

dependencies {
    implementation(stackModule("network"))
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)

    addDaggerDependencies()
}