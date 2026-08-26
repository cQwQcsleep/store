# SkyAuto - Android

网易《光·遇》(Sky) 桌面辅助 `sky.angin.cn` 的非官方安卓客户端。最低支持 **Android 12L (API 32)**，Jetpack Compose + Material3 构建。

## 功能
- 身高查询 / 身高排行
- 好友管理（分组、改名、拉黑、批量操作）
- 每日任务自动化 / 跑图
- 设备模拟（多账号「在线」注入）与多账号管理
- 登录 / 注册 / 会话恢复

## 技术要点
- **后端对接**：与站点同源的 `/api` RPC 接口；`serverEndpoints` 可被 `GET /api/config/site` 覆盖。
- **加密层**：`GET /api/crypto/key` 下发 base64 的 AES-256-GCM 密钥；非 GET 且带 body 的 `/api/*` 请求体需 `base64(IV(12B) || AES-GCM ciphertext)` 加密并带 `X-Encrypted:1` 头；响应带同名头则解密。
- **会话**：Cookie 维持（`credentials: include`），登录/退出/注册后清缓存。
- 网络层、会话、Repository、ViewModel、DI 已按分层落地于 `app/src/main/java/com/skyauto/app/`。

## 目录
- `app/` — Android 应用源码
- `docs/api-contracts/` — 由多子模型舰队逆向出的 API 契约（认证/账号/任务/设备·身高）
- `docs/orchestration-codegen/` — 舰队产出的工程骨架与关键代码参考

## 交付说明
本项目逆向与代码生成由「1M 上下文远程子模型舰队（deepseek-v4-flash / glm-5.2，`reasoning_effort` 最高档，并行调度）」完成，主控 Agent 仅负责抓取工作包、派发任务与落盘归档。