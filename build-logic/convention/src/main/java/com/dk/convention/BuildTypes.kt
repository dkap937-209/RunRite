package com.dk.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
){
    commonExtension.run {
        buildFeatures{
            buildConfig = true
        }

        val apiKey = gradleLocalProperties(rootDir, rootProject.providers).getProperty("API_KEY")
        val baseUrl = gradleLocalProperties(rootDir, rootProject.providers).getProperty("BASE_URL")

        when(extensionType){
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension>{
                    buildTypes {
                        debug {
                            configureDebugBuildType(
                                apiKey = apiKey,
                                baseUrl = baseUrl
                            )
                        }
                        release {
                            configureReleaseBuildType(
                                commonExtension = commonExtension,
                                apiKey = apiKey,
                                baseUrl = baseUrl
                            )
                        }
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension>{
                    buildTypes {
                        debug {
                            configureDebugBuildType(
                                apiKey = apiKey,
                                baseUrl = baseUrl
                            )
                        }
                        release {
                            configureReleaseBuildType(
                                commonExtension = commonExtension,
                                apiKey = apiKey,
                                baseUrl = baseUrl
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(
    apiKey: String,
    baseUrl: String
){
    buildConfigField(
        type ="String",
        name = "API_KEY",
        value = "\"$apiKey\""
    )
    buildConfigField(
        type ="String",
        name = "BASE_URL",
        value = "\"$baseUrl\""
    )
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    apiKey: String,
    baseUrl: String
){
    buildConfigField(
        type ="String",
        name = "API_KEY",
        value = "\"$apiKey\""
    )
    buildConfigField(
        type ="String",
        name = "BASE_URL",
        value = "\"$baseUrl\""
    )

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}