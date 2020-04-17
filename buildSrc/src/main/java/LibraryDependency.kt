object LibraryVersion {
    const val KOTLIN = "1.4-M1"
    const val APP_COMPACT = "1.1.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.1.0"
    const val ANDROID_NAVIGATION = "2.2.1"
    const val GOOGLE_MATERIAL = "1.1.0"
    const val DAGGER = "2.26"
    const val ANDROID_LIFECYCLE = "2.2.0"
    const val RECYCLERVIEW = "1.2.0-alpha01"
    const val ROOM = "2.2.4"
    const val RETROFIT = "2.7.1"
    const val GSON = "2.8.6"
    const val COROUTINES = "1.3.4"
    const val OKHTTP3 = "4.4.0"
    const val MOSHI = "2.7.1"
    const val PAGING = "2.1.1"
    const val COIL = "0.9.5"
}

object LibraryDependency {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${LibraryVersion.KOTLIN}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
    const val APP_COMPACT = "androidx.appcompat:appcompat:${LibraryVersion.APP_COMPACT}"
    const val CORE_KTX = "androidx.core:core-ktx:${LibraryVersion.CORE_KTX}"
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${LibraryVersion.ANDROID_NAVIGATION}"
    const val NAVIGATION_UI_KTX =
        "androidx.navigation:navigation-ui-ktx:${LibraryVersion.ANDROID_NAVIGATION}"
    const val GOOGLE_MATERIAL =
        "com.google.android.material:material:${LibraryVersion.GOOGLE_MATERIAL}"
    const val DAGGER = "com.google.dagger:dagger:${LibraryVersion.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${LibraryVersion.DAGGER}"
    // ViewModel and LiveData
    const val ANDROID_LIFECYCLE_EXTENSIONS =
        "androidx.lifecycle:lifecycle-extensions:${LibraryVersion.ANDROID_LIFECYCLE}"
    // alternatively - just ViewModel
    const val ANDROID_LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.ANDROID_LIFECYCLE}"
    const val ANDROID_LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${LibraryVersion.ANDROID_LIFECYCLE}"
    const val ANDROID_RECYCLERVIEW =
        "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLERVIEW}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val RETROFIT_ADAPTER_RXJAVA2 =
        "com.squareup.retrofit2:adapter-rxjava2:${LibraryVersion.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON =
        "com.squareup.retrofit2:converter-gson:${LibraryVersion.RETROFIT}"
    const val GOOGLE_GSON = "com.google.code.gson:gson:${LibraryVersion.GSON}"
    const val KOTLIN_COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${LibraryVersion.COROUTINES}"
    const val OKHTTP3_LOGGING = "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.OKHTTP3}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${LibraryVersion.MOSHI}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${LibraryVersion.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${LibraryVersion.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${LibraryVersion.ROOM}"
    const val PAGING_RUNTIME = "androidx.paging:paging-runtime:${LibraryVersion.PAGING}"
    const val PAGING_RUNTIME_KTX = "androidx.paging:paging-runtime-ktx:${LibraryVersion.PAGING}"
    const val COIL_KT = "io.coil-kt:coil:${LibraryVersion.COIL}"
}