plugins {
    alias(libs.plugins.runrite.android.feature.ui)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.dk.analytics.presentation"
}

dependencies {

    implementation(projects.analytics.domain)
}