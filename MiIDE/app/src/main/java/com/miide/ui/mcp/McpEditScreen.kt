package com.miide.ui.mcp

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.core.designsystem.components.MiCardGroup
import com.miide.core.designsystem.theme.MiColors
import com.miide.tools.mcp.McpTransport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun McpEditScreen(
    serverId: String?,
    onBack: () -> Unit,
    onSaved: () -> Unit,
    viewModel: McpEditViewModel = hiltViewModel()
) {
    val vm = viewModel

    LaunchedEffect(serverId) {
        vm.load(serverId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (serverId == null) "添加 MCP 服务器" else "编辑 MCP 服务器") },
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
                    enabled = !vm.testing && !vm.saving,
                    modifier = Modifier.weight(1f)
                ) {
                    if (vm.testing) {
                        CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(if (vm.testing) "连接中…" else "测试连接")
                }
                Button(
                    onClick = { vm.save(onSaved) },
                    enabled = !vm.saving && !vm.testing,
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
            vm.testOutcome?.let { outcome ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = MaterialTheme.shapes.small,
                    color = (if (outcome.success) MiColors.Success else MaterialTheme.colorScheme.error).copy(alpha = 0.12f)
                ) {
                    Text(
                        text = outcome.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (outcome.success) MiColors.Success else MaterialTheme.colorScheme.error,
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

            MiCardGroup {
                Column(Modifier.padding(vertical = 8.dp)) {
                    FormField(
                        value = vm.name,
                        onValueChange = { vm.name = it },
                        label = "服务器名称",
                        placeholder = "例如：本机文件系统"
                    )
                    TransportDropdown(
                        value = vm.transport,
                        onSelect = { vm.transport = it }
                    )
                    FormField(
                        value = vm.endpoint,
                        onValueChange = { vm.endpoint = it },
                        label = "服务器地址",
                        placeholder = if (vm.transport == McpTransport.SSE)
                            "https://example.com/mcp/sse（事件流地址）"
                        else "https://example.com/mcp（HTTP 端点）",
                        singleLine = true
                    )
                }
            }

            Text(
                text = "请求头（可选）",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            MiCardGroup {
                Column(Modifier.padding(vertical = 8.dp)) {
                    OutlinedTextField(
                        value = vm.headersText,
                        onValueChange = { vm.headersText = it },
                        label = { Text("每行一个：Header: Value") },
                        placeholder = {
                            Text("Authorization: Bearer xxx")
                        },
                        minLines = 3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp)
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
                        Text("启用该服务器", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "关闭后其工具不会提供给 AI",
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
    singleLine: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { if (placeholder.isNotBlank()) Text(placeholder) },
        singleLine = singleLine,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransportDropdown(
    value: McpTransport,
    onSelect: (McpTransport) -> Unit
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
            value = value.displayName(),
            onValueChange = {},
            readOnly = true,
            label = { Text("传输方式") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            McpTransport.entries.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.displayName()) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

private fun McpTransport.displayName(): String = when (this) {
    McpTransport.HTTP -> "HTTP（推荐）"
    McpTransport.SSE -> "SSE（事件流）"
    McpTransport.STDIO -> "STDIO（需 Termux，暂未支持）"
}
