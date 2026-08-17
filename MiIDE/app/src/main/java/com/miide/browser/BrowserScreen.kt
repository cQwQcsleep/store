package com.miide.browser

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Http
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowserScreen(
    onBack: () -> Unit,
    onOpenChat: () -> Unit = {},
    viewModel: BrowserViewModel = hiltViewModel()
) {
    val tabs by viewModel.tabs.collectAsState()
    val activeIndex by viewModel.activeIndex.collectAsState()
    val address by viewModel.address.collectAsState()
    val devtools by viewModel.devtools.collectAsState()
    var showDevtools by remember { mutableStateOf(false) }
    var addressText by remember { mutableStateOf("") }

    val activeTab = tabs.getOrNull(activeIndex)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("浏览器", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    // 把当前页交给 AI 抓取总结
                    IconButton(
                        onClick = {
                            val url = activeTab?.url.orEmpty()
                            if (url.startsWith("http://") || url.startsWith("https://")) {
                                com.miide.ui.chat.AiAskBridge.askFetch(url)
                                onOpenChat()
                            }
                        },
                        enabled = activeTab?.url?.startsWith("http") == true
                    ) {
                        Icon(Icons.Default.SmartToy, contentDescription = "交给 AI 抓取")
                    }
                    IconButton(onClick = { showDevtools = !showDevtools }) {
                        Icon(Icons.Default.BugReport, contentDescription = "DevTools")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 地址栏
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.goBack() }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "后退", modifier = Modifier.size(20.dp))
                }
                IconButton(onClick = { viewModel.goForward() }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "前进", modifier = Modifier.size(20.dp))
                }
                IconButton(onClick = { viewModel.reload() }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Refresh, contentDescription = "刷新", modifier = Modifier.size(20.dp))
                }
                TextField(
                    value = addressText,
                    onValueChange = { addressText = it },
                    placeholder = { Text("输入 URL 或搜索", style = MaterialTheme.typography.bodySmall) },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                Spacer(Modifier.width(4.dp))
                IconButton(onClick = { viewModel.navigate(addressText) }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "前往", modifier = Modifier.size(20.dp))
                }
            }

            // 标签栏
            TabRow(
                selectedTabIndex = activeIndex.coerceIn(0, tabs.lastIndex),
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == activeIndex,
                        onClick = { viewModel.selectTab(index) },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    tab.title.takeIf { it.isNotBlank() } ?: tab.url,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.labelSmall
                                )
                                if (tabs.size > 1) {
                                    IconButton(
                                        onClick = { viewModel.closeTab(tab.id) },
                                        modifier = Modifier.size(20.dp)
                                    ) {
                                        Icon(Icons.Default.Close, contentDescription = "关闭标签", modifier = Modifier.size(14.dp))
                                    }
                                }
                            }
                        }
                    )
                }
            }

            // WebView 内容区
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (activeTab != null) {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { viewModel.webViewFor(activeTab) },
                        update = { }
                    )
                }
            }

            // DevTools 面板
            if (showDevtools) {
                DevToolsPanel(
                    devtools = devtools,
                    onCaptureDom = { viewModel.captureDom() },
                    onClear = { viewModel.clearDevTools() }
                )
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose { }
    }
}

@Composable
private fun DevToolsPanel(
    devtools: DevToolsData,
    onCaptureDom: () -> Unit,
    onClear: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Terminal, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("Console", style = MaterialTheme.typography.labelMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Spacer(Modifier.width(4.dp))
                Icon(Icons.Default.Http, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("Network (${devtools.requests.size})", style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.width(4.dp))
                IconButton(onClick = onCaptureDom, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Code, contentDescription = "抓取 DOM", modifier = Modifier.size(16.dp))
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = onClear, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Close, contentDescription = "清除", modifier = Modifier.size(16.dp))
                }
            }
            Spacer(Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                devtools.console.takeLast(50).forEach { entry ->
                    Text(
                        text = "[${entry.level}] ${entry.message}",
                        style = MaterialTheme.typography.labelSmall,
                        color = when (entry.level) {
                            "ERROR" -> Color.Red
                            "WARNING" -> Color(0xFFFFA000)
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}