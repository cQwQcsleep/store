# 第三方软件声明

本产品包含下列第三方软件。许可证全文置于 `app/src/main/assets/third_party/licenses/`。

下列组件的传递依赖，适用其上游公布的许可证，或 Apache-2.0 / MIT。

---

## 组件

| 名称 | 版本 | 许可证 | 版权 / 来源 |
| --- | --- | --- | --- |
| Sora Editor（editor、language-textmate、oniguruma-native） | 0.24.4 | LGPL-2.1 | Rosemoe and contributors。https://github.com/Rosemoe/sora-editor |
| Eclipse Compiler for Java (ECJ) | 3.37.0 | EPL-2.0 | Eclipse Foundation。https://github.com/eclipse-jdt/eclipse.jdt.core |
| aapt2（`libaapt2.so`） | ReVanced v1.1.0（AOSP platform-tools 35.0.2） | ReVanced 仓库 GPL-3.0；AOSP aapt2 源码 Apache-2.0 | The Android Open Source Project；ReVanced。https://github.com/ReVanced/aapt2/tree/v1.1.0 |
| android.jar | API 37 stubs | 见 `licenses/AndroidJar.txt` | The Android Open Source Project |
| AndroidX / Jetpack Compose / Material3 / Security Crypto | Compose BOM 2026.02.01 | Apache-2.0 | The Android Open Source Project |
| Kotlin 标准库 / Compose 编译器插件 | 2.4.0 | Apache-2.0 | JetBrains s.r.o. |
| kotlinc-android | 2.4.0 | Apache-2.0 | Pranav Purwar；JetBrains s.r.o. |
| ARSCLib | 1.3.8 | Apache-2.0 | REAndroid |
| OkHttp / okhttp-sse | 4.12.0 | Apache-2.0 | Square, Inc. |
| SSHJ | 0.38.0 | Apache-2.0 | Jeroen van Erp / Hierynomus |
| apksig | 9.2.1 | Apache-2.0 | The Android Open Source Project |
| R8 | 8.5.10 | BSD-3-Clause（及上游 NOTICE 所载其他许可） | Google LLC |
| Bouncy Castle（bcprov / bcpkix / bcutil） | 1.78.1 | Bouncy Castle Licence | The Legion of the Bouncy Castle Inc. |
| TextMate 语法与 Dark+ / Light+ 主题 | 随包 `assets/textmate/`（无单一版本号） | MIT | TextMate bundles、Atom language-*、Microsoft（Dark+/Light+）及各语法原作者 |

Sora Editor 与 ECJ 按上游发行版使用，未经修改。

---

## 对应源码

本产品所含版本的对应源码：

| 组件 | 对应源码 |
| --- | --- |
| Sora Editor 0.24.4 | https://repo1.maven.org/maven2/io/github/rosemoe/editor/0.24.4/editor-0.24.4-sources.jar |
| | https://repo1.maven.org/maven2/io/github/rosemoe/language-textmate/0.24.4/language-textmate-0.24.4-sources.jar |
| | https://repo1.maven.org/maven2/io/github/rosemoe/oniguruma-native/0.24.4/oniguruma-native-0.24.4-sources.jar |
| ECJ 3.37.0 | https://repo1.maven.org/maven2/org/eclipse/jdt/ecj/3.37.0/ecj-3.37.0-sources.jar |
| aapt2 ReVanced v1.1.0 | https://github.com/ReVanced/aapt2/archive/refs/tags/v1.1.0.zip |

自本产品分发之日起三年内，亦可通过产品内建议反馈书面索取 Sora Editor 0.24.4 与 ECJ 3.37.0 的对应源码，收费不超过复制该源码的成本。

---

## 内嵌二进制

**libaapt2.so** 与 ReVanced/aapt2 v1.1.0 一致：

| ABI | SHA-256 |
| --- | --- |
| arm64-v8a | `7e5ae2e1f62fc24cab14072555ffd0a1a7e1ce27e82cc1008967b835b6d8df5b` |
| armeabi-v7a | `466124dc6a412c39cb970a12ccaf2df054abf6f6e63e78690cd7b6e53263bd86` |
| x86_64 | `46d2244c3c574979ecae452c8a32e0a25ac0184bfe29a3d805821e36401ecdcb` |

**android.jar** SHA-256：`bf1b4387cc7ca94fc6ef684f040d9d16fbf16248e181819f020736ea2053f177`

---

## 许可证文本

| 文件 | 许可 |
| --- | --- |
| Apache-2.0.txt | Apache License 2.0 |
| MIT.txt | MIT License |
| BSD-3-Clause.txt | BSD 3-Clause |
| BouncyCastle.txt | Bouncy Castle Licence |
| LGPL-2.1.txt | GNU Lesser General Public License 2.1 |
| EPL-2.0.txt | Eclipse Public License 2.0 |
| GPL-3.0.txt | GNU General Public License 3.0 |
| AndroidJar.txt | android.jar 再分发说明 |
