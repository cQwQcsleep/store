package com.skyauto.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Construction
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skyauto.app.BuildConfig
import com.skyauto.app.data.network.MaintenanceMonitor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

/**
 * 服务器维护中 —— 全屏覆盖页。
 * 检测到接口维护时覆盖在一切之上，阻断进入功能区，提供重试按钮。
 */
@Composable
fun MaintenanceScreen(
    modifier: Modifier = Modifier
) {
    val underMaintenance by MaintenanceMonitor.underMaintenance.collectAsState()
    val scope = rememberCoroutineScope()
    var checking by remember { mutableStateOf(false) }

    // 重试：探测 key 接口是否恢复，恢复后复位维护状态
    fun retry() {
        checking = true
        scope.launch {
            val recovered = withContext(Dispatchers.IO) { probeKey() }
            delay(300)
            if (recovered) {
                MaintenanceMonitor.clear()
            }
            checking = false
        }
    }

    if (!underMaintenance) return

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF6C8CFF), Color(0xFF9A8CFF))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Rounded.Construction,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(46.dp)
                )
            }

            Spacer(Modifier.height(28.dp))

            Text(
                "服务器维护中",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(10.dp))

            Text(
                "平台正在更新，请稍后再试。\n恢复后 App 将自动进入。",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = { retry() },
                enabled = !checking,
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {
                if (checking) {
                    Text("检测中…", style = MaterialTheme.typography.titleMedium)
                } else {
                    Icon(Icons.Rounded.Refresh, contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.padding(start = 8.dp))
                    Text("重试", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

/** 探测 crypto/key 接口是否已恢复（返回 JSON 即恢复）。 */
private fun probeKey(): Boolean {
    return runCatching {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
        val req = Request.Builder()
            .url(BuildConfig.API_BASE_URL + "/crypto/key")
            .get()
            .build()
        client.newCall(req).execute().use { resp ->
            if (!resp.isSuccessful) return false
            val text = resp.body?.string() ?: return false
            // 恢复标准：能解析出 success=true 的 JSON key
            val json = runCatching { org.json.JSONObject(text) }.getOrNull() ?: return false
            json.optBoolean("success", false) && json.has("key")
        }
    }.getOrDefault(false)
}