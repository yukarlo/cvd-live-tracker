plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
}

dependencies {
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    addDaggerDependencies()
}
