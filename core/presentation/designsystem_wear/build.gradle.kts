import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin.Companion.kotlinNpmResolutionManager

plugins {
    alias(libs.plugins.runrite.android.library.compose)
}

android {
    namespace = "com.dk.core.presentation.designsystem_wear"

    defaultConfig {
        minSdk = 30
    }
}

dependencies {

    api(projects.core.presentation.designsystem)
    implementation(libs.androidx.wear.compose.material)
}