package com.miide.ui.ai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.core.designsystem.components.MiCardGroup
import com.miide.core.designsystem.components.MiEmptyState
import com.miide.core.designsystem.theme.MiColors
import com.miide.core.model.ProviderConfig
import com.miide.core.model.ProviderType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiSettingsScreen(
    onBack: () -> Unit,
    onAddProvider: () -> Unit,
    onEditProvider: (String) -> Unit,
    viewModel: AiSettingsViewModel = hiltViewModel()
) {
    val providers by viewModel.providers.collectAsState()
    val defaultId by viewModel.defaultProviderId.collectAsState()

    var deleteTarget by remember { mutableStateOf<ProviderConfig?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI 设置") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(onClick = onAddProvider) {
                        Icon(Icons.Default.Add, contentDescription = "添加供应商")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->
        if (providers.isEmpty()) {
            MiEmptyState(
                title = "还没有配置 AI 供应商",
                subtitle = "添加一个供应商并填入 API Key 即可开始",
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "供应商",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                items(providers, key = { it.id }) { config ->
                    ProviderCard(
                        config = config,
                        isDefault = config.id == defaultId,
                        onEdit = { onEditProvider(config.id) },
                        onToggle = { viewModel.setEnabled(config, it) },
                        onSetDefault = { viewModel.setDefault(config.id) },
                        onDelete = { deleteTarget = config }
                    )
                }
            }
        }
    }

    deleteTarget?.let { target ->
        AlertDialog(
            onDismissRequest = { deleteTarget = null },
            title = { Text("删除供应商") },
            text = { Text("确定要删除「${target.name}」吗？该操作不可恢复。") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.delete(target)
                        deleteTarget = null
                    }
                ) { Text("删除", color = MaterialTheme.colorScheme.error) }
            },
            dismissButton = {
                TextButton(onClick = { deleteTarget = null }) { Text("取消") }
            }
        )
    }
}

@Composable
private fun ProviderCard(
    config: ProviderConfig,
    isDefault: Boolean,
    onEdit: () -> Unit,
    onToggle: (Boolean) -> Unit,
    onSetDefault: () -> Unit,
    onDelete: () -> Unit
) {
    MiCardGroup {
        Surface(
            onClick = onEdit,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            color = Color.Transparent
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProviderAvatar(type = config.type)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = config.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (isDefault) {
                            Spacer(Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "默认",
                                tint = MiColors.AccentOrange,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "${config.type.displayName} · ${config.protocol.displayName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = config.baseUrl.ifBlank { "未设置 Base URL" },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    )
                }
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "编辑")
                }
            }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = config.enabled,
                onCheckedChange = onToggle,
                modifier = Modifier.size(width = 48.dp, height = 32.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "启用",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.weight(1f))
            if (!isDefault) {
                TextButton(onClick = onSetDefault) { Text("设为默认") }
            } else {
                AssistChip(
                    onClick = {},
                    enabled = false,
                    label = { Text("默认供应商") }
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "删除",
                    tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                )
            }
        }
    }
}

/** 供应商品牌色圆形头像（取类型名首字母）。 */
@Composable
private fun ProviderAvatar(type: ProviderType) {
    val tint = when (type) {
        ProviderType.OPENAI -> MiColors.AccentGreen
        ProviderType.DEEPSEEK -> MiColors.AccentBlue
        ProviderType.ZHIPU_GLM -> MiColors.AccentCyan
        ProviderType.KIMI -> MiColors.AccentViolet
        ProviderType.QWEN -> MiColors.AccentOrange
        ProviderType.ANTHROPIC -> MiColors.AccentPink
        ProviderType.GEMINI -> MiColors.AccentBlue
        ProviderType.CUSTOM -> MiColors.AccentViolet
    }
    Surface(shape = MaterialTheme.shapes.medium, color = tint.copy(alpha = 0.16f)) {
        Text(
            text = type.displayName.take(1),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = tint,
            modifier = Modifier.padding(10.dp)
        )
    }
}
