import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.chaquopy)
}

// 签名配置：读取根目录 keystore.properties（本地或 CI Secret 注入），缺失时发布构建不签名
val keystoreFile = rootProject.file("keystore.properties")
val keystoreProps = if (keystoreFile.exists()) {
    Properties().apply { keystoreFile.inputStream().use { load(it) } }
} else {
    Properties()
}

android {
    namespace = "com.miide"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.miide"
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        // Chaquopy 17 的 Python 3.12 仅支持 64 位 ABI
        ndk {
            abiFilters += listOf("arm64-v8a", "x86_64")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // 签名：本地 keystore.properties（或 CI Secret 注入）存在时启用签名
            signingConfig = if (keystoreFile.exists()) {
                signingConfigs.create("release") {
                    storeFile = rootProject.file(keystoreProps.getProperty("storeFile") ?: "keystore.jks")
                    storePassword = keystoreProps.getProperty("storePassword") ?: ""
                    keyAlias = keystoreProps.getProperty("keyAlias") ?: ""
                    keyPassword = keystoreProps.getProperty("keyPassword") ?: ""
                }
            } else {
                null
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
    }

    lint {
        // AGP 8.7.3 内置 lint 在当前依赖组合（Chaquopy/Compose/库模块）下会在
        // ASM 迁移阶段抛 NegativeArraySizeException / IncompatibleClassChangeError 崩溃，
        // 属工具自身兼容性问题，非项目代码错误。发布版跳过 lint（由 CI 独立检查）。
        checkReleaseBuilds = false
        disable += "ComposableFlowOperator"
    }

    packaging {
        resources {
            // jsch 与 jspecify 各自带一份 OSGI 清单，路径重复导致合并失败
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

// 内置精简运行时：Python（Chaquopy）
chaquopy {
    defaultConfig {
        version = "3.12"
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:editor"))
    implementation(project(":core:runtime"))
    implementation(project(":core:git"))
    implementation(project(":core:remote"))
    implementation(project(":core:plugin"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.ktor.client.core)
    implementation(libs.androidx.room.runtime)

    implementation(libs.rosemoe.editor)
    implementation(libs.rosemoe.language.textmate)

    // 内置虚拟终端
    implementation(libs.termux.terminal.view)

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")
}
