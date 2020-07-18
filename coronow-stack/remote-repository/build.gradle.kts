plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
    daggerHilt
}

dependencies {
    implementation(coreModule("network"))
    implementation(coreModule("domain-model"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    addDaggerDependencies()
}
