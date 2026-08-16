package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.BuildConfig
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.theme.CoralRed
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.AuthViewModel
import com.skyauto.app.ui.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    onLogout: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val user by authViewModel.currentUser.collectAsState()

    AppScreen(title = "设置") {
        GradientHero(
            title = "设置",
            subtitle = user?.username ?: "账户信息",
            colors = listOf(HyperBlue, HyperLavender)
        )

        GlassCard {
            SettingsRow(Icons.Outlined.Person, "用户名", user?.username ?: "-")
            SettingsRow(Icons.Outlined.Notifications, "邮箱", user?.email ?: "-")
            SettingsRow(Icons.Outlined.Info, "角色", user?.role ?: "-")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                settingsViewModel.logout { onLogout() }
            },
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = CoralRed),
            modifier = Modifier.fillMaxWidth().height(52.dp)
        ) {
            Icon(Icons.Outlined.ExitToApp, null)
            Text("退出登录")
        }

        Text(
            "SkyAuto v${BuildConfig.VERSION_NAME} · 澎湃 HyperOS 设计 · 数据来自 sky.angin.cn",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
    }
}

@Composable
private fun SettingsRow(icon: ImageVector, label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(end = 12.dp))
        Text(label, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Text(value, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}