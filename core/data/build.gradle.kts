plugins {
    alias(libs.plugins.runrite.android.library)
}

android {
    namespace = "com.dk.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}