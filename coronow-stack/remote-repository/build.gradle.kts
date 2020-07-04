plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
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
