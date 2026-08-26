Sky Auto tool v8.0 API 契约（sky.ang.cn）

## 0. 基础约定

Base URL: `https://sky.ang.cn`  
API 前缀: `/api`  
所有请求需携带 Cookie 会话，使用 `credentials: 'include'`。  
`GET /api/accounts` 建议带 `cache: 'no-store'`。

### 加密层（必须严格复刻）

1. `GET /api/crypto/key` 返回：
```json
{
  "success": true,
  "key": "base64编码的32字节AES-GCM密钥"
}
```

2. 凡是非 `GET` 且带 body、URL 包含 `/api/` 的请求，body 必须加密：

- 随机生成 12 字节 IV
- AES-256-GCM 加密
- 输出格式：`base64(IV || ciphertext)`
- 请求头加：`X-Encrypted: 1`

3. 响应若带响应头 `X-Encrypted: 1`，则：

- base64 解码
- 前 12 字节为 IV
- 剩余为 AES-GCM 密文
- 解密后再 `JSON.parse`

---

## 1. 认证端点（已确认）

### POST /api/auth/login

请求体：
```json
{
  "account": "邮箱/手机号",
  "login_type": "email|mobile|vivo|huawei",
  "password": "明文密码",
  "device_id": 123456
}
```

响应：
```json
{
  "success": true,
  "message": "登录成功",
  "user": {
    "id": "用户ID",
    "account": "登录账号",
    "display_username": "显示名",
    "email": "邮箱",
    "mobile": "手机号"
  }
}
```

失败：
```json
{
  "success": false,
  "message": "错误信息"
}
```

### POST /api/auth/register

请求体：
```json
{
  "account": "邮箱/手机号",
  "login_type": "email|mobile",
  "password": "密码",
  "code": "验证码",
  "device_id": 123456
}
```

响应：
```json
{
  "success": true,
  "message": "注册成功"
}
```

### POST /api/auth/logout

请求体：
```json
{}
```

响应：
```json
{
  "success": true
}
```

### GET /api/auth/me

响应：
```json
{
  "success": true,
  "user": {
    "id": "用户ID",
    "account": "登录账号",
    "display_username": "显示名",
    "email": "邮箱",
    "mobile": "手机号"
  }
}
```

未登录：
```json
{
  "success": false,
  "message": "未登录"
}
```

### POST /api/auth/send-reset-code/{email}

路径参数：`email`  
响应：
```json
{
  "success": true,
  "message": "验证码已发送"
}
```

---

## 2. 账号管理（已确认）

### GET /api/accounts

响应完整结构：

```json
{
  "success": true,
  "accounts": [
    {
      "id": "账号记录ID",
      "account": "登录账号",
      "display_username": "游戏内显示昵称",
      "name": "兼容旧字段的显示名",
      "nickname": "备注名",
      "status": "online|offline|unknown|error|adding",
      "platform": "ios|android|pc|switch|ps|test",
      "login_type": "email|mobile|vivo|huawei",
      "game_user_id": "光遇游戏UID",
      "friend_id": "光遇好友ID/好友码",
      "device_id": "绑定的设备模拟ID",
      "server": "国服|国际服|测试服",
      "game_server": "国服|国际服|测试服",
      "height": 0,
      "last_height": 0,
      "created_at": "2025-01-01T00:00:00.000Z",
      "updated_at": "2025-01-01T00:00:00.000Z",
      "last_login_at": "2025-01-01T00:00:00.000Z",
      "avatar": "头像URL或空",
      "extra": {}
    }
  ]
}
```

未登录或失败：
```json
{
  "success": false,
  "message": "错误信息"
}
```

### POST /api/accounts/add/start

添加游戏账号，启动异步登录任务。

请求体：
```json
{
  "login_type": "email",
  "account": "邮箱/手机号",
  "password": "密码",
  "device_id": 123456
}
```

响应：
```json
{
  "success": true,
  "task_id": "异步任务ID"
}
```

失败：
```json
{
  "success": false,
  "message": "登录启动失败"
}
```

### GET /api/accounts/add/status/{task_id}

路径参数：`task_id`

响应：
```json
{
  "success": true,
  "task": {
    "status": "pending|running|need_sms_code|need_sms_verify|success|failed",
    "finished": false,
    "logs": [
      {
        "level": "info|error",
        "message": "日志内容",
        "time": "2025-01-01T00:00:00.000Z"
      }
    ]
  }
}
```

`finished: true` 时：
- `status: "success"` 表示添加成功
- 否则取 `logs` 最后一条 `message` 作为失败原因

---

## 3. 好友关系 RPC（已确认端点，action 部分确认/部分推测）

### GET /api/accounts/{account_id}/friend-relations

获取好友关系列表。

