plugins {
    coroNowPlugin
    androidApplication
    kotlinAndroid
    kotlinAndroidExtensions
    kotlinKapt
    daggerHilt
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":common-android"))
    implementation(coreModule("network"))
    implementation(coreModule("dispatchers"))
    implementation(libModule("usecase"))
    implementation(libModule("database"))
    implementation(libModule("remote-repository"))
    implementation(libModule("local-repository"))
    implementation(featureModule("home"))
    implementation(featureModule("countries"))
    implementation(featureModule("symptoms"))
    implementation(featureModule("preventive-measures"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_UI_KTX)
    implementation(LibraryDependency.GOOGLE_MATERIAL)
    implementation(LibraryDependency.APP_UPDATER)

    addDaggerDependencies()
}