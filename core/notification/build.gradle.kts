plugins {
    alias(libs.plugins.runrite.android.library)
}

android {
    namespace = "com.dk.core.notification"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.presentation.ui)
    implementation(projects.core.presentation.designsystem)
}