import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class CoroNowPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.all {
                when (this) {
                    is AppPlugin -> extensions.getByType<BaseAppModuleExtension>().apply {
                        setUpAndroidModules(project = project, extension = this)
                    }
                    is LibraryPlugin -> extensions.getByType<LibraryExtension>().apply {
                        setUpLibraryModule(project = project, extension = this)
                    }
                }
            }
        }
    }

    private fun setUpAndroidModules(extension: BaseAppModuleExtension, project: Project) {
        extension.apply {
            defaultConfig.apply {
                applicationId = AndroidConfig.APP_ID
            }

            setUpCommon(project = project, extension = extension)
        }
    }

    private fun setUpLibraryModule(extension: LibraryExtension, project: Project) {
        extension.apply {
            setUpCommon(project = project, extension = extension)
        }
    }

    private fun setUpCommon(project: Project, extension: BaseExtension) {
        with(extension) {
            buildFeatures.viewBinding = true

            compileOptions.apply {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
            buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

            defaultConfig.apply {
                minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
                targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
                versionCode = AndroidConfig.VERSION_CODE
                versionName = AndroidConfig.VERSION_NAME
                testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
            }

            lintOptions {
                isAbortOnError = false
            }

            sourceSets.getByName("androidTest")
                .assets.srcDirs(project.files("${project.projectDir}/schemas"))

            buildTypes.apply {
                getByName(BuildType.RELEASE) {
                    isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
                getByName(BuildType.DEBUG) {
                    isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
                    isDebuggable = BuildTypeDebug.isDebuggable
                }
            }
        }
    }
}
