package com.skyauto.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.AuthViewModel

private enum class AuthMode { LOGIN, REGISTER, RESET }

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    var mode by remember { mutableStateOf(AuthMode.LOGIN) }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }

    val loading by viewModel.loginLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFE8F0FF), Color(0xFFF7F3FF))))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 品牌
            Box(
                Modifier
                    .size(72.dp)
                    .background(Brush.linearGradient(listOf(HyperBlue, HyperLavender)), RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("光", color = Color.White, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(16.dp))
            Text("光遇助手", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
            Text("SkyAuto · 全功能 API 原生客户端", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(32.dp))

            // 模式切换
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                ModeChip("登录", mode == AuthMode.LOGIN) { mode = AuthMode.LOGIN }
                ModeChip("注册", mode == AuthMode.REGISTER) { mode = AuthMode.REGISTER }
                ModeChip("找回", mode == AuthMode.RESET) { mode = AuthMode.RESET }
            }
            Spacer(Modifier.height(20.dp))

            when (mode) {
                AuthMode.LOGIN -> {
                    AuthField(Icons.Rounded.Email, "QQ 邮箱", email, { email = it }, KeyboardType.Email)
                    Spacer(Modifier.height(12.dp))
                    AuthField(Icons.Rounded.Lock, "密码", password, { password = it }, KeyboardType.Password)
                    error?.let { Spacer(Modifier.height(8.dp)); AuthError(it) }
                    Spacer(Modifier.height(20.dp))
                    AuthButton("登 录", loading) { viewModel.login(email, password) }
                }
                AuthMode.REGISTER -> {
                    AuthField(Icons.Rounded.Email, "QQ 邮箱", email, { email = it }, KeyboardType.Email)
                    Spacer(Modifier.height(12.dp))
                    AuthField(Icons.Rounded.Person, "用户名", username, { username = it }, KeyboardType.Text)
                    Spacer(Modifier.height(12.dp))
                    AuthField(Icons.Rounded.Lock, "密码", password, { password = it }, KeyboardType.Password)
                    error?.let { Spacer(Modifier.height(8.dp)); AuthError(it) }
                    Spacer(Modifier.height(20.dp))
                    AuthButton("注 册", loading) { viewModel.register(email, username, password) }
                }
                AuthMode.RESET -> {
                    AuthField(Icons.Rounded.Email, "QQ 邮箱", email, { email = it }, KeyboardType.Email)
                    Spacer(Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = code,
                            onValueChange = { code = it },
                            modifier = Modifier.weight(1f),
                            label = { Text("验证码") },
                            shape = RoundedCornerShape(18.dp),
                            singleLine = true
                        )
                        Spacer(Modifier.size(8.dp))
                        TextButton(onClick = { viewModel.sendResetCode(email) }) {
                            Text("发送验证码")
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    AuthField(Icons.Rounded.Lock, "新密码", password, { password = it }, KeyboardType.Password)
                    error?.let { Spacer(Modifier.height(8.dp)); AuthError(it) }
                    Spacer(Modifier.height(20.dp))
                    AuthButton("重置密码", loading) { viewModel.resetPassword(email, code, password) { if (it) mode = AuthMode.LOGIN } }
                }
            }
        }
    }
}

@Composable
private fun ModeChip(label: String, selected: Boolean, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(
            label,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun AuthField(
    icon: ImageVector,
    label: String,
    value: String,
    onChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        leadingIcon = { Icon(icon, null, tint = MaterialTheme.colorScheme.onSurfaceVariant) },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = Color.White.copy(alpha = 0.7f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.5f)
        )
    )
}

@Composable
private fun AuthError(message: String) {
    Text(message, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
}

@Composable
private fun AuthButton(text: String, loading: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = !loading,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = GrassGreen)
    ) {
        if (loading) {
            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
        } else {
            Text(text, fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.SemiBold)
        }
    }
}