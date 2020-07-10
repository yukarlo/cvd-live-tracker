plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
    daggerHilt
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(coreModule("base"))
    implementation(coreModule("domain-model"))
    implementation(coreModule("usecase"))
    implementation(project(":common-android"))
    implementation(stackModule("usecase"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_LIVEDATA_KTX)
    implementation(LibraryDependency.ANDROID_LIFECYCLE_VIEWMODEL_KTX)
    implementation(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_UI_KTX)
    implementation(LibraryDependency.GOOGLE_MATERIAL)
    implementation(LibraryDependency.ADAPTER_DELEGATE)
    implementation(LibraryDependency.COIL_KT)
    implementation(LibraryDependency.ANDROID_RECYCLERVIEW)
    implementation(LibraryDependency.WILLIAM_CHART)

    addDaggerDependencies()
}
