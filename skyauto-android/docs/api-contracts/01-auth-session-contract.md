{
  "contract": {
    "base": {
      "api": "/api",
    "ws": "/ws",
      "override": "GET /api/config/site 的 config.serverEndpoints.api 可覆盖默认值"
    },

    "encryption": {
      "algorithm": "AES-256-GCM",
      "key_bytes": 32,
      "iv_bytes": 12,
      "tag_bits": 128,
      "key_url": "/api/crypto/key",
      "key_method": "GET",
      "key_credentials": "include",
      "key_request_encrypted": false,
      "key_response": {
        "success": {"type": "boolean"},
        "key": {"type": "string", "description": "base64(raw 32 bytes AES key)"}
      },
      "key_cache": "全局缓存；POST /auth/login、/auth/logout、/auth/register 前后都会清空，并清空 /auth/me 的缓存",
      "request_encryption_condition": "method != GET 且存在 body 且 URL 包含 /api/",
      "request_format": "base64(IV(12B) || AES-GCM ciphertext(含128bit tag))",
      "request_headers": {
        "Content-Type": "application/json",
        "X-Encrypted": "1"
      },
      "response_decryption_condition": "响应头 X-Encrypted == '1'",
      "response_decryption": "base64 decode -> 前12字节为IV -> AES-GCM decrypt -> UTF-8 文本 -> JSON.parse",
      "decryption_failure_behavior": "前端返回原始 Response，由调用方 catch 处理"
    },

    "session": {
      "type": "cookie",
      "fetch_credentials": "include",
      "cookie_name": "未在 JS 中硬编码，由服务端 Set-Cookie 决定",
      "android_requirement": "使用 CookieJar/CookieManager 自动保存并回传服务端 Set-Cookie"
    },

    "endpoints": {
      "cryptoKey": {
        "method": "GET",
        "url": "/api/crypto/key",
        "request_encrypted": false,
        "credentials": "include",
        "response_encrypted": "如果响应头 X-Encrypted:1 则解密",
        "response": {
          "success": {"type": "boolean"},
          "key": {"type": "string", "description": "base64 原始 AES-256-GCM 密钥"}
        }
      },

      "authLogin": {
        "method": "POST",
        "url": "/api/auth/login",
        "request_encrypted": true,
        "credentials": "include",
        "headers": {
          "Content-Type": "application/json",
          "X-Encrypted": "1"
        },
        "plaintext_body": {
          "required": ["account", "login_type", "password", "device_id"],
          "properties": {
            "account": {"type": "string"},
            "login_type": {
              "type": "string",
              "enum": ["email", "mobile", "vivo", "huawei"],
              "description": "email=邮箱账号, mobile=手机号, vivo=vivo渠道账号, huawei=华为渠道账号"
            },
            "password": {"type": "string"},
            "device_id": {"type": "integer", "description": "设备/模拟设备 ID，必须是 number，不能是 string"}
          }
        },
        "response_encrypted": "如果响应头 X-Encrypted:1 则解密后再解析",
        "response": {
          "success": {"type": "boolean"},
          "message": {"type": "string"},
          "user": {"type": "object", "description": "可选；给定 JS 上下文中前端只消费 success，user 字段未展开"}
        }
      },

      "authMe": {
        "method": "GET",
        "url": "/api/auth/me",
        "request_encrypted": false,
        "credentials": "include",
        "response_encrypted": "如果响应头 X-Encrypted:1 则解密",
        "response": {
          "success": {"type": "boolean"},
          "user": {"type": "object", "description": "前端仅用 success 判断登录态，user 字段未在给定上下文中消费"}
        }
      },

      "authRegister": {
        "method": "POST",
        "url": "/api/auth/register",
        "request_encrypted": true,
        "credentials": "include",
        "headers": {
          "Content-Type": "application/json",
          "X-Encrypted": "1"
        },
        "plaintext_body": {
          "required": ["account", "login_type", "password", "device_id"],
          "properties": {
            "account": {"type": "string"},
            "login_type": {
              "type": "string",
              "enum": ["email", "mobile", "vivo", "huawei"]
            },
            "password": {"type": "string"},
            "device_id": {"type": "integer"}
          },
          "note": "注册请求体未在给定 JS 上下文中直接展开，按 login 同构推断；以后端实际校验为准"
        },
        "response_encrypted": "如果响应头 X-Encrypted:1 则解密后再解析",
        "response": {
          "success": {"type": "boolean"},
          "message": {"type": "string"},
          "user": {"type": "object", "optional": true}
        }
      },

      "authLogout": {
        "method": "POST",
        "url": "/api/auth/logout",
        "request_encrypted": false,
        "description": "无 body 时不加密；若客户端主动传 body，则按通用规则加密",
        "credentials": "include",
        "response_encrypted": "如果响应头 X-Encrypted:1 则解密",
        "response": {
          "success": {"type": "boolean"},
          "message": {"type": "string"}
        }
      },

      "authSendResetCode": {
        "method": "GET",
        "url": "/api/auth/send-reset-code/{encodeURIComponent(email)}",
        "request_encrypted": false,
        "credentials": "include",
        "response_encrypted": "如果响应头 X-Encrypted:1 则解密",
        "response": {
          "success": {"type": "boolean"},
          "message": {"type": "string"}
        }
      }
    },

    "cacheInvalidation": {
      "trigger": "任意非 GET 请求且 URL 包含 /auth/login、/auth/logout、/auth/register",
      "clears": [
        "/auth/me 的缓存",
        "AES-GCM key 缓存"
      ],
      "timing": "请求发送前一次，请求返回后一次"
    },

    "loginSequence": [
      "解析 API base：默认 /api，若已拉取 /api/config/site 则使用 config.serverEndpoints.api。",
      "首次需要加密请求时，GET {api}/crypto/key，credentials=include，取 {success,key}。",
      "base64 decode key，得到 32 字节 AES-256-GCM 密钥。",
      "构造明文 JSON：{account, login_type, password, device_id}。",
      "生成 12 字节随机 IV，AES-GCM 加密明文 UTF-8。",
      "wire body = base64(IV || ciphertext)，请求头保留 Content-Type: application/json，并加 X-Encrypted:1。",
      "POST {api}/auth/login，credentials=include。",
      "若响应头 X-Encrypted:1，则 base64 decode -> 前12字节IV -> AES-GCM解密 -> JSON.parse。",
      "无论成功失败，login/logout/register 都会清空 key 缓存和 /auth/me 缓存。",
      "登录后调用 GET {api}/auth/me 确认登录态。"
    ],

    "errorConventions": {
      "business_error": "HTTP 状态通常仍为 200，业务错误以 success=false 和 message 表示",
      "numeric_error_code": "未在给定 JS 中观察到数字错误码",
      "network_or_decrypt_error": "前端抛 Error(message)，message 来自异常或接口返回",
      "add_account_poll_status": {
        "need_sms_code": "需要短信验证，请稍后使用单账号登录继续",
        "need_sms_verify": "需要短信验证，请稍后使用单账号登录继续",
        "finished": {
          "status_success": "添加成功",
          "status_other": "取 logs 最后一条 message，否则返回 登录失败"
        }
      }
    }
  },

  "notes": [
    "未从给定 JS 上下文中提取到硬编码页面跳转；登录态由 /auth/me 的 success 控制。",
    "Cookie 名称、HttpOnly、SameSite 均未在前端硬编码，Android 端应把 Set-Cookie 当不透明数据自动管理。",
    "所有业务响应都先按 success 判断，不能只依赖 HTTP status。",
    "auth/send-reset-code 按站内事实为 GET 路径参数；如果实测服务端是 POST，则请求体需按通用加密规则加密。"
  ]
}