package com.miide.ui.remote

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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.core.designsystem.components.MiCardGroup
import com.miide.core.designsystem.theme.MiColors
import com.miide.core.remote.RemoteAuth
import com.miide.core.remote.RemoteEntry
import com.miide.core.remote.RemoteProfile

/**
 * 远程 SSH/SFTP 开发：主机配置管理 + 远程文件浏览/打开 + 远程命令执行。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoteScreen(
    onBack: () -> Unit,
    onOpenFile: (uri: String, name: String) -> Unit,
    viewModel: RemoteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val profiles by viewModel.profiles.collectAsState()

    var editing by remember { mutableStateOf<RemoteProfile?>(null) }
    var showEditDialog by remember { mutableStateOf(false) }
    var execCmd by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("远程开发") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    if (state.connectedProfile != null) {
                        TextButton(onClick = { viewModel.disconnect() }) {
                            Text("断开", color = MaterialTheme.colorScheme.error)
                        }
                    } else {
                        IconButton(onClick = {
                            editing = null
                            showEditDialog = true
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "添加主机")
                        }
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
            state.error?.let { err ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.errorContainer
                ) {
                    Text(
                        text = err,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                    )
                }
            }

            if (state.connectedProfile != null) {
                RemoteBrowser(
                    state = state,
                    execCmd = execCmd,
                    onExecCmdChange = { execCmd = it },
                    onRefresh = { viewModel.connect(state.connectedProfile!!) },
                    onNavigate = { path -> viewModel.navigateToDir(path) },
                    onGoUp = { parent -> viewModel.navigateToDir(parent) },
                    onOpenFile = { entry ->
                        viewModel.readFile(entry.path)
                        // 读取完成后通过回调把内容桥接到编辑器
                    },
                    onExec = { viewModel.execCommand(execCmd); execCmd = "" },
                    onOpenInEditor = { name, path ->
                        val pending = RemoteOpenBridge.Pending(
                            name = name,
                            content = state.fileContent.orEmpty(),
                            profileId = state.connectedProfile!!.id,
                            remotePath = path
                        )
                        RemoteOpenBridge.pending = pending
                        onOpenFile("", name)
                    }
                )
            } else {
                RemoteProfileList(
                    profiles = profiles,
                    loading = state.loading,
                    onConnect = { viewModel.connect(it) },
                    onEdit = { profile ->
                        editing = profile
                        showEditDialog = true
                    },
                    onDelete = { viewModel.deleteProfile(it) }
                )
            }
        }
    }

    if (showEditDialog) {
        RemoteProfileDialog(
            initial = editing,
            onDismiss = { showEditDialog = false },
            onSave = { profile ->
                viewModel.upsertProfile(profile)
                showEditDialog = false
            }
        )
    }
}

/** 未连接：主机列表。 */
@Composable
private fun RemoteProfileList(
    profiles: List<RemoteProfile>,
    loading: Boolean,
    onConnect: (RemoteProfile) -> Unit,
    onEdit: (RemoteProfile) -> Unit,
    onDelete: (RemoteProfile) -> Unit
) {
    if (profiles.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.Dns,
                contentDescription = null,
                tint = MiColors.AccentViolet,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "暂无远程主机",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "点击右上角 + 添加 SSH 主机，\n支持密码与私钥两种认证",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        return
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "已保存的主机",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
        items(profiles, key = { it.id }) { profile ->
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
                        .clickable { onConnect(profile) }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Cloud,
                        contentDescription = null,
                        tint = MiColors.AccentBlue
                    )
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            profile.displayName,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            "${profile.username}@${profile.host}:${profile.port}  ·  ${
                                if (profile.authType == RemoteAuth.PASSWORD) "密码认证" else "私钥认证"
                            }",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Text(
                        if (loading) "连接中…" else "连接",
                        style = MaterialTheme.typography.labelLarge,
                        color = MiColors.AccentBlue
                    )
                    Spacer(Modifier.width(4.dp))
                    IconButton(onClick = { onEdit(profile) }, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Edit, contentDescription = "编辑", modifier = Modifier.size(18.dp))
                    }
                    IconButton(onClick = { onDelete(profile) }, modifier = Modifier.size(32.dp)) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "删除",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

/** 已连接：文件浏览 + 命令执行。 */
@Composable
private fun RemoteBrowser(
    state: com.miide.ui.remote.RemoteUiState,
    execCmd: String,
    onExecCmdChange: (String) -> Unit,
    onRefresh: () -> Unit,
    onNavigate: (String) -> Unit,
    onGoUp: (String) -> Unit,
    onOpenFile: (RemoteEntry) -> Unit,
    onExec: () -> Unit,
    onOpenInEditor: (name: String, path: String) -> Unit
) {
    val profile = state.connectedProfile ?: return
    Column(modifier = Modifier.fillMaxSize()) {
        // 路径栏
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onGoUp(parentOf(state.currentPath)) }, modifier = Modifier.size(32.dp)) {
                Icon(Icons.Default.ArrowUpward, contentDescription = "上一级", modifier = Modifier.size(18.dp))
            }
            IconButton(onClick = onRefresh, modifier = Modifier.size(32.dp)) {
                Icon(Icons.Default.Refresh, contentDescription = "刷新", modifier = Modifier.size(18.dp))
            }
            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        profile.displayName,
                        style = MaterialTheme.typography.labelMedium,
                        color = MiColors.AccentViolet
                    )
                    Text("  ›  ", style = MaterialTheme.typography.labelMedium)
                    Text(
                        state.currentPath,
                        style = MaterialTheme.typography.labelMedium,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        HorizontalDivider()

        // 文件列表
        if (state.loading) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(state.entries, key = { it.path }) { entry ->
                    RemoteEntryRow(entry = entry, onOpen = {
                        if (entry.isDir) onNavigate(entry.path) else onOpenFile(entry)
                    })
                }
                if (state.fileContent != null) {
                    item {
                        // 读取文件后显示"在编辑器中打开"
                        val fileContent = state.fileContent!!
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    "文件预览（${fileContent.length} 字符）",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    fileContent.take(800) + if (fileContent.length > 800) "\n…" else "",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontFamily = FontFamily.Monospace,
                                    maxLines = 20,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.height(8.dp))
                                TextButton(onClick = { onOpenInEditor(state.fileNameHint, state.filePathHint) }) {
                                    Text("在编辑器中打开")
                                }
                            }
                        }
                    }
                }
            }
        }

        HorizontalDivider()

        // 命令执行
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Terminal, contentDescription = null, tint = MiColors.AccentGreen, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                OutlinedTextField(
                    value = execCmd,
                    onValueChange = onExecCmdChange,
                    placeholder = { Text("远程执行命令，如 ls -la /") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    textStyle = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.width(8.dp))
                TextButton(onClick = onExec, enabled = execCmd.isNotBlank()) {
                    Text("执行")
                }
            }
            state.execResult?.let { result ->
                Spacer(Modifier.height(8.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 240.dp),
                    shape = MaterialTheme.shapes.small,
                    color = Color(0xFF0D1117)
                ) {
                    Text(
                        text = result.ifBlank { "(无输出)" },
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Monospace,
                        color = Color(0xFF9ECBFF),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

@Composable
private fun RemoteEntryRow(entry: RemoteEntry, onOpen: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onOpen)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (entry.isDir) Icons.Default.Folder else Icons.Default.InsertDriveFile,
            contentDescription = null,
            tint = if (entry.isDir) MiColors.AccentOrange else MiColors.AccentBlue,
            modifier = Modifier.size(22.dp)
        )
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = entry.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = if (entry.isDir) "目录" else formatSize(entry.size) + "  ·  " + entry.permissions,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
        if (!entry.isDir) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

/** 新增/编辑远程主机对话框。 */
@Composable
private fun RemoteProfileDialog(
    initial: RemoteProfile?,
    onDismiss: () -> Unit,
    onSave: (RemoteProfile) -> Unit
) {
    var name by remember { mutableStateOf(initial?.name ?: "") }
    var host by remember { mutableStateOf(initial?.host ?: "") }
    var port by remember { mutableStateOf((initial?.port ?: 22).toString()) }
    var username by remember { mutableStateOf(initial?.username ?: "") }
    var authType by remember { mutableStateOf(initial?.authType ?: RemoteAuth.PASSWORD) }
    var password by remember { mutableStateOf(initial?.password ?: "") }
    var privateKey by remember { mutableStateOf(initial?.privateKey ?: "") }
    var passphrase by remember { mutableStateOf(initial?.passphrase ?: "") }
    var basePath by remember { mutableStateOf(initial?.basePath ?: "/") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initial == null) "添加远程主机" else "编辑远程主机") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("名称（可选）") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = host,
                    onValueChange = { host = it },
                    label = { Text("主机地址") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = port,
                        onValueChange = { port = it.filter(Char::isDigit) },
                        label = { Text("端口") },
                        singleLine = true,
                        modifier = Modifier.weight(0.4f)
                    )
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("用户名") },
                        singleLine = true,
                        modifier = Modifier.weight(0.6f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("认证方式", style = MaterialTheme.typography.labelLarge)
                    Spacer(Modifier.width(12.dp))
                    TextButton(onClick = { authType = RemoteAuth.PASSWORD }) {
                        Text(if (authType == RemoteAuth.PASSWORD) "● " else "○ " + "密码")
                    }
                    TextButton(onClick = { authType = RemoteAuth.PRIVATE_KEY }) {
                        Text(if (authType == RemoteAuth.PRIVATE_KEY) "● " else "○ " + "私钥")
                    }
                }
                if (authType == RemoteAuth.PASSWORD) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("密码") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    OutlinedTextField(
                        value = privateKey,
                        onValueChange = { privateKey = it },
                        label = { Text("PEM 私钥内容") },
                        minLines = 4,
                        maxLines = 8,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = passphrase,
                        onValueChange = { passphrase = it },
                        label = { Text("私钥口令（可选）") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = basePath,
                    onValueChange = { basePath = it },
                    label = { Text("初始路径") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val profile = RemoteProfile(
                        id = initial?.id ?: "",
                        name = name,
                        host = host.trim(),
                        port = port.toIntOrNull() ?: 22,
                        username = username.trim(),
                        authType = authType,
                        password = password,
                        privateKey = privateKey,
                        passphrase = passphrase,
                        basePath = basePath.ifBlank { "/" },
                        createdAt = initial?.createdAt ?: System.currentTimeMillis()
                    )
                    onSave(profile)
                },
                enabled = host.isNotBlank() && username.isNotBlank()
            ) {
                Text("保存")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("取消") }
        }
    )
}

private fun parentOf(path: String): String {
    if (path == "/") return "/"
    val trimmed = path.trimEnd('/')
    val idx = trimmed.lastIndexOf('/')
    return if (idx <= 0) "/" else trimmed.substring(0, idx)
}

private fun formatSize(bytes: Long): String = when {
    bytes >= 1_048_576 -> String.format(java.util.Locale.US, "%.1f MB", bytes / 1048576.0)
    bytes >= 1024 -> String.format(java.util.Locale.US, "%.1f KB", bytes / 1024.0)
    else -> "$bytes B"
}
