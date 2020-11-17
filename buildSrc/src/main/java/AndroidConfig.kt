internal object AndroidConfig {
    const val COMPILE_SDK_VERSION = 30
    const val BUILD_TOOLS_VERSION = "30.0.2"
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 30

    const val VERSION_CODE = 4
    const val VERSION_NAME = "1.00"

    const val APP_ID = "com.yukarlo.coronow"
    const val TEST_INSTRUMENTATION_RUNNER = "android.support.test.runner.AndroidJUnitRunner"
}

internal interface BuildType {
    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }

    val isMinifyEnabled: Boolean
    val isDebuggable: Boolean
    val isShrinkResources: Boolean
}

internal object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val isDebuggable = true
    override val isShrinkResources = true
}

internal object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val isDebuggable = false
    override val isShrinkResources = true
}