plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
}

dependencies {
    implementation(coreModule("domain-model"))
    implementation(coreModule("network"))
    implementation(coreModule("usecase"))
    implementation(coreModule("dispatchers"))
    implementation(stackModule("remote-repository"))
    implementation(stackModule("local-repository"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_LIVEDATA_KTX)
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    addDaggerDependencies()
}