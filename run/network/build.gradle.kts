plugins {
    alias(libs.plugins.runrite.android.library)
    alias(libs.plugins.runrite.jvm.ktor)
}

android {
    namespace = "com.dk.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}