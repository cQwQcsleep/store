package com.miide.ui.git

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.ForkRight
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.core.designsystem.components.MiCardGroup
import com.miide.core.designsystem.theme.MiColors
import com.miide.core.git.GitBranch
import com.miide.core.git.GitCommit
import com.miide.core.git.GitStatusEntry
import com.miide.core.git.StatusKind
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitScreen(
    onBack: () -> Unit,
    viewModel: GitViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    var showClone by remember { mutableStateOf(false) }
    var showInit by remember { mutableStateOf(false) }
    var showBranches by remember { mutableStateOf(false) }
    var showAddRemote by remember { mutableStateOf(false) }
    var commitMsg by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Git 集成") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(onClick = { showClone = true }) {
                        Icon(Icons.Default.Download, contentDescription = "克隆")
                    }
                    IconButton(onClick = { showInit = true }) {
                        Icon(Icons.Default.Add, contentDescription = "初始化")
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
            // 仓库选择栏
            RepoHeader(
                repoPath = state.repoPath,
                isRepo = state.isRepo,
                branch = state.currentBranch,
                message = state.message,
                error = state.error,
                busy = state.busy,
                onOpen = { viewModel.openRepo(it) },
                onRefresh = { viewModel.refresh() },
                onBranches = { showBranches = true }
            )

            if (!state.isRepo) {
                EmptyRepo(
                    onClone = { showClone = true },
                    onInit = { showInit = true }
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { SectionTitle("变更") }
                    if (state.status.isEmpty()) {
                        item {
                            Text(
                                text = "工作区干净，没有未提交的变更",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        }
                    } else {
                        items(state.status, key = { it.path }) { entry ->
                            StatusRow(
                                entry = entry,
                                onStage = { viewModel.stage(listOf(entry.path)) },
                                onUnstage = { viewModel.unstage(listOf(entry.path)) },
                                onDiff = { viewModel.showDiff(listOf(entry.path)) }
                            )
                        }
                        item {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Surface(
                                    onClick = { viewModel.stage(emptyList()) },
                                    shape = MaterialTheme.shapes.small,
                                    color = MiColors.AccentBlue.copy(alpha = 0.14f)
                                ) {
                                    Text(
                                        "全部暂存",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MiColors.AccentBlue,
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                    )
                                }
                                Surface(
                                    onClick = { viewModel.unstage(emptyList()) },
                                    shape = MaterialTheme.shapes.small,
                                    color = MaterialTheme.colorScheme.surfaceVariant
                                ) {
                                    Text(
                                        "全部取消",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }

                    item { SectionTitle("提交") }
                    item {
                        MiCardGroup {
                            Column(Modifier.padding(12.dp)) {
                                OutlinedTextField(
                                    value = commitMsg,
                                    onValueChange = { commitMsg = it },
                                    placeholder = { Text("提交信息") },
                                    modifier = Modifier.fillMaxWidth(),
                                    minLines = 2
                                )
                                Spacer(Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    TextButton(onClick = { viewModel.pull() }) {
                                        Icon(Icons.Default.ArrowDownward, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(Modifier.width(4.dp))
                                        Text("拉取")
                                    }
                                    TextButton(onClick = { viewModel.push() }) {
                                        Icon(Icons.Default.Upload, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(Modifier.width(4.dp))
                                        Text("推送")
                                    }
                                    Spacer(Modifier.width(8.dp))
                                    TextButton(
                                        onClick = {
                                            if (commitMsg.isNotBlank()) {
                                                viewModel.commit(commitMsg)
                                                commitMsg = ""
                                            }
                                        }
                                    ) { Text("提交", color = MiColors.AccentBlue) }
                                }
                            }
                        }
                    }

                    item { SectionTitle("提交记录") }
                    if (state.commits.isEmpty()) {
                        item {
                            Text(
                                text = "暂无提交",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    } else {
                        items(state.commits) { c ->
                            CommitRow(c)
                        }
                    }

                    item { SectionTitle("远程") }
                    if (state.remotes.isEmpty()) {
                        item {
                            Text(
                                text = "未配置远程仓库",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    } else {
                        items(state.remotes) { r ->
                            MiCardGroup {
                                Row(
                                    Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Tag, contentDescription = null, tint = MiColors.AccentCyan, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(8.dp))
                                    Column(Modifier.weight(1f)) {
                                        Text(r.name, style = MaterialTheme.typography.titleSmall)
                                        Text(r.url, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                    }
                                }
                            }
                        }
                    }
                    item {
                        TextButton(onClick = { showAddRemote = true }) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("添加远程")
                        }
                    }
                }
            }
        }
    }

    // diff 预览
    if (state.diffText.isNotBlank()) {
        AlertDialog(
            onDismissRequest = { viewModel.showDiff(null); viewModel.refresh() },
            title = { Text("差异预览") },
            text = {
                LazyColumn(Modifier.height(320.dp)) {
                    item {
                        Text(
                            text = state.diffText,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { viewModel.showDiff(null); viewModel.refresh() }) { Text("关闭") }
            }
        )
    }

    if (showClone) {
        CloneDialog(
            onDismiss = { showClone = false },
            onConfirm = { url, name, branch ->
                viewModel.cloneRemote(url, name, branch)
                showClone = false
            }
        )
    }
    if (showInit) {
        InitDialog(
            onDismiss = { showInit = false },
            onConfirm = { name ->
                viewModel.initRepo(name)
                showInit = false
            }
        )
    }
    if (showBranches) {
        BranchDialog(
            branches = state.branches,
            onDismiss = { showBranches = false },
            onCheckout = { name ->
                viewModel.checkout(name)
                showBranches = false
            },
            onCreate = { name ->
                viewModel.checkout(name, create = true)
                showBranches = false
            }
        )
    }
    if (showAddRemote) {
        AddRemoteDialog(
            onDismiss = { showAddRemote = false },
            onConfirm = { name, url ->
                viewModel.addRemote(name, url)
                showAddRemote = false
            }
        )
    }
}

@Composable
private fun RepoHeader(
    repoPath: String,
    isRepo: Boolean,
    branch: String,
    message: String?,
    error: String?,
    busy: Boolean,
    onOpen: (String) -> Unit,
    onRefresh: () -> Unit,
    onBranches: () -> Unit
) {
    var input by remember { mutableStateOf(repoPath) }
    Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                placeholder = { Text("输入仓库绝对路径") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Surface(
                onClick = { onOpen(input) },
                shape = MaterialTheme.shapes.small,
                color = MiColors.AccentBlue.copy(alpha = 0.16f)
            ) {
                Text(
                    "打开",
                    style = MaterialTheme.typography.labelLarge,
                    color = MiColors.AccentBlue,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                )
            }
        }
        if (repoPath.isNotBlank()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isRepo) {
                    Icon(Icons.Default.ForkRight, contentDescription = null, tint = MiColors.AccentGreen, modifier = Modifier.size(16.dp))
                    Surface(
                        onClick = onBranches,
                        shape = MaterialTheme.shapes.small,
                        color = MiColors.AccentGreen.copy(alpha = 0.12f)
                    ) {
                        Text(
                            branch.ifBlank { "(detached)" },
                            style = MaterialTheme.typography.labelMedium,
                            color = MiColors.AccentGreen,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                    Text(
                        text = repoPath.substringAfterLast('/'),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        maxLines = 1
                    )
                }
                Spacer(Modifier.weight(1f))
                if (busy) {
                    Text("处理中…", style = MaterialTheme.typography.labelMedium, color = MiColors.AccentOrange)
                }
            }
        }
        message?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = MiColors.AccentGreen,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        error?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun EmptyRepo(onClone: () -> Unit, onInit: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.ForkRight, contentDescription = null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(48.dp))
        Spacer(Modifier.height(12.dp))
        Text("打开一个 Git 仓库，或克隆 / 初始化新仓库", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Surface(onClick = onClone, shape = MaterialTheme.shapes.medium, color = MiColors.AccentBlue.copy(alpha = 0.16f)) {
                Text("从远程克隆", color = MiColors.AccentBlue, style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp))
            }
            Surface(onClick = onInit, shape = MaterialTheme.shapes.medium, color = MaterialTheme.colorScheme.surfaceVariant) {
                Text("初始化新仓库", color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp))
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
}

@Composable
private fun StatusRow(
    entry: GitStatusEntry,
    onStage: () -> Unit,
    onUnstage: () -> Unit,
    onDiff: () -> Unit
) {
    val (label, color) = when (entry.status) {
        StatusKind.ADDED -> "新增" to MiColors.AccentGreen
        StatusKind.MODIFIED -> "修改" to MiColors.AccentBlue
        StatusKind.DELETED -> "删除" to MiColors.AccentRed
        StatusKind.UNTRACKED -> "未跟踪" to MiColors.AccentOrange
        StatusKind.CONFLICTING -> "冲突" to MiColors.AccentPink
    }
    MiCardGroup {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable(onClick = onDiff),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(shape = MaterialTheme.shapes.small, color = color.copy(alpha = 0.14f)) {
                Text(
                    label,
                    style = MaterialTheme.typography.labelSmall,
                    color = color,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            Text(
                text = entry.path,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily.Monospace,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onDiff) {
                Icon(Icons.Default.ContentCopy, contentDescription = "diff", tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), modifier = Modifier.size(18.dp))
            }
            IconButton(onClick = onStage) {
                Icon(Icons.Default.Check, contentDescription = "暂存", tint = MiColors.AccentGreen, modifier = Modifier.size(18.dp))
            }
            IconButton(onClick = onUnstage) {
                Icon(Icons.Default.Save, contentDescription = "取消暂存", tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f), modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
private fun CommitRow(c: GitCommit) {
    val fmt = remember { SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()) }
    MiCardGroup {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(c.message, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f), maxLines = 2)
                Text(fmt.format(Date(c.time)), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
            }
            Spacer(Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(shape = MaterialTheme.shapes.small, color = MiColors.AccentCyan.copy(alpha = 0.12f)) {
                    Text(c.shortId, style = MaterialTheme.typography.labelSmall, color = MiColors.AccentCyan, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                }
                Spacer(Modifier.width(8.dp))
                Text(c.author, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
            }
        }
    }
}

@Composable
private fun CloneDialog(
    onDismiss: () -> Unit,
    onConfirm: (url: String, name: String, branch: String?) -> Unit
) {
    var url by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var branch by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("克隆仓库") },
        text = {
            Column {
                OutlinedTextField(value = url, onValueChange = { url = it }, label = { Text("远程 URL") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("目录名（留空自动）") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = branch, onValueChange = { branch = it }, label = { Text("分支（留空默认）") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(url.trim(), name.trim(), branch.trim().ifBlank { null }) }) { Text("克隆") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } }
    )
}

@Composable
private fun InitDialog(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("初始化新仓库") },
        text = {
            Column {
                Text("将创建在应用沙箱目录下的 git 文件夹中", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("仓库目录名") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            }
        },
        confirmButton = { TextButton(onClick = { onConfirm(name.trim()) }) { Text("初始化") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } }
    )
}

@Composable
private fun BranchDialog(
    branches: List<GitBranch>,
    onDismiss: () -> Unit,
    onCheckout: (String) -> Unit,
    onCreate: (String) -> Unit
) {
    var newName by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("分支") },
        text = {
            Column {
                LazyColumn(Modifier.height(220.dp)) {
                    items(branches, key = { it.name }) { b ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCheckout(b.name) }
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.ForkRight, contentDescription = null, tint = if (b.isCurrent) MiColors.AccentGreen else MaterialTheme.colorScheme.outline, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(b.name, style = MaterialTheme.typography.bodyMedium, color = if (b.isCurrent) MiColors.AccentGreen else MaterialTheme.colorScheme.onSurface, modifier = Modifier.weight(1f))
                            if (b.isCurrent) Text("当前", style = MaterialTheme.typography.labelSmall, color = MiColors.AccentGreen)
                        }
                    }
                }
                HorizontalDivider()
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(value = newName, onValueChange = { newName = it }, label = { Text("新建分支") }, singleLine = true, modifier = Modifier.weight(1f))
                    Spacer(Modifier.width(8.dp))
                    TextButton(onClick = { if (newName.isNotBlank()) onCreate(newName.trim()) }) { Text("创建") }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("关闭") } }
    )
}

@Composable
private fun AddRemoteDialog(onDismiss: () -> Unit, onConfirm: (String, String) -> Unit) {
    var name by remember { mutableStateOf("origin") }
    var url by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加远程") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("名称") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = url, onValueChange = { url = it }, label = { Text("URL") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            }
        },
        confirmButton = { TextButton(onClick = { onConfirm(name.trim(), url.trim()) }) { Text("添加") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("取消") } }
    )
}