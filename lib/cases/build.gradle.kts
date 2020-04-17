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

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_LIVEDATA_KTX)
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    addDaggerDependencies()
}