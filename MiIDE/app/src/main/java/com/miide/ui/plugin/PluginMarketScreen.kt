package com.miide.ui.plugin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.core.designsystem.components.MiCardGroup
import com.miide.core.designsystem.theme.MiColors
import com.miide.core.plugin.InstalledPlugin
import com.miide.core.plugin.PluginManifest
import com.miide.core.plugin.PluginPackage
import com.miide.core.plugin.PluginType

/**
 * 插件市场（基础版）：浏览内置目录、安装 / 卸载 / 启停、运行脚本插件。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PluginMarketScreen(
    onBack: () -> Unit,
    viewModel: PluginMarketViewModel = hiltViewModel()
) {
    val installed by viewModel.installed.collectAsState()
    val state by viewModel.state.collectAsState()

    var detail by remember { mutableStateOf<PluginPackage?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("插件市场") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            state.message?.let { msg ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.clearMessage() }
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = msg,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        Text("×", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
                    }
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // 已安装插件
                item {
                    SectionHeader("已安装（${installed.size}）")
                }
                if (installed.isEmpty()) {
                    item {
                        Text(
                            "暂无已安装插件，从下方市场安装",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                } else {
                    items(installed.values.sortedBy { it.installedAt }) { plugin ->
                        InstalledRow(
                            plugin = plugin,
                            running = state.runningPluginId == plugin.manifest.id,
                            onToggle = { viewModel.setEnabled(plugin.manifest.id, it) },
                            onUninstall = { viewModel.uninstall(plugin.manifest.id, plugin.manifest.name) },
                            onRun = { viewModel.runScript(plugin.manifest) }
                        )
                    }
                }

                // 运行结果
                state.runResult?.let { result ->
                    item {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        "运行结果",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        "${result.exitCode}  ${result.durationMs}ms",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = if (result.exitCode == 0) MiColors.AccentGreen else MaterialTheme.colorScheme.error
                                    )
                                }
                                Spacer(Modifier.height(6.dp))
                                if (result.stderr.isNotBlank()) {
                                    Text(
                                        result.stderr,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontFamily = FontFamily.Monospace,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                    Spacer(Modifier.height(4.dp))
                                }
                                if (result.stdout.isNotBlank()) {
                                    Text(
                                        result.stdout,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontFamily = FontFamily.Monospace,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                if (result.emits.isNotEmpty()) {
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        "动作流: ${result.emits.joinToString(" → ")}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MiColors.AccentBlue
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(8.dp))
                    SectionHeader("市场")
                }
                items(viewModel.catalog) { pkg ->
                    val isInstalled = installed.containsKey(pkg.manifest.id)
                    CatalogRow(
                        pkg = pkg,
                        installed = isInstalled,
                        onDetail = { detail = pkg },
                        onInstall = { viewModel.install(pkg) }
                    )
                }
                item { Spacer(Modifier.height(24.dp)) }
            }
        }
    }

    detail?.let { pkg ->
        PluginDetailDialog(
            pkg = pkg,
            installed = installed.containsKey(pkg.manifest.id),
            busy = state.busy,
            onDismiss = { detail = null },
            onInstall = {
                viewModel.install(pkg)
                detail = null
            }
        )
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Composable
private fun InstalledRow(
    plugin: InstalledPlugin,
    running: Boolean,
    onToggle: (Boolean) -> Unit,
    onUninstall: () -> Unit,
    onRun: () -> Unit
) {
    val m = plugin.manifest
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = MaterialTheme.shapes.medium,
        color = if (plugin.enabled) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.15f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PluginIcon(m)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    m.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = if (plugin.enabled) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
                Text(
                    "v${m.version}  ·  ${typeLabel(m.type)}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
            if (m.type == PluginType.SCRIPT) {
                IconButton(onClick = onRun, enabled = plugin.enabled && !running) {
                    if (running) {
                        CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                    } else {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "运行",
                            tint = MiColors.AccentGreen,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            Switch(
                checked = plugin.enabled,
                onCheckedChange = onToggle,
                modifier = Modifier.size(width = 48.dp, height = 30.dp)
            )
            IconButton(onClick = onUninstall) {
                Icon(Icons.Default.Build, contentDescription = "卸载", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
private fun CatalogRow(
    pkg: PluginPackage,
    installed: Boolean,
    onDetail: () -> Unit,
    onInstall: () -> Unit
) {
    val m = pkg.manifest
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDetail)
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PluginIcon(m)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    m.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    m.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    "v${m.version}  ·  ${typeLabel(m.type)}  ·  ${m.author}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
                )
            }
            Spacer(Modifier.width(8.dp))
            if (installed) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = MiColors.AccentGreen, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("已安装", color = MiColors.AccentGreen, style = MaterialTheme.typography.labelLarge)
                }
            } else {
                TextButton(onClick = onInstall) {
                    Text("安装")
                }
            }
        }
    }
}

@Composable
private fun PluginDetailDialog(
    pkg: PluginPackage,
    installed: Boolean,
    busy: Boolean,
    onDismiss: () -> Unit,
    onInstall: () -> Unit
) {
    val m = pkg.manifest
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(m.name) },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    "v${m.version}  ·  ${typeLabel(m.type)}  ·  ${m.author}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Spacer(Modifier.height(8.dp))
                Text(m.description, style = MaterialTheme.typography.bodyMedium)
                if (m.permissions.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "权限声明: ${m.permissions.joinToString("、")}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                if (m.entry != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "入口: ${m.entry}",
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        },
        confirmButton = {
            if (installed) {
                TextButton(onClick = onDismiss) { Text("完成") }
            } else {
                TextButton(onClick = onInstall, enabled = !busy) { Text("安装") }
            }
        },
        dismissButton = {
            if (!installed) {
                TextButton(onClick = onDismiss) { Text("取消") }
            }
        }
    )
}

@Composable
private fun PluginIcon(m: PluginManifest) {
    val icon: ImageVector = when {
        m.icon == "palette" -> Icons.Default.Palette
        m.icon == "commit" || m.icon == "git" -> Icons.Default.Build
        m.icon == "data" || m.icon == "schedule" || m.icon == "list" -> Icons.Default.DataObject
        else -> Icons.Default.Extension
    }
    val tint = when (m.type) {
        PluginType.SCRIPT -> MiColors.AccentBlue
        PluginType.THEME -> MiColors.AccentViolet
        PluginType.FEATURE -> MiColors.AccentGreen
    }
    Surface(shape = MaterialTheme.shapes.small, color = tint.copy(alpha = 0.14f)) {
        Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.padding(8.dp).size(20.dp))
    }
}

private fun typeLabel(type: PluginType): String = when (type) {
    PluginType.SCRIPT -> "脚本插件"
    PluginType.THEME -> "主题包"
    PluginType.FEATURE -> "功能包"
}
