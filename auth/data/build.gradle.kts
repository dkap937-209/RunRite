plugins {
    alias(libs.plugins.runrite.android.library)
    alias(libs.plugins.runrite.jvm.ktor)
}

android {
    namespace = "com.dk.auth.data"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}