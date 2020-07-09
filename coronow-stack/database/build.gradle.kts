plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
    sqlDelight
    daggerHilt
}

dependencies {
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.KOTLIN_COROUTINES)
    implementation(LibraryDependency.SQL_DELIGHT_DRIVER)

    addDaggerDependencies()
}
