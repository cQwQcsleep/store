逆向结论如下。

通用约定
- Base：默认 `/api`，实际以 `GET /api/config/site` 返回的 `config.serverEndpoints.api` 为准；WebSocket 为 `config.serverEndpoints.ws`，默认 `/ws`。
- 所有请求 `credentials: "include"`，Cookie 维持会话。
- 非 `GET` 且带 body、URL 含 `/api/` 的请求，body 按 8393 模块加密：
  - 12 字节随机 IV
  - AES-256-GCM 加密
  - body = `base64(IV || ciphertext)`
  - 请求头加 `X-Encrypted: 1`
- 响应头若存在 `X-Encrypted: 1`，响应体按同样方式解密后再 `JSON.parse`。
- `GET /api/config/site` 在前端缓存 10 秒；`/api/crypto/key` 不参与请求体加密。

---

## 1. 设备模拟 API

### GET /api/devices
获取当前用户可用的模拟设备列表。

响应：
```json
{
  "success": true,
  "devices": [
    {
      "id": 3,
      "name": "模拟设备-03",
      "platform": "android",
      "model": "Xiaomi M2012K11AC",
      "android_version": "12",
      "status": "online",
      "bound_account_count": 2,
      "last_seen_at": "2025-01-01T12:00:00.000Z",
      "created_at": "2025-01-01T00:00:00.000Z"
    }
  ]
}
```

说明：
- `id` 是数字，即账号绑定用的 `device_id`。
- 添加账号时 `device_id: Number(h)` 中的 `h` 来自这个 `id`。
- 账号对象里的 `device_id` 也是这个数字，表示该账号当前绑定/最后使用的模拟设备。

### GET /api/devices/{id}
获取单个设备详情。

响应：
```json
{
  "success": true,
  "device": {
    "id": 3,
    "name": "模拟设备-03",
    "platform": "android",
    "model": "Xiaomi M2012K11AC",
    "android_version": "12",
    "status": "online",
    "bound_account_count": 2,
    "last_seen_at": "2025-01-01T12:00:00.000Z",
    "created_at": "2025-01-01T00:00:00.000Z"
  }
}
```

### POST /api/devices
创建设备。

请求体（加密）：
```json
{
  "name": "模拟设备-04",
  "model": "Xiaomi M2012K11AC",
  "platform": "android",
  "android_version": "12"
}
```

响应：
```json
{
  "success": true,
  "device": {
    "id": 4,
    "name": "模拟设备-04",
    "platform": "android",
    "model": "Xiaomi M2012K11AC",
    "android_version": "12",
    "status": "offline",
    "bound_account_count": 0,
    "created_at": "2025-01-01T12:00:00.000Z"
  }
}
```

### PATCH /api/devices/{id}
更新设备信息，例如改名。

请求体（加密）：
```json
{
  "name": "新设备名"
}
```

响应：
```json
{
  "success": true,
  "device": {}
}
```

### DELETE /api/devices/{id}
删除设备。

响应：
```json
{
  "success": true
}
```

### POST /api/devices/{id}/simulate-login
“在线模拟登录”端点，把设备模拟登录状态注入到指定游戏账号。

请求体（加密）：
```json
{
  "account_id": "账号ID",
  "game_server": "国服"
}
```

`game_server` 取值范围来自 `config.gameServerOptions`：
```json
["国服", "国际服", "测试服"]
```

响应：
```json
{
  "success": true,
  "status": "online",
  "expires_at": "2025-01-01T13:00:00.000Z"
}
```

说明：
- 实际登录动作在添加账号时也会触发，即 `POST /api/accounts/add/start` 携带 `device_id` 后，后端会用该设备完成模拟登录并写入账号状态。
- 设备本身不是游戏服务器，它是登录来源指纹；账号的 `status` 是否“在线”由这个模拟登录结果决定。

---

## 2. 身高查询 / 排行 API

### GET /api/height/self
查询当前账号自己的身高。

请求参数：
```text
?account_id=xxx
```

响应：
```json
{
  "success": true,
  "account_id": "xxx",
  "self_height": 0.718,
  "height_val": 0.718,
  "updated_at": "2025-01-01T12:00:00.000Z"
}
```

字段说明：
- `self_height`：自己的身高数值。
- `height_val`：用于排序/计算百分位的原始身高值，前端展示时通常用 `Number(height_val).toFixed(2)`。

### GET /api/height/rank
获取身高排行。

请求参数：
```text
?page=1&page_size=20&account_id=xxx
```

响应：
```json
{
  "success": true,
  "total": 12345,
  "page": 1,
  "page_size": 20,
  "self": {
    "rank": 123,
    "account_id": "xxx",
    "nickname": "当前昵称",
    "display_username": "显示名",
    "self_height": 0.718,
    "height_val": 0.718
  },
  "list": [
    {
      "rank": 1,
      "account_id": "a1",
      "nickname": "玩家A",
      "display_username": "玩家A",
      "self_height": 0.718,
      "height_val": 0.718
    }
  ]
}
```

说明：
- `list[].height_val` 是后端排序依据。
- 前端显示身高时使用 `Number(height_val).toFixed(2)`，所以响应里 `height_val` 可以是高精度浮点数。
- `self_height` 与 `height_val` 在数值上通常一致；若站点内部有修正/换算，`height_val` 以排行计算值为准。
- `self` 仅在传入 `account_id` 时返回，用于高亮“我的排名”。

---

## 3. /api/config/site 返回结构

### GET /api/config/site

响应：
```json
{
  "success": true,
  "config": {
    "serverEndpoints": {
      "api": "/api",
      "ws": "/ws"
    },
    "gameServerOptions": ["国服", "国际服", "测试服"],
    "bg_desktop_type": "image",
    "bg_desktop_url": "https://...",
    "bg_mobile_type": "image",
    "bg_mobile_url": "https://...",
    "glass_opacity": 0.35,
    "top_banner": "公告文字",
    "top_banner_seconds": 8,
    "announcements": [
      {
        "id": "announcement_1",
        "title": "公告标题",
        "content": "公告内容",
        "content_format": "markdown",
        "level": "info",
        "force": false
      }
    ],
    "guest_popup": {
      "enabled": true,
      "title": "温馨提示",
      "content": "弹窗内容",
      "content_format": "html",
      "level": "info",
      "force": true
    }
  }
}
```

字段说明：
- `serverEndpoints` 可覆盖前端默认 API/WS 地址。
- `gameServerOptions` 用于账号平台选择。
- `bg_desktop_type` / `bg_mobile_type`：`image`、`video` 或 `color`。
- `glass_opacity`：前端玻璃拟态透明度。
- `top_banner`：顶部横幅文本。
- `announcements`：公告列表。
- `guest_popup`：未登录弹窗配置。

---

## 4. api.open-meteo.com 的作用

`api.open-meteo.com` 是前端直接调用的外部天气服务，不经过站点后端，也不参与 AES-GCM 加密。

前端调用方式：
```text
GET https://api.open-meteo.com/v1/forecast?latitude=...&longitude=...&current_weather=true
```

它只用于页面天气展示，例如：
- 背景/顶部天气状态
- 当前温度、天气代码、风速展示

它不参与身高计算、不进入 `/api/height/*` 请求体或响应体。后端 API 契约中不存在 open-meteo 字段。