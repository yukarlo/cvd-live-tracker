plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("CoroNowPlugin") {
            id = "CoroNowPlugin"
            implementationClass = "CoroNowPlugin"
        }
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

repositories {
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}

dependencies {
    implementation("com.android.tools.build:gradle:4.2.0-alpha15")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
}