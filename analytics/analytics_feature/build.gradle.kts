plugins {
    alias(libs.plugins.runrite.android.dynamic.feature)
}
android {
    namespace = "com.dk.analytics.analytics_feature"
}

dependencies {
    implementation(project(":app"))

    api(projects.analytics.presentation)
    implementation(projects.analytics.domain)
    implementation(projects.analytics.data)
    implementation(projects.core.database)
}