plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
}

dependencies {
    implementation(libModule("database"))
    implementation(coreModule("domain-model"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.KOTLIN_COROUTINES)
    implementation(LibraryDependency.SQL_DELIGHT_COROUTINES)

    addDaggerDependencies()
}
