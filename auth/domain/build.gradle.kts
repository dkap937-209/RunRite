plugins {
    alias(libs.plugins.runrite.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}