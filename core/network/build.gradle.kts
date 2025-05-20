plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.openApiGenerator)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.secretGradlePlugin)
}

android {
    namespace = "io.github.mikan.tart.core.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofitConverterMoshi)
    implementation(libs.retrofitConverterScalars)
    implementation(libs.okhttpLoggingInterceptor)
    implementation(libs.moshi)
    implementation(libs.moshiKotlin)
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)
}

val apiName = "qiita"
val buildApiDir = "${layout.buildDirectory.get()}/openApiGenerator/$apiName"
val basePackage = "io.github.mikan.tart.core.network"
fun String.packageToDir() = replace('.', '/')

task<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("generate") {
    doFirst {
        delete(file(buildApiDir))
    }
    generatorName.set("kotlin")
    library.set("jvm-retrofit2")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "useCoroutines" to "true",
        )
    )
    remoteInputSpec.set("https://raw.githubusercontent.com/nanato12/qiita-openapi/refs/heads/develop/openapi.yml")
    outputDir.set(buildApiDir)
    packageName.set(basePackage)
    apiPackage.set("$basePackage.remote")
    modelPackage.set("$basePackage.model")
    generateApiTests.set(false)
    generateModelTests.set(false)
}

task<Copy>("copy") {
    val dirFrom = "$buildApiDir/src/main/kotlin/${basePackage.packageToDir()}"
    val dirInto = "$projectDir/src/main/java/${basePackage.packageToDir()}"

    doFirst {
        delete(file(dirInto))
    }

    dependsOn("generate")
    from(dirFrom)
    into(dirInto)
}

task("buildApi") {
    dependsOn("generate", "copy")
}
