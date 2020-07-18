internal object GradleCustomPlugin {
    internal const val CORONOW_PLUGIN = "CoroNowPlugin"
}

internal object GradlePluginId {
    internal const val ANDROID_APPLICATION = "com.android.application"
    internal const val ANDROID_LIBRARY = "com.android.library"
    internal const val JAVA_LIBRARY = "java-library"
    internal const val KOTLIN_ANDROID = "android"
    internal const val KOTLIN_ANDROID_EXTENSIONS = "android.extensions"
    internal const val KOTLIN_KAPT = "kapt"
    internal const val KOTLIN_JVM = "jvm"
    internal const val KOTLIN = "kotlin"
}

internal object GradlePluginVersion {
    const val ANDROID_GRADLE = "4.2.0-alpha04"
}

internal object GradlePlugin {
    const val ANDROID_TOOLS_BUILD_GRADLE =
        "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
    const val KOTLIN_GRADLE_PLUGIN = "gradle-plugin"
    const val DAGGER_HILT_ANDROID_GRADLE_PLUGIN =
        "com.google.dagger:hilt-android-gradle-plugin:${LibraryVersion.DAGGER_HILT_ANDROID}"
    const val APOLLO_GRADLE_PLUGIN =
        "com.apollographql.apollo:apollo-gradle-plugin:2.2.2"
}