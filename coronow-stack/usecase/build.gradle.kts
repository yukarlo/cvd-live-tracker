plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
}

dependencies {
    implementation(coreModule("domain-model"))
    implementation(coreModule("network"))
    implementation(libModule("remote-repository"))
    implementation(libModule("local-repository"))
    api(coreModule("dispatchers"))
    api(coreModule("usecase"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_LIVEDATA_KTX)
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    addDaggerDependencies()
}