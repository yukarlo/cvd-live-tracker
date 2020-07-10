plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
    daggerHilt
}

dependencies {
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    addDaggerDependencies()
}