响应：
```json
{
  "success": true,
  "friends": [
    {
      "friend_id": "游戏好友ID",
      "name": "游戏内昵称",
      "remark": "备注名或空",
      "blocked": false,
      "platform": "ios|android|pc|switch|ps",
      "last_seen": "2025-01-01T00:00:00.000Z"
    }
  ],
  "blocked": [
    {
      "friend_id": "游戏好友ID",
      "name": "游戏内昵称",
      "remark": "备注名或空",
      "blocked": true,
      "platform": "ios|android|pc|switch|ps",
      "last_seen": "2025-01-01T00:00:00.000Z"
    }
  ]
}
```

失败：
```json
{
  "success": false,
  "message": "错误信息"
}
```

### POST /api/accounts/{account_id}/friend-relations

通用好友关系 RPC。

请求体通用结构：
```json
{
  "action": "rename|block|unblock|delete|list|set_tag|set_note",
  "friend_id": "游戏好友ID",
  "name": "用于 rename 的新昵称/备注",
  "value": "用于 set_tag/set_note 的标签或备注值"
}
```

#### 已确认/高置信 action

| action | 必填字段 | 语义 |
|---|---|---|
| `rename` | `friend_id`, `name` | 修改好友备注/昵称 |
| `block` | `friend_id` | 拉黑好友 |
| `unblock` | `friend_id` | 解除拉黑 |
| `list` | 无 | 获取好友列表，等价 GET |

#### 推测 action

| action | 必填字段 | 语义 |
|---|---|---|
| `delete` | `friend_id` | 删除好友 |
| `set_tag` | `friend_id`, `value` | 设置好友标签/分组 |
| `set_note` | `friend_id`, `value` | 设置好友备注 |
| `get_blocked` | 无 | 获取黑名单 |

#### 成功响应

```json
{
  "success": true,
  "message": "操作成功",
  "data": {
    "friend_id": "游戏好友ID",
    "name": "操作后的昵称/备注",
    "blocked": false,
    "value": "标签或备注值"
  }
}
```

`list` 成功响应：
```json
{
  "success": true,
  "friends": [
    {
      "friend_id": "游戏好友ID",
      "name": "游戏内昵称",
      "remark": "备注名或空",
      "blocked": false,
      "platform": "ios|android|pc|switch|ps"
    }
  ],
  "blocked": []
}
```

失败响应：
```json
{
  "success": false,
  "message": "错误信息",
  "code": "可选错误码"
}
```

---

## 4. 字段语义

- `account_id`：本站账号记录 ID，路径参数。
- `friend_id`：光遇游戏内好友 ID / 好友码，不是本站账号 ID。
- `name`：用于 `rename`，表示修改后的好友昵称/备注。
- `value`：通用值字段，用于 `set_tag`、`set_note` 等扩展 action。
- `status` 账号状态：`online`、`offline`、`unknown`、`error`、`adding`。
- `login_type`：`email`、`mobile`、`vivo`、`huawei`。
- `server`：`国服`、`国际服`、`测试服`。

---

## 5. 推测端点（从站点功能/前端特征猜测，未在已给 CTX 中完全确认）

### 好友/榜单相关

```text
GET  /api/accounts/{account_id}/friends
GET  /api/accounts/{account_id}/height
GET  /api/accounts/{account_id}/height/history
GET  /api/accounts/{account_id}/height/rank
GET  /api/leaderboard?scope=all
GET  /api/leaderboard?scope=friends&account_id={account_id}
POST /api/accounts/{account_id}/friend-relations/block
POST /api/accounts/{account_id}/friend-relations/unblock
```

推测响应示例：

```json
{
  "success": true,
  "rank": 1,
  "total": 100,
  "height": 12.34,
  "history": [
    {
      "height": 12.34,
      "time": "2025-01-01T00:00:00.000Z"
    }
  ]
}
```

### 设备模拟相关

```text
GET    /api/devices
GET    /api/accounts/{account_id}/devices
POST   /api/devices
DELETE /api/devices/{device_id}
POST   /api/accounts/{account_id}/devices/bind
POST   /api/accounts/{account_id}/devices/unbind
```

推测设备对象：

```json
{
  "id": "设备ID",
  "name": "设备名称",
  "type": "android|ios|pc",
  "model": "设备型号",
  "system": "系统版本",
  "status": "online|offline",
  "created_at": "2025-01-01T00:00:00.000Z"
}
```

### 每日任务自动化相关

```text
GET  /api/accounts/{account_id}/daily-tasks
POST /api/accounts/{account_id}/daily-tasks/run
GET  /api/tasks/{task_id}
```

推测响应：

```json
{
  "success": true,
  "task_id": "任务ID",
  "status": "pending|running|success|failed",
  "finished": false,
  "logs": [
    {
      "level": "info|error",
      "message": "日志内容",
      "time": "2025-01-01T00:00:00.000Z"
    }
  ]
}
```