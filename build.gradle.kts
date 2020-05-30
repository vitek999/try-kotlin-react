plugins {
    id("org.jetbrains.kotlin.js") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.70"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val ktorVersion = "1.3.2"
val serializationVersion = "0.20.0"

repositories {
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serializationVersion")

    //Coroutines (chapter 8)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")

    implementation(npm("text-encoding"))
    implementation(npm("abort-controller"))

    implementation("io.ktor:ktor-client-js:$ktorVersion")
    implementation(npm("bufferutil"))
    implementation(npm("utf-8-validate"))

    // ktor
    implementation("io.ktor:ktor-client-json-js:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization-js:$ktorVersion")
    implementation(npm("fs"))

    //React, React DOM + Wrappers (chapter 3)
    implementation("org.jetbrains:kotlin-react:16.13.1-pre.103-kotlin-1.3.72")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.103-kotlin-1.3.72")
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))

    //Kotlin Styled (chapter 3)
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.103-kotlin-1.3.72")
    implementation(npm("styled-components"))
    implementation(npm("inline-style-prefixer"))

    //Video Player (chapter 7)
    implementation(npm("react-player"))

    //Share Buttons (chapter 7)
    implementation(npm("react-share"))
}

kotlin.target.browser {
    dceTask {
        dceOptions.devMode = true
        keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
    }
}