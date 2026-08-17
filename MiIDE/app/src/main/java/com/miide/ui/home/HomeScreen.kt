package com.miide.ui.home

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.core.designsystem.components.MiCardGroup
import com.miide.core.designsystem.components.MiGradientBar
import com.miide.core.designsystem.theme.MiColors
import com.miide.ui.chat.ChatBottomPanel

@Composable
fun HomeScreen(
    onOpenNewFile: () -> Unit,
    onOpenFile: (uri: String, name: String) -> Unit,
    onOpenAiSettings: () -> Unit,
    onOpenChat: () -> Unit,
    onOpenBrowser: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val openDocument = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            // 申请持久化读写权限，便于编辑器内保存回写
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
            val name = uri.lastPathSegment?.substringAfterLast('/')?.ifBlank { null }
                ?: "file.txt"
            onOpenFile(uri.toString(), name)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                MiGradientBar()

                Column(Modifier.padding(24.dp)) {
                    Text(
                        text = "MiIDE",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "AI 优先的安卓开发环境",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                }

                // 快捷入口
                MiCardGroup {
                    HomeActionItem(
                        icon = Icons.Default.Code,
                        tint = MiColors.AccentBlue,
                        title = "新建文件",
                        subtitle = "打开空白编辑器",
                        onClick = onOpenNewFile
                    )
                    HomeActionItem(
                        icon = Icons.Default.FolderOpen,
                        tint = MiColors.AccentOrange,
                        title = "打开文件",
                        subtitle = "从设备选择文件",
                        onClick = { openDocument.launch(arrayOf("*/*")) }
                    )
                    HomeActionItem(
                        icon = Icons.Default.Settings,
                        tint = MiColors.AccentViolet,
                        title = "AI 设置",
                        subtitle = "配置 AI 供应商与模型",
                        onClick = onOpenAiSettings
                    )
                    HomeActionItem(
                        icon = Icons.Default.Public,
                        tint = MiColors.AccentBlue,
                        title = "浏览器",
                        subtitle = "网页浏览 / 实时预览 / DevTools",
                        onClick = onOpenBrowser
                    )
                }

                Spacer(Modifier.height(20.dp))

                // AI 配置状态
                MiCardGroup {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CloudDone,
                            contentDescription = null,
                            tint = if (uiState.enabledCount > 0) MiColors.AccentGreen else MiColors.AccentRed,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(Modifier.size(14.dp))
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = if (uiState.enabledCount > 0) "已就绪" else "尚未配置 AI 供应商",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = if (uiState.enabledCount > 0) {
                                    "已启用 ${uiState.enabledCount} 个供应商，点击「AI 设置」管理"
                                } else {
                                    "配置 API Key 后即可开始 AI 辅助编程"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                        if (uiState.enabledCount > 0) {
                            AssistChip(
                                onClick = onOpenAiSettings,
                                label = { Text("管理") }
                            )
                        } else {
                            Surface(
                                onClick = onOpenAiSettings,
                                shape = MaterialTheme.shapes.small,
                                color = MiColors.AccentViolet,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(Modifier.size(4.dp))
                                    Text("添加", style = MaterialTheme.typography.labelLarge)
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
            }

            // 底部 AI 对话面板
            ChatBottomPanel(onExpand = onOpenChat)
        }
    }
}

@Composable
private fun HomeActionItem(
    icon: ImageVector,
    tint: androidx.compose.ui.graphics.Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = androidx.compose.ui.graphics.Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
                color = tint.copy(alpha = 0.14f)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp)
                )
            }
            Spacer(Modifier.size(14.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Text(
                text = "›",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        }
    }
}

