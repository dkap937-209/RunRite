plugins {
    alias(libs.plugins.runrite.android.library)
    alias(libs.plugins.runrite.android.room)
}

android {
    namespace = "com.dk.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.junit.ktx)
}