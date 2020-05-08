plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
}

dependencies {
    implementation(coreModule("main"))
    implementation(coreModule("domain-model"))
    implementation(coreModule("network"))
    api(coreModule("dispatchers"))
    api(coreModule("usecase"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_LIVEDATA_KTX)
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    addDaggerDependencies()
}