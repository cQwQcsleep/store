# YimaIDE 1.0.8preview — Full Decompiled Source Project
# =========================================================

This repository contains the **full decompiled source code** of the Android app
**Yima IDE** (`com.yimaide.app`, version `1.0.8preview`, build 9), recovered from
the distribution APK downloaded from pgyer.com.

It is an **independent project** — it does not reference or reuse anything from
any other project in this repository, and the content in this folder is a
self-contained snapshot of the decompiled app.

---

## Content

| Path | Description |
|------|-------------|
| `original/Yima_IDE_1.0.8preview.apk` | The original distribution APK (SHA-256 `e53236d41d9fcef6f92d47f9025f9abe3c1555957fde709c02d2baf270024b27`) |
| `app/src/main/java/` | Decompiled Java sources (**38,473 files**, full app + bundled libraries) |
| `app/src/main/res/` | Decoded Android resources |
| `app/src/main/assets/` | App assets (bundled `android.jar`, textmate syntaxes, etc.) |
| `app/src/main/jniLibs/` | Native libraries (aapt2, oniguruma, etc.) for all ABIs |
| `app/src/main/AndroidManifest.xml` | Decoded manifest |

## About the app

- **Package**: `com.yimaide.app`
- **minSdk / targetSdk / compileSdk**: 33 / 36 / 36
- **AGP used to build the original**: 9.2.1
- **UI**: Jetpack Compose (Material 3)
- **Features**: on-device code compilation (bundles `android.jar`, `aapt2` and
  textmate grammar assets), code-slot hot reload via a runtime
  `CodeSlotLoader`/`CodeSlotProvider` protocol.

## How it was decompiled

1. APK obtained from the pgyer install endpoint (after resolving the
   signature/finalCode handshake in the pgyer web installer).
2. Decompiled with **jadx 1.5.6** (`--show-bad-code`).
3. Because the app ships 12 dex files with overlapping class sets, each
   `classes*.dex` was decompiled individually and then merged so that **no class
   is lost** (a single-pass whole-APK run drops some classes due to jadx
   cross-dex dedup).
4. Resources decoded by jadx; the large `assets/android.jar` was copied
   verbatim from the APK.

> Note: the code was R8-obfuscated in the release build, so class/method names in
> `app/src/main/java` are the obfuscated (short) names. They are complete and
> source-level, but not the original readable Kotlin identifiers.

## Building

The folder is structured as a standard Android Gradle project so it can be
opened directly in Android Studio / built with Gradle:

```bash
# Requires: JDK 17+, Android SDK (platform 36)
./gradlew :app:assembleDebug
```

A Gradle wrapper is **not** committed; use a local Gradle 8.x or open the folder
in Android Studio. Note that a clean rebuild of an R8-obfuscated app from
decompiled sources will not reproduce the original binary 1:1 (signing key,
resources mapping and original source identifiers are not available), but the
project is structured to compile at the app level.

## License / disclaimer

This is a **decompiled artifact** for reference/study. The app itself is a
third-party product; all trademarks and copyright belong to their respective
owners. Use responsibly and in compliance with applicable laws and the rights of
the original author.
