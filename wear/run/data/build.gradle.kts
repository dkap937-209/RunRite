plugins {
    alias(libs.plugins.runrite.android.library)
}

android {
    namespace = "com.dk.wear.run.data"

    defaultConfig {
        minSdk = 30
    }
}

dependencies {
    implementation(libs.androidx.health.services.client)
    implementation(libs.bundles.koin)
}