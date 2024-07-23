plugins {
    alias(libs.plugins.runrite.android.library)
}

android {
    namespace = "com.dk.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}