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

    implementation("com.apollographql.apollo:apollo-runtime:2.2.2")
    // optional: if you want to use the normalized cache
    implementation("com.apollographql.apollo:apollo-normalized-cache-sqlite:2.2.2")
    // optional: for coroutines support
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.2.2")

    addDaggerDependencies()
}
