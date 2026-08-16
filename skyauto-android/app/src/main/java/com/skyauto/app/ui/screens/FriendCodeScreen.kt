package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.ui.components.AccountPicker
import com.skyauto.app.ui.components.EmptyState
import com.skyauto.app.ui.components.GlassCard
import com.skyauto.app.ui.components.GradientHero
import com.skyauto.app.ui.components.HyperLoader
import com.skyauto.app.ui.components.SectionTitle
import com.skyauto.app.ui.theme.GrassGreen
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.theme.SunOrange
import com.skyauto.app.ui.viewmodel.FriendCodeViewModel

@Composable
fun FriendCodeScreen(viewModel: FriendCodeViewModel = hiltViewModel()) {
    val accounts by viewModel.accounts.collectAsState()
    val selectedId by viewModel.selectedAccountId.collectAsState()
    val invites by viewModel.invites.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()

    var nickname by remember { mutableStateOf("") }
    var acceptLink by remember { mutableStateOf("") }

    AppScreen(title = "好友码") {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            GradientHero(
                title = "好友码",
                subtitle = "邀请码 · 好友互加邀请",
                colors = listOf(HyperBlue, HyperLavender)
            )

            HyperLoader(loading)
            message?.let {
                Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
            }

            AccountPicker(accounts, selectedId, viewModel::loadAccount)

            // ---- 生成好友码 ----
            SectionTitle("生成好友码")
            GlassCard {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = nickname,
                        onValueChange = { nickname = it },
                        label = { Text("备注昵称 (可选)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp)
                    )
                    Button(
                        onClick = { viewModel.generate(nickname) },
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("生成好友码")
                    }
                }
            }

            // ---- 接受好友码 ----
            Spacer(Modifier.height(8.dp))
            SectionTitle("接受好友码")
            GlassCard {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = acceptLink,
                        onValueChange = { acceptLink = it },
                        label = { Text("好友码或链接") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp)
                    )
                    Button(
                        onClick = { viewModel.accept(acceptLink) },
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("接受好友码")
                    }
                }
            }

            // ---- 我的邀请码 ----
            Spacer(Modifier.height(8.dp))
            SectionTitle("我的邀请码")
            if (invites.isEmpty() && !loading) {
                EmptyState("暂无好友码")
            } else {
                invites.forEach { invite ->
                    GlassCard {
                        Column(Modifier.fillMaxWidth()) {
                            Row(Modifier.fillMaxWidth()) {
                                Text(
                                    invite.code ?: "-",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.weight(1f)
                                )
                                invite.status?.let {
                                    Text(it, style = MaterialTheme.typography.bodySmall, color = if (it.contains("有效") || it.contains("已开启")) GrassGreen else SunOrange)
                                }
                            }
                            invite.note?.let {
                                Spacer(Modifier.height(4.dp))
                                Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            invite.inviteUrl?.let {
                                Spacer(Modifier.height(4.dp))
                                Text("链接：${it}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}