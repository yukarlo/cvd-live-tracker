plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
    sqlDelight
    daggerHilt
}

dependencies {
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.SQL_DELIGHT_DRIVER)

    addDaggerDependencies()
}
