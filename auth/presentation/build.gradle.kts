plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.runrite.android.feature.ui)
}

android {
    namespace = "com.dk.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}