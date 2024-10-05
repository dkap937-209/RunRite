plugins {
    alias(libs.plugins.runrite.android.library)
}

android {
    namespace = "com.dk.analytics.data"
}

dependencies {

    implementation(libs.kotlinx.coroutines.core)

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.analytics.domain)
}