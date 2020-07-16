plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
}

dependencies {
    implementation(LibraryDependency.ANDROID_LIFECYCLE_VIEWMODEL_KTX)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_LIVEDATA_KTX)
    implementation(LibraryDependency.KOTLIN_COROUTINES)
}
