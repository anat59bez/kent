pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Kent"

include(":app")
include(":core:ui")
include(":core:network")
include(":core:crypto")
include(":core:database")
include(":core:common")
include(":feature:auth")
include(":feature:chat")
include(":feature:calls")
include(":feature:stories")
include(":feature:ai")
include(":feature:settings")
include(":feature:profile")

