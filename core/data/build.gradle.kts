plugins {
    alias(libs.plugins.runrite.android.library)
    alias(libs.plugins.runrite.jvm.ktor)
}

android {
    namespace = "com.dk.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}