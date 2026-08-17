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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.core.designsystem.components.MiCardGroup
import com.miide.core.model.ProviderPresets
import com.miide.core.model.ProviderProtocol
import com.miide.core.model.ProviderType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProviderEditScreen(
    providerId: String?,
    onBack: () -> Unit,
    onSaved: () -> Unit,
    viewModel: ProviderEditViewModel = hiltViewModel()
) {
    val vm = viewModel

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (providerId == null) "添加供应商" else "编辑供应商") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { vm.testConnection() },
                    enabled = !vm.testing,
                    modifier = Modifier.weight(1f)
                ) {
                    if (vm.testing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(if (vm.testing) "测试中…" else "测试连接")
                }
                Button(
                    onClick = { vm.save(onSaved) },
                    enabled = !vm.saving,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("保存")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // 测试结果提示
            vm.testOutcome?.let { outcome ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = MaterialTheme.shapes.small,
                    color = (if (outcome.success) com.miide.core.designsystem.theme.MiColors.Success
                    else MaterialTheme.colorScheme.error).copy(alpha = 0.12f)
                ) {
                    Text(
                        text = outcome.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (outcome.success) com.miide.core.designsystem.theme.MiColors.Success
                        else MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            vm.errorMessage?.let { message ->
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }

            if (providerId == null) {
                PresetQuickAdd(onApply = { vm.applyPreset(it) })
            }

            MiCardGroup {
                Column(Modifier.padding(vertical = 8.dp)) {
                    FormField(
                        value = vm.name,
                        onValueChange = { vm.name = it },
                        label = "供应商名称",
                        placeholder = "例如：DeepSeek 官方"
                    )
                    EnumDropdown(
                        value = vm.type,
                        label = "供应商类型",
                        options = ProviderType.entries,
                        display = { it.displayName },
                        onSelect = { vm.type = it }
                    )
                    EnumDropdown(
                        value = vm.protocol,
                        label = "协议",
                        options = ProviderProtocol.entries,
                        display = { it.displayName },
                        onSelect = { vm.protocol = it }
                    )
                    FormField(
                        value = vm.baseUrl,
                        onValueChange = { vm.baseUrl = it },
                        label = "Base URL",
                        placeholder = "https://api.deepseek.com/v1",
                        singleLine = true
                    )
                    PasswordField(
                        value = vm.apiKey,
                        onValueChange = { vm.apiKey = it },
                        label = "API Key"
                    )
                }
            }

            Text(
                text = "模型",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            MiCardGroup {
                Column(Modifier.padding(vertical = 8.dp)) {
                    OutlinedTextField(
                        value = vm.modelsText,
                        onValueChange = { vm.modelsText = it },
                        label = { Text("模型 ID（每行一个）") },
                        placeholder = {
                            Text(
                                "deepseek-chat\nchatglm_turbo\nkimi-k2"
                            )
                        },
                        minLines = 4,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                    EnumDropdown(
                        value = vm.defaultModelId,
                        label = "默认模型",
                        options = vm.modelsText.lines().map { it.trim() }.filter { it.isNotBlank() }.distinct(),
                        display = { it },
                        onSelect = { vm.defaultModelId = it }
                    )
                }
            }

            MiCardGroup {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text("启用该供应商", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "关闭后 AI 对话不会使用它",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                    Switch(checked = vm.enabled, onCheckedChange = { vm.enabled = it })
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    singleLine: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { if (placeholder.isNotBlank()) Text(placeholder) },
        singleLine = singleLine,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    )
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var visible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    imageVector = if (visible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = if (visible) "隐藏" else "显示"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    )
}

/** 一键添加官方预设：横向滚动供应商 chips，点击即填充表单。 */
@Composable
private fun PresetQuickAdd(onApply: (ProviderPresets.Preset) -> Unit) {
    Column(Modifier.padding(vertical = 8.dp)) {
        Text(
            text = "官方预设 · 一键配置",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "选择国内主流供应商，自动填入官方 Base URL 与默认模型",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ProviderPresets.all, key = { it.type.name }) { preset ->
                val tint = when (preset.type) {
                    ProviderType.DEEPSEEK -> com.miide.core.designsystem.theme.MiColors.AccentBlue
                    ProviderType.ZHIPU_GLM -> com.miide.core.designsystem.theme.MiColors.AccentCyan
                    ProviderType.KIMI -> com.miide.core.designsystem.theme.MiColors.AccentViolet
                    ProviderType.QWEN -> com.miide.core.designsystem.theme.MiColors.AccentOrange
                    else -> com.miide.core.designsystem.theme.MiColors.AccentGreen
                }
                Surface(
                    onClick = { onApply(preset) },
                    shape = MaterialTheme.shapes.medium,
                    color = tint.copy(alpha = 0.14f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = preset.type.displayName.take(1),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = tint
                        )
                        Text(
                            text = preset.name,
                            style = MaterialTheme.typography.labelLarge,
                            color = tint
                        )
                    }
                }
            }
        }
    }
}

/** 通用下拉选择器。 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun <T> EnumDropdown(
    value: T?,
    label: String,
    options: List<T>,
    display: (T) -> String,
    onSelect: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        OutlinedTextField(
            value = value?.let(display) ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(display(option)) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
