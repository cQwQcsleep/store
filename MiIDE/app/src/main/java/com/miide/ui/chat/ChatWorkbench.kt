package com.miide.ui.chat

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.DataUsage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miide.core.designsystem.theme.MiColors

/** 格式化毫秒为「分:秒」或「秒」。 */
fun formatElapsed(ms: Long): String {
    val totalSeconds = ms / 1000
    if (totalSeconds < 60) return "${totalSeconds}s"
    return "${totalSeconds / 60}m ${totalSeconds % 60}s"
}

/**
 * 工作台状态栏：当前状态 + 耗时 + token 消耗。
 * 让用户在等待时对「AI 在干什么、花了多久、烧了多少 token」一目了然。
 */
@Composable
fun ChatStatusBar(
    status: String,
    isStreaming: Boolean,
    elapsedMs: Long,
    usage: UsageInfo?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // 状态
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(7.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if (isStreaming) MiColors.AccentGreen else MaterialTheme.colorScheme.outlineVariant)
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = status,
                style = MaterialTheme.typography.labelSmall,
                color = if (isStreaming) MiColors.AccentGreen else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        if (isStreaming || elapsedMs > 0) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(13.dp)
                )
                Spacer(Modifier.width(3.dp))
                Text(
                    text = formatElapsed(elapsedMs),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
        usage?.let {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.DataUsage,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(13.dp)
                )
                Spacer(Modifier.width(3.dp))
                Text(
                    text = "${it.totalTokens} tokens",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

/** 动作类型 → 图标与颜色（用于动作流时间线）。 */
private fun activityVisual(kind: ActivityKind): Pair<ImageVector, Color> = when (kind) {
    ActivityKind.THINK -> Icons.Default.Psychology to MiColors.AccentViolet
    ActivityKind.TOOL -> Icons.Default.Handyman to MiColors.AccentCyan
    ActivityKind.READ -> Icons.AutoMirrored.Filled.MenuBook to MiColors.AccentBlue
    ActivityKind.WRITE -> Icons.Default.Edit to MiColors.AccentOrange
    ActivityKind.RUN -> Icons.Default.PlayArrow to MiColors.AccentGreen
    ActivityKind.FETCH -> Icons.Default.Language to MiColors.AccentBlue
    ActivityKind.DONE -> Icons.Default.CheckCircle to MiColors.AccentGreen
    ActivityKind.STOPPED -> Icons.Default.StopCircle to MiColors.AccentRed
    ActivityKind.QUEUE -> Icons.Default.Schedule to MiColors.AccentCyan
}

/**
 * 实时动作流：AI 正在/最近做了什么，现场直播式时间线。
 * 只展示最近 4 条，新动作自动滚到最底部。
 */
@Composable
fun ActivityStream(
    entries: List<ActivityEntry>,
    modifier: Modifier = Modifier,
    maxVisible: Int = 4
) {
    if (entries.isEmpty()) return
    val visible = entries.takeLast(maxVisible)
    val listState = rememberLazyListState()
    LaunchedEffect(entries.size) {
        listState.animateScrollToItem((visible.size - 1).coerceAtLeast(0))
    }
    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxWidth()
            .height((48 * visible.size.coerceAtMost(4)).dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(visible, key = { index, _ -> index }) { _, entry ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val (icon, color) = activityVisual(entry.kind)
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = entry.text,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * 跑马灯小窗口：AI 的全部输出（思考 + 正文 + 动作）在这里实时滚过。
 * 有内容时自动滚到底部，用户随时瞄一眼即可掌握 AI 动态。
 */
@Composable
fun TickerBar(
    text: String,
    modifier: Modifier = Modifier,
    height: Int = 48
) {
    if (text.isBlank()) return
    val scroll = rememberScrollState()
    LaunchedEffect(text) {
        scroll.animateScrollTo(scroll.maxValue)
    }
    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MiColors.AccentCyan)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "AI 输出跑马灯",
                style = MaterialTheme.typography.labelSmall,
                color = MiColors.AccentCyan
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ) {
            Box(Modifier.height(height.dp), contentAlignment = Alignment.BottomStart) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scroll)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}

/**
 * 改动 diff 预览：AI 修改当前文件时，展示将改什么（新增/删除/上下文行），
 * 可接受 / 拒绝。
 */
@Composable
fun DiffPreview(
    pending: PendingDiff,
    onAccept: () -> Unit,
    onReject: () -> Unit,
    modifier: Modifier = Modifier
) {
    val diff = remember(pending) { DiffUtils.compute(pending.before, pending.after) }
    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = MiColors.AccentOrange,
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = pending.summary,
                style = MaterialTheme.typography.labelMedium,
                color = MiColors.AccentOrange,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.weight(1f))
            TextButton(onClick = onReject) {
                Text("拒绝", color = MiColors.AccentRed)
            }
            TextButton(onClick = onAccept) {
                Text("接受", color = MiColors.AccentGreen)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                .height(160.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val rowBg = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
            val lineStyle = MaterialTheme.typography.labelSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
            val contextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            val addColor = MiColors.AccentGreen
            val removeColor = MiColors.AccentRed

            // 用行号前缀强制 monospace 对齐
            val digits = diff.size.toString().length

            diff.forEachIndexed { index, line ->
                val (prefix, color, bg) = when (line.kind) {
                    DiffLineKind.ADD -> Triple("+ ", addColor, addColor.copy(alpha = 0.08f))
                    DiffLineKind.REMOVE -> Triple("- ", removeColor, removeColor.copy(alpha = 0.08f))
                    DiffLineKind.CONTEXT -> Triple("  ", contextColor, rowBg)
                }
                Text(
                    text = "${index.toString().padStart(digits)} $prefix${line.text}",
                    style = lineStyle,
                    color = color,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bg)
                        .padding(horizontal = 8.dp, vertical = 1.dp)
                )
            }
        }
    }
}
