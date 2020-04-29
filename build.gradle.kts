import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories.addDefaults()
    dependencies.addPluginDependencies()
}

allprojects {
    repositories.addDefaults()

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
