# MiIDE

> 安卓端 AI 编程 IDE · 完全免费 · 完全开源 · 零遥测零埋点
> An Android AI coding IDE — free, open source, zero telemetry, zero tracking.

## 简介 / Introduction

MiIDE 是一款运行在安卓设备上的轻量 AI 编程 IDE，内置代码编辑器、终端、Git、远程 SSH/SFTP 开发与插件市场，并深度适配国内主流 AI 供应商（DeepSeek / 智谱 GLM / Kimi / 通义千问）。

MiIDE is a lightweight AI coding IDE for Android, featuring a built-in code editor, terminal, Git integration, remote SSH/SFTP development and a plugin market, with deep support for mainstream Chinese AI providers (DeepSeek / Zhipu GLM / Kimi / Qwen).

## 核心特性 / Features

- **AI 对话**：与多家供应商自由切换，流式输出、思考可见、实时动作流、改动 diff 预览、耗时与 token 消耗展示、跑马灯输出小窗口
- **暂停 / 继续控制**：AI 工作时可随时暂停查看现状，决定继续或中途打断重定向；非阻塞建议队列自动排队处理
- **代码编辑**：基于 Rosemoe 的高性能编辑器，语法高亮、行号、光标定位，与 AI 会话双向桥接
- **内置终端**：Termux 内核虚拟终端，可运行常用命令
- **Git 集成**：clone / commit / push / pull / branch / diff
- **远程开发**：SSH / SFTP 连接服务器，文件浏览、读写与命令执行
- **分屏多窗格**：编辑 + 对话、编辑 + 终端，可拖拽调整占比
- **插件市场**：脚本插件（QuickJS 沙箱）、主题包、官方功能包的浏览安装与管理
- **零遥测**：不采集任何数据，无任何埋点

## 安装 / Installation

- 从 [GitHub Releases](https://github.com) 下载最新 APK（v1.0.0+）
- 开启「允许安装未知应用」后侧载安装
- 支持 ABI：arm64-v8a、x86_64

## 快速开始 / Quick Start

1. 安装并打开 MiIDE
2. 进入「AI 设置」添加供应商 API Key（支持 DeepSeek / GLM / Kimi / 通义千问预设一键填充）
3. 回到首页开始与 AI 对话，或新建 / 打开文件开始编辑

## 构建 / Build

```bash
# JDK 17 必需（AGP 8.7.3 不支持 JDK 25）
JAVA_HOME=/path/to/jdk17 ./gradlew :app:assembleDebug
# 发布版
JAVA_HOME=/path/to/jdk17 ./gradlew :app:assembleRelease
```

产物位于 `app/build/outputs/apk/`。

## 发布 / Release

打 `v1.0.0` 形式的 tag 推送后，`.github/workflows/release.yml` 会自动构建 Release APK 并发布到 GitHub Releases（签名密钥通过仓库 Secret 注入，仓库内不保存明文）。

## 模块结构 / Modules

| 模块 | 说明 |
|---|---|
| `app` | 应用壳 + UI（首页 / 编辑器 / 对话 / 终端 / Git / 远程 / 插件市场 / 设置） |
| `core:model` | 领域模型（供应商 / 模型 / 对话消息等） |
| `core:network` | 网络层与 API 聚合网关（多 Key 轮询 / 失败切换 / 限额控制） |
| `core:data` | Room 本地存储与仓库层 |
| `core:designsystem` | 设计系统与主题 |
| `core:editor` | 编辑器内核封装与桥接 |
| `core:runtime` | 内置运行时（Python / JS / Lua） |
| `core:git` | Git 集成（JGit） |
| `core:remote` | 远程 SSH/SFTP（JSch） |
| `core:plugin` | 插件市场与 QuickJS 沙箱 |

## 隐私承诺 / Privacy

MiIDE 零遥测、零埋点、零权限收集。所有数据（含 API Key 与远程服务器凭据）默认仅存于本机。

MiIDE has zero telemetry, zero tracking and zero data collection. All data (including API keys and remote credentials) stays on your device by default.

## 开源协议 / License

正式发布前确定（倾向 Apache-2.0 或 MIT）。

## 致谢 / Acknowledgments

- [Rosemoe Sora Editor](https://github.com/Rosemoe/sora-editor)
- [Termux Terminal Emulator](https://github.com/termux/termux-app)
- [JGit](https://www.eclipse.org/jgit/)
- [JSch](https://github.com/mwiede/jsch)
- [QuickJS](https://bellard.org/quickjs/)
- [Ktor](https://ktor.io/)
