plugins {
    alias(libs.plugins.runrite.android.library)
    alias(libs.plugins.runrite.android.room)
}

android {
    namespace = "com.dk.analytics.data"
}

dependencies {

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.koin)

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.analytics.domain)
}