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

internal object GradlePlugin {
    const val ANDROID_BUILD_TOOLS = "com.android.tools.build:gradle:${LibraryVersion.BUILD_TOOLS}"
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${LibraryVersion.KOTLIN}"
    const val DAGGER_HILT_ANDROID_GRADLE_PLUGIN =
        "com.google.dagger:hilt-android-gradle-plugin:${LibraryVersion.DAGGER_HILT_ANDROID}"
    const val NAVIGATION_SAFE_ARGS_GRADLE_PLUGIN =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${LibraryVersion.ANDROID_NAVIGATION}"
}