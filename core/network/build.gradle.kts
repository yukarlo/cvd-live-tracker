plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
}

dependencies {
    implementation(coreModule("domain-model"))
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    api(LibraryDependency.RETROFIT)
    api(LibraryDependency.RETROFIT_ADAPTER_RXJAVA2)
    api(LibraryDependency.MOSHI_CONVERTER)
    api(LibraryDependency.RETROFIT_CONVERTER_GSON)
    api(LibraryDependency.OKHTTP3_LOGGING)

    addDaggerDependencies()
}
