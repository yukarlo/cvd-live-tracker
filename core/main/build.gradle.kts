plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
    daggerHilt
}

dependencies {
    implementation(coreModule("network"))
    implementation(coreModule("dispatchers"))
    implementation(libModule("database"))
    implementation(libModule("remote-repository"))
    implementation(libModule("local-repository"))
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)

    addDaggerDependencies()
}
