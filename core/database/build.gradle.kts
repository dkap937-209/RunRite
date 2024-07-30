plugins {
    alias(libs.plugins.runrite.android.library)
    alias(libs.plugins.runrite.android.room)
}

android {
    namespace = "com.dk.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)

    implementation(projects.core.domain)
}