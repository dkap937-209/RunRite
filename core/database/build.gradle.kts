plugins {
    alias(libs.plugins.runrite.android.library)
}

android {
    namespace = "com.dk.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)

    implementation(projects.core.domain)
}