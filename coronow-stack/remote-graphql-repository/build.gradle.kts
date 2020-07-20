plugins {
    coroNowPlugin
    androidLibrary
    kotlinAndroid
    kotlinKapt
    daggerHilt
    id("com.apollographql.apollo")
}

apollo {
    // instruct the compiler to generate Kotlin models
    generateKotlinModels.set(true)
}

dependencies {
    implementation(coreModule("network"))
    implementation(coreModule("domain-model"))

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.KOTLIN_COROUTINES)

    implementation(LibraryDependency.GRAPHQL_RUNTIME)
    implementation(LibraryDependency.GRAPHQL_COROUTINES)

    addDaggerDependencies()
}
