plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
}

dependencies {
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_VIEWMODEL_KTX)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_LIVEDATA_KTX)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_COMMON_JAVA8)
    implementation(LibraryDependency.KOTLIN_COROUTINES)
}
