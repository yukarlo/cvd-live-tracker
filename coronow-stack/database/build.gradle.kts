plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
    sqlDelight
    daggerHilt
}

sqldelight {
    database("cvdDatabase") {
        packageName = "com.yukarlo.coronow"
        sourceFolders = listOf("sqldelight")
        schemaOutputDirectory = file("src/main/sqldelight")
        dialect = "sqlite:3.24"
    }
}

dependencies {
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.SQL_DELIGHT_DRIVER)

    addDaggerDependencies()
}
