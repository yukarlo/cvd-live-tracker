plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
    daggerHilt
}

dependencies {
    implementation(coreModule("domain-model"))
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    api(LibraryDependency.RETROFIT)
    api(LibraryDependency.RETROFIT_ADAPTER_RXJAVA2)
    api(LibraryDependency.RETROFIT_CONVERTER_GSON)
    api(LibraryDependency.OKHTTP3_LOGGING)

    addDaggerDependencies()
}
