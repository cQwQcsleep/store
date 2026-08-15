package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.WarmAmber
import com.skyauto.app.ui.viewmodel.AccountAddViewModel
import com.skyauto.app.ui.viewmodel.AccountsViewModel

private val PLATFORMS = listOf(
    "huawei" to "华为",
    "vivo" to "Vivo",
    "bilibili" to "B站",
    "oppo" to "九游",
    "honor" to "荣耀",
    "xiaomi" to "小米",
    "bytedance" to "字节",
    "wechat" to "微信",
    "visitor" to "游客"
)

@Composable
fun AccountsScreen(
    viewModel: AccountsViewModel = hiltViewModel(),
    addViewModel: AccountAddViewModel = hiltViewModel()
) {
    val accounts by viewModel.accounts.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    val adding by addViewModel.adding.collectAsState()
    val addMessage by addViewModel.message.collectAsState()
    val logs by addViewModel.logs.collectAsState()
    val smsTarget by addViewModel.smsTarget.collectAsState()
    val smsContent by addViewModel.smsContent.collectAsState()
    val needHuaweiTrust by addViewModel.needHuaweiTrust.collectAsState()
    val needHuaweiCaptcha by addViewModel.needHuaweiCaptcha.collectAsState()
    val needBilibiliBrowser by addViewModel.needBilibiliBrowser.collectAsState()
    val needHonorBrowser by addViewModel.needHonorBrowser.collectAsState()
    val needVivoSubaccount by addViewModel.needVivoSubaccount.collectAsState()
    val needUcAccount by addViewModel.needUcAccount.collectAsState()

    var selectedPlatform by rememberSaveable { mutableStateOf("huawei") }
    var accountInput by rememberSaveable { mutableStateOf("") }
    var passwordInput by rememberSaveable { mutableStateOf("") }
    var loginType by rememberSaveable { mutableStateOf("") }
    var smsCode by rememberSaveable { mutableStateOf("") }

    AppScreen(title = "账号管理") {
        GradientHero(
            title = "我的账号",
            subtitle = "多账号管理 · 多渠道添加",
            colors = listOf(HyperBlue, HyperLavender)
        )

        HyperLoader(loading)
        message?.let {
            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }

        // 账号列表
        if (accounts.isEmpty() && !loading) {
            EmptyState("暂无账号，请在下方添加账号")
        } else {
            accounts.forEach { account ->
                GlassCard {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(
                                account.displayUsername ?: account.account ?: "未命名账号",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            account.deviceName?.let {
                                Text("设备：$it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        Text(statusText(account.status), style = MaterialTheme.typography.labelMedium, color = statusColor(account.status))
                    }
                    Row(Modifier.fillMaxWidth().padding(top = 6.dp)) {
                        Text("平台：${account.platform ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                        Text("登录方式：${account.loginType ?: "-"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    account.lastActivityAt?.let {
                        Text("最近活动：${it.take(16).replace("T", " ")}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }

        Spacer(Modifier.height(4.dp))

        // 添加账号
        SectionTitle("添加账号")
        GlassCard {
            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth()) {
                    PLATFORMS.chunked(3).forEach { row ->
                        Column(Modifier.weight(1f)) {
                            row.forEach { (key, label) ->
                                FilterChip(
                                    selected = selectedPlatform == key,
                                    onClick = { selectedPlatform = key },
                                    label = { Text(label, style = MaterialTheme.typography.labelMedium) },
                                    modifier = Modifier.padding(vertical = 3.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = accountInput,
                    onValueChange = { accountInput = it },
                    label = { Text("账号 / 手机号") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(6.dp))
                OutlinedTextField(
                    value = passwordInput,
                    onValueChange = { passwordInput = it },
                    label = { Text("密码（可选）") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(6.dp))
                OutlinedTextField(
                    value = loginType,
                    onValueChange = { loginType = it },
                    label = { Text("登录方式（可选，如 account/phone）") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(10.dp))
                Button(
                    onClick = {
                        addViewModel.start(selectedPlatform, accountInput, passwordInput, loginType.ifBlank { null })
                    },
                    enabled = !adding,
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HyperBlue),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Icon(Icons.Outlined.Add, null)
                    Text(if (adding) "添加中…" else "开始添加", modifier = Modifier.padding(start = 6.dp))
                }

                addMessage?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(it, style = MaterialTheme.typography.bodySmall, color = if (it.contains("成功")) GrassGreen else WarmAmber)
                }

                // 需要扫码/浏览器步骤
                if (needHuaweiTrust || needHuaweiCaptcha || needBilibiliBrowser || needHonorBrowser || needVivoSubaccount || needUcAccount) {
                    Spacer(Modifier.height(10.dp))
                    GlassCard {
                        Column(Modifier.fillMaxWidth()) {
                            Text("需要人工确认的步骤", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            if (needHuaweiTrust) Text("• 华为需开启信任弹窗", style = MaterialTheme.typography.bodySmall)
                            if (needHuaweiCaptcha) Text("• 华为需完成验证码", style = MaterialTheme.typography.bodySmall)
                            if (needBilibiliBrowser) Text("• B站需浏览器扫码授权", style = MaterialTheme.typography.bodySmall)
                            if (needHonorBrowser) Text("• 荣耀需浏览器扫码授权", style = MaterialTheme.typography.bodySmall)
                            if (needVivoSubaccount) Text("• Vivo 需子账号信息", style = MaterialTheme.typography.bodySmall)
                            if (needUcAccount) Text("• 九游需 UC 账号信息", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }

                // 短信验证
                if (smsTarget != null && smsContent != null) {
                    Spacer(Modifier.height(10.dp))
                    GlassCard {
                        Column(Modifier.fillMaxWidth()) {
                            Text("短信验证", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text("发送至：$smsTarget", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("内容：$smsContent", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            OutlinedTextField(
                                value = smsCode,
                                onValueChange = { smsCode = it },
                                label = { Text("验证码") },
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(8.dp))
                            Button(
                                onClick = { addViewModel.submitSms(smsCode) },
                                shape = RoundedCornerShape(18.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("提交验证码")
                            }
                        }
                    }
                }

                // 日志
                if (logs.isNotEmpty()) {
                    Spacer(Modifier.height(10.dp))
                    Column {
                        Text("任务日志", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        logs.takeLast(8).forEach { log ->
                            Text(
                                "${log.time?.take(16)?.replace("T", " ")}  ${log.message ?: ""}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                if (adding) {
                    Spacer(Modifier.height(8.dp))
                    TextButton(onClick = { addViewModel.cancel() }) {
                        Text("取消添加", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}

private fun statusText(status: String?): String = when (status) {
    "在线" -> "在线"
    "离线" -> "离线"
    "pending", "待处理" -> "待处理"
    else -> status ?: "未知"
}

@Composable
private fun statusColor(status: String?): androidx.compose.ui.graphics.Color = when (status) {
    "在线" -> GrassGreen
    "离线" -> MaterialTheme.colorScheme.onSurfaceVariant
    else -> MaterialTheme.colorScheme.tertiary
}
