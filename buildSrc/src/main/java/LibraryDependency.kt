internal object LibraryVersion {
    internal const val KOTLIN = "1.4.0"
    internal const val APP_COMPACT = "1.3.0-alpha01"
    internal const val CONSTRAINT_LAYOUT = "2.0.0-beta8"
    internal const val CORE_KTX = "1.5.0-alpha01"
    internal const val ANDROID_NAVIGATION = "2.3.0"
    internal const val GOOGLE_MATERIAL = "1.3.0-alpha01"
    internal const val SWIPE_REFRESH_LAYOUT = "1.1.0"
    internal const val DAGGER_HILT_ANDROID = "2.28.1-alpha"
    internal const val DAGGER_HILT = "1.0.0-alpha01"
    internal const val ANDROID_LIFECYCLE = "2.3.0-alpha05"
    internal const val RECYCLERVIEW = "1.2.0-alpha04"
    internal const val RETROFIT = "2.9.0"
    internal const val GSON = "2.8.6"
    internal const val COROUTINES = "1.3.7"
    internal const val OKHTTP3 = "4.7.2"
    internal const val MOSHI = "2.9.0"
    internal const val PAGING = "3.0.0-alpha02"
    internal const val COIL = "0.11.0"
    internal const val ADAPTER_DELEGATE = "4.3.0"
    internal const val WILLIAM_CHART = "3.7.1"
    internal const val CIRCULAR_IMAGE_VIEW = "3.1.0"
    internal const val SQL_DELIGHT = "1.4.0"
    internal const val APP_UPDATER = "2.7"
}

object LibraryDependency {
    // region Kotlin

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${LibraryVersion.KOTLIN}"
    const val KOTLIN_COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${LibraryVersion.COROUTINES}"

    // endregion

    // region Navigation
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${LibraryVersion.ANDROID_NAVIGATION}"
    const val NAVIGATION_UI_KTX =
        "androidx.navigation:navigation-ui-ktx:${LibraryVersion.ANDROID_NAVIGATION}"
    // endregion

    // region Dagger Hilt

    const val DAGGER_HILT_ANDROID =
        "com.google.dagger:hilt-android:${LibraryVersion.DAGGER_HILT_ANDROID}"
    const val DAGGER_HILT_ANDROID_COMPILER =
        "com.google.dagger:hilt-android-compiler:${LibraryVersion.DAGGER_HILT_ANDROID}"
    const val DAGGER_HILT = "androidx.hilt:hilt-common:${LibraryVersion.DAGGER_HILT}"
    const val DAGGER_HILT_LIFECYCLE_VIEWMODEL =
        "androidx.hilt:hilt-lifecycle-viewmodel:${LibraryVersion.DAGGER_HILT}"
    const val DAGGER_HILT_COMPILER = "androidx.hilt:hilt-compiler:${LibraryVersion.DAGGER_HILT}"

    // endregion

    // region Lifecycle

    const val ANDROID_LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.ANDROID_LIFECYCLE}"
    const val ANDROID_LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${LibraryVersion.ANDROID_LIFECYCLE}"
    const val ANDROID_LIFECYCLE_COMMON_JAVA8 =
        "androidx.lifecycle:lifecycle-common-java8:${LibraryVersion.ANDROID_LIFECYCLE}"

    // endregion

    // region Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val RETROFIT_ADAPTER_RXJAVA2 =
        "com.squareup.retrofit2:adapter-rxjava2:${LibraryVersion.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON =
        "com.squareup.retrofit2:converter-gson:${LibraryVersion.RETROFIT}"

    // endregion

    // region Paging

    const val PAGING_RUNTIME = "androidx.paging:paging-runtime:${LibraryVersion.PAGING}"
    const val PAGING_RUNTIME_KTX = "androidx.paging:paging-runtime-ktx:${LibraryVersion.PAGING}"

    // endregion

    // region SQL Deligt
    const val SQL_DELIGHT = "com.squareup.sqldelight:gradle-plugin:${LibraryVersion.SQL_DELIGHT}"
    const val SQL_DELIGHT_DRIVER =
        "com.squareup.sqldelight:android-driver:${LibraryVersion.SQL_DELIGHT}"
    const val SQL_DELIGHT_COROUTINES =
        "com.squareup.sqldelight:coroutines-extensions-jvm:${LibraryVersion.SQL_DELIGHT}"

    // endregion

    const val GOOGLE_MATERIAL =
        "com.google.android.material:material:${LibraryVersion.GOOGLE_MATERIAL}"

    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${LibraryVersion.SWIPE_REFRESH_LAYOUT}"

    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"

    const val APP_COMPACT = "androidx.appcompat:appcompat:${LibraryVersion.APP_COMPACT}"

    const val CORE_KTX = "androidx.core:core-ktx:${LibraryVersion.CORE_KTX}"

    const val ANDROID_RECYCLERVIEW =
        "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLERVIEW}"

    const val GOOGLE_GSON = "com.google.code.gson:gson:${LibraryVersion.GSON}"

    const val OKHTTP3_LOGGING = "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.OKHTTP3}"

    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${LibraryVersion.MOSHI}"

    const val COIL_KT = "io.coil-kt:coil:${LibraryVersion.COIL}"

    const val WILLIAM_CHART = "com.diogobernardino:williamchart:${LibraryVersion.WILLIAM_CHART}"

    const val CIRCULAR_IMAGE_VIEW =
        "de.hdodenhof:circleimageview:${LibraryVersion.CIRCULAR_IMAGE_VIEW}"

    const val ADAPTER_DELEGATE =
        "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${LibraryVersion.ADAPTER_DELEGATE}"

    const val APP_UPDATER = "com.github.javiersantos:AppUpdater:${LibraryVersion.APP_UPDATER}"
}
