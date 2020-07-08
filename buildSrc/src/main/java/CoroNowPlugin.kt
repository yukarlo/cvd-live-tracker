import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
            buildFeatures.dataBinding = true

            defaultConfig.apply {
                applicationId = AndroidConfig.APP_ID
            }

            setUpCommon(project = project, extension = extension)
        }
    }

    private fun setUpLibraryModule(extension: LibraryExtension, project: Project) {
        extension.apply {
            buildFeatures.dataBinding = true

            setUpCommon(project = project, extension = extension)
        }
    }

    private fun setUpCommon(project: Project, extension: BaseExtension) {
        with(extension) {

//            lintOptions {
//                lintConfig = File("lint.xml")
//            }

            project.allprojects {
                tasks.withType(KotlinCompile::class.java).all {
                    kotlinOptions.freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
                }
            }

            packagingOptions {
                pickFirst("META-INF/usecase_debug.kotlin_module")
                pickFirst("META-INF/kotlinx-coroutines-core.kotlin_module")
            }

            buildFeatures.viewBinding = true

            project.tasks.withType<KotlinCompile> {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_1_8.toString()
                }
            }

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
