object AndroidConfig {
    const val COMPILE_SDK_VERSION = 29
    const val BUILD_TOOLS_VERSION = "29.0.3"
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 29

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val APP_ID = "com.yukarlo.tmdb"
    const val TEST_INSTRUMENTATION_RUNNER = "android.support.test.runner.AndroidJUnitRunner"
}

interface BuildType {
    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }

    val isMinifyEnabled: Boolean
    val isDebuggable: Boolean
    val isShrinkResources: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val isDebuggable = true
    override val isShrinkResources = true
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val isDebuggable = false
    override val isShrinkResources = true
}