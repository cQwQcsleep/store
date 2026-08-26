加密规则（严格按 8393 模块语义）
- 先 GET /api/crypto/key，返回 { success, key }，key 是 base64 的 32 字节 AES-256-GCM 原始密钥。
- 凡 method != GET 且带 body 且 URL 含 /api/ 的请求，body 加密为：
  base64(12字节随机IV || AES-GCM密文)
  并加请求头 X-Encrypted: 1。
- 响应头含 X-Encrypted: 1 时，响应体按同样方式解密后再 JSON.parse。
- 所有请求必须 credentials: include，依赖 cookie 会话。
- /auth/login、/auth/logout、/auth/register 调用后会清空 crypto key 与 /auth/me 缓存。
- 统一错误响应：{ "success": false, "message": "错误说明" }

================================================================
1. task_types 全集
================================================================
GET /api/task/types

响应字段：
{
  "success": true,
  "task_types": [
    {
      "type": "login",
      "name": "账号登录",
      "category": "account",
      "reward": false,
      "requires_device": false
    },
    {
      "type": "daily_task",
      "name": "每日任务",
      "category": "automation",
      "reward": true,
      "requires_device": true
    },
    {
      "type": "runtask",
      "name": "跑图",
      "category": "automation",
      "reward": true,
      "requires_device": true
    },
    {
      "type": "season_task",
      "name": "季节任务",
      "category": "automation",
      "reward": true,
      "requires_device": true
    },
    {
      "type": "event_task",
      "name": "活动任务",
      "category": "automation",
      "reward": true,
      "requires_device": true
    },
    {
      "type": "device_online",
      "name": "设备在线模拟",
      "category": "device",
      "reward": false,
      "requires_device": true
    }
  ]
}

================================================================
2. task 通用字段与状态机
================================================================
task 对象通用字段：
{
  "task_id": "uuid",
  "type": "login | daily_task | runtask | season_task | event_task | device_online",
  "status": "pending | started | success | finished | failed | canceled | need_sms_code | need_sms_verify | need_huawei_captcha | need_huawei_password | need_bilibili_qr",
  "finished": false,
  "progress": 0,
  "logs": [
    {
      "time": "2026-01-01T00:00:00.000Z",
      "level": "info",
      "message": "日志内容"
    }
  ],
  "result": null,
  "verification": null,
  "created_at": "2026-01-01T00:00:00.000Z",
  "updated_at": "2026-01-01T00:00:00.000Z"
}

状态机：

type=login：
started -> need_sms_code -> success
started -> need_sms_verify -> success
started -> need_huawei_captcha -> need_huawei_password -> success
started -> need_bilibili_qr -> success
started -> success | failed | canceled

type=daily_task / runtask / season_task / event_task：
pending -> started -> finished | failed | canceled

type=device_online：
pending -> started -> finished | failed | canceled

说明：
- 登录类任务终态 status 为 success。
- 自动化任务终态 status 为 finished。
- 所有任务都有 finished 布尔字段，用于轮询判断是否结束。
- need_* 状态只会出现在 type=login 的任务中。
- need_bilibili_qr 时 verification 里会带 qr_url / qr_token。

================================================================
3. 创建任务
================================================================
POST /api/task/create

请求体（加密前明文）：
{
  "task_type": "runtask",
  "account_id": "uuid",
  "device_id": "uuid",
  "config": {
    "maps": ["prairie", "forest", "valley", "wasteland", "vault"],
    "mode": "full",
    "collect_candles": true,
    "collect_hearts": false
  }
}

响应：
{
  "success": true,
  "task_id": "uuid"
}

================================================================
4. 领取任务
================================================================
POST /api/task/claim

请求体（加密前明文）：
{
  "task_type": "daily_task",
  "account_id": "uuid"
}

响应：
{
  "success": true,
  "task_id": "uuid"
}

================================================================
5. 运行任务
================================================================
POST /api/task/runtask

请求体（加密前明文）：
{
  "task_id": "uuid"
}

响应：
{
  "success": true,
  "task_id": "uuid",
  "status": "started"
}

================================================================
6. task_id 轮询
================================================================
GET /api/task/status/{task_id}

注意：此请求必须带 cache: "no-store"。

运行中响应：
{
  "success": true,
  "task": {
    "task_id": "uuid",
    "type": "runtask",
    "status": "started",
    "finished": false,
    "progress": 35,
    "logs": [
      {
        "time": "2026-01-01T00:00:00.000Z",
        "level": "info",
        "message": "开始跑图"
      }
    ],
    "result": null,
    "verification": null,
    "created_at": "2026-01-01T00:00:00.000Z",
    "updated_at": "2026-01-01T00:00:00.000Z"
  }
}

终态成功响应：
{
  "success": true,
  "task": {
    "task_id": "uuid",
    "type": "runtask",
    "status": "finished",
    "finished": true,
    "progress": 100,
    "logs": [],
    "result": {
      "success": true,
      "reward": {
        "task_id": "uuid",
        "account_id": "uuid",
        "items": [
          {
            "type": "candle",
            "amount": 1
          }
        ]
      }
    },
    "verification": null,
    "created_at": "2026-01-01T00:00:00.000Z",
    "updated_at": "2026-01-01T00:00:00.000Z"
  }
}

终态失败响应：
{
  "success": true,
  "task": {
    "task_id": "uuid",
    "type": "runtask",
    "status": "failed",
    "finished": true,
    "progress": 100,
    "logs": [
      {
        "time": "2026-01-01T00:00:00.000Z",
        "level": "error",
        "message": "登录失败原因"
      }
    ],
    "result": {
      "success": false,
      "message": "登录失败原因"
    },
    "verification": null,
    "created_at": "2026-01-01T00:00:00.000Z",
    "updated_at": "2026-01-01T00:00:00.000Z"
  }
}

登录任务等待验证码响应：
{
  "success": true,
  "task": {
    "task_id": "uuid",
    "type": "login",
    "status": "need_sms_code",
    "finished": false,
    "progress": 0,
    "logs": [],
    "result": null,
    "verification": {
      "type": "sms_code",
      "sent_to": "***",
      "expires_in": 300
    },
    "created_at": "2026-01-01T00:00:00.000Z",
    "updated_at": "2026-01-01T00:00:00.000Z"
  }
}

================================================================
7. 领取任务奖励
================================================================
POST /api/task/reward

请求体（加密前明文）：
{
  "task_id": "uuid"
}

响应：
{
  "success": true,
  "reward": {
    "task_id": "uuid",
    "account_id": "uuid",
    "items": [
      {
        "type": "candle",
        "amount": 1
      },
      {
        "type": "heart",
        "amount": 1
      }
    ],
    "claimed_at": "2026-01-01T00:00:00.000Z"
  }
}

================================================================
8. reward_account_quota
================================================================
GET /api/task/reward_account_quota?account_id={account_id}

响应：
{
  "success": true,
  "account_id": "uuid",
  "quota": {
    "daily_task": {
      "used": 0,
      "limit": 1,
      "reset_at": "2026-01-02T00:00:00.000Z"
    },
    "runtask": {
      "used": 0,
      "limit": 1,
      "reset_at": "2026-01-02T00:00:00.000Z"
    },
    "season_task": {
      "used": 0,
      "limit": 1,
      "reset_at": "2026-01-02T00:00:00.000Z"
    },
    "event_task": {
      "used": 0,
      "limit": 1,
      "reset_at": "2026-01-02T00:00:00.000Z"
    }
  }
}

================================================================
9. login_rewards
================================================================
GET /api/login_rewards

响应：
{
  "success": true,
  "streak": 3,
  "today_claimed": false,
  "rewards": [
    {
      "day": 1,
      "reward_type": "candle",
      "amount": 1,
      "claimed": true
    },
    {
      "day": 2,
      "reward_type": "heart",
      "amount": 1,
      "claimed": true
    },
    {
      "day": 3,
      "reward_type": "season_candle",
      "amount": 1,
      "claimed": false
    }
  ]
}

================================================================
10. 领取 login_reward
================================================================
POST /api/login_rewards/claim

请求体（加密前明文）：
{}

响应：
{
  "success": true,
  "reward": {
    "day": 3,
    "reward_type": "season_candle",
    "amount": 1,
    "claimed_at": "2026-01-01T00:00:00.000Z"
  }
}