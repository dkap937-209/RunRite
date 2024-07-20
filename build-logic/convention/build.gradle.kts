plugins {
    `kotlin-dsl`
}

group = "com.dk.runrite.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

//gradlePlugin {
//    plugins {
//        register("androidApplication") {
//            id = "runrite.android.application"
//            implementationClass = "AndroidApplicationConventionPlugin"
//        }
//    }
//}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "runrite.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}