plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.runrite.android.library.compose)
}

android {
    namespace = "com.dk.core.presentation.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    debugImplementation(libs.androidx.ui.tooling)
    api(libs.androidx.compose.material3)
}
