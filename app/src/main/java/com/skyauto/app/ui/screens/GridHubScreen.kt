package com.skyauto.app.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.ui.navigation.AppMenu
import com.skyauto.app.ui.navigation.MenuLeaf
import com.skyauto.app.ui.navigation.Routes
import com.skyauto.app.ui.theme.FrostWhite
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.viewmodel.HubViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.math.sign

/**
 * 全天域 2D 无限吸附网格桌面。
 *
 * - 所有功能卡片平铺为正方形网格，任意方向滑动，松手丝滑吸附到最近格；
 * - 高速度甩动按速度大小连续多格过冲（极高速度可一口滚过数十格）并精准吸附，
 *   吸附动画可被下一次手势打断，打断后从当前位置继续；
 * - 点击任意卡片自动吸附并居中，同时以 90% 覆盖层缩放展开对应功能，返回键退出，
 *   切换其他卡片时自动关闭；
 * - 仅渲染 3×3 可视窗口，预加载 5×5 数据（资源缓存 + 预测），性能友好；
 * - 右上角格子小地图 + 屏幕四边轻微淡化内容。
 */
@Composable
fun GridHubScreen(
    onRequestAuth: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val viewModel: HubViewModel = hiltViewModel()
    val leaves = remember { AppMenu.leaves() }

    var expandedRoute by remember { mutableStateOf<String?>(null) }

    BoxWithConstraints(Modifier.fillMaxSize()) {
        val viewW = constraints.maxWidth.toFloat()
        val viewH = constraints.maxHeight.toFloat()

        // 自适应网格：正方形格子，3×3 尽量贴合屏幕。
        val cellSize = minOf(viewW, viewH) / 3f
        // 整数格边长：offset 与 size 统一用整数，避免四舍五入/截断不一致导致卡片重叠
        val cell = cellSize.roundToInt()
        val colCount = 3
        val rowCount = ceil(leaves.size.toFloat() / colCount).toInt().coerceAtLeast(1)
        val gridW = colCount * cellSize
        val gridH = rowCount * cellSize

        // 当前可视窗口中心格（行, 列）
        val center = remember { mutableStateOf(0 to 0) }
        // 网格层平移量（px）
        val offset = remember {
            Animatable(Offset(0f, 0f), Offset.VectorConverter)
        }

        fun clampCenter(c: Int, r: Int): Pair<Int, Int> = Pair(
            c.coerceIn(0, colCount - 1),
            r.coerceIn(0, rowCount - 1)
        )

        fun targetOffsetFor(c: Int, r: Int): Offset {
            val cc = c.coerceIn(0, colCount - 1)
            val rr = r.coerceIn(0, rowCount - 1)
            val x = viewW / 2f - (cc * cellSize + cellSize / 2f)
            val y = viewH / 2f - (rr * cellSize + cellSize / 2f)
            return Offset(
                if (gridW <= viewW) 0f else x.coerceIn(viewW - gridW, 0f),
                if (gridH <= viewH) 0f else y.coerceIn(viewH - gridH, 0f)
            )
        }

        fun cellCenter(off: Offset): Pair<Int, Int> {
            val cx = viewW / 2f - off.x
            val cy = viewH / 2f - off.y
            return clampCenter((cx / cellSize).roundToInt(), (cy / cellSize).roundToInt())
        }

        fun snapToCell(c: Int, r: Int) {
            val (cc, rr) = clampCenter(c, r)
            center.value = cc to rr
            scope.launch {
                offset.animateTo(targetOffsetFor(cc, rr), spring(dampingRatio = 0.8f, stiffness = 350f))
            }
        }

        // 初始居中首格
        LaunchedEffect(Unit) {
            offset.snapTo(targetOffsetFor(0, 0))
            center.value = 0 to 0
            viewModel.onFirstShown()
        }

        // 预加载 5×5：中心格 ±2 范围内的功能路由（去重后并发预加载）
        LaunchedEffect(center.value) {
            val routes = buildSet {
                for (dr in -2..2) {
                    for (dc in -2..2) {
                        val c = center.value.first + dc
                        val r = center.value.second + dr
                        if (c < 0 || c >= colCount || r < 0 || r >= rowCount) continue
                        leaves.getOrNull(r * colCount + c)?.let { add(it.route) }
                    }
                }
            }.toList()
            viewModel.preloadLeaves(routes)
        }

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(FrostWhite, Color(0xFFEDF1F8))
                    )
                )
        ) {
            Box(
                Modifier
                    .size(gridW.toInt().dp, gridH.toInt().dp)
                    .graphicsLayer {
                        translationX = offset.value.x
                        translationY = offset.value.y
                    }
                    .pointerInput(Unit) {
                        val velocityTracker = VelocityTracker()
                        detectDragGestures(
                            onDragStart = {
                                velocityTracker.resetTracking()
                                // 打断进行中的吸附动画，从当前位置继续拖拽
                                scope.launch { offset.stop() }
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                velocityTracker.addPosition(change.uptimeMillis, change.position)
                                val nx = offset.value.x + dragAmount.x
                                val ny = offset.value.y + dragAmount.y
                                scope.launch {
                                    offset.snapTo(
                                        Offset(
                                            if (gridW <= viewW) 0f else nx.coerceIn(viewW - gridW, 0f),
                                            if (gridH <= viewH) 0f else ny.coerceIn(viewH - gridH, 0f)
                                        )
                                    )
                                }
                                center.value = cellCenter(offset.value)
                            },
                            onDragEnd = {
                                val v = velocityTracker.calculateVelocity() // px/s
                                val (cc, rr) = center.value
                                var tc = cc
                                var tr = rr
                                // 按速度大小连续过冲：vx/vy 为末速度，速度越快过冲格数越多
                                if (abs(v.x) > MIN_FLING_SPEED) {
                                    val overshoot = (abs(v.x) / (cellSize * SPEED_PER_CELL)).roundToInt()
                                    tc += sign(v.x).toInt() * overshoot
                                }
                                if (abs(v.y) > MIN_FLING_SPEED) {
                                    val overshoot = (abs(v.y) / (cellSize * SPEED_PER_CELL)).roundToInt()
                                    tr += sign(v.y).toInt() * overshoot
                                }
                                snapToCell(tc, tr)
                            },
                            onDragCancel = {
                                snapToCell(center.value.first, center.value.second)
                            }
                        )
                    }
            ) {
                val (cc, rr) = center.value
                for (dr in -1..1) {
                    for (dc in -1..1) {
                        val c = cc + dc
                        val r = rr + dr
                        if (c < 0 || c >= colCount || r < 0 || r >= rowCount) continue
                        val idx = r * colCount + c
                        val leaf = leaves.getOrNull(idx) ?: continue
                        GridCard(
                            leaf = leaf,
                            paletteIndex = idx,
                            modifier = Modifier
                                .offset { IntOffset(c * cell, r * cell) }
                                .size(cell.dp),
                            onClick = {
                                scope.launch { offset.stop() }
                                snapToCell(c, r)
                                expandedRoute = leaf.route
                            }
                        )
                    }
                }
            }

            // ---- 屏幕四边轻微淡化内容 ----
            EdgeFade(
                viewW = viewW,
                viewH = viewH,
                fade = 72f
            )

            // ---- 右上角格子小地图 ----
            MiniMap(
                colCount = colCount,
                rowCount = rowCount,
                cellCount = leaves.size,
                center = center.value,
                modifier = Modifier.align(Alignment.TopEnd).padding(12.dp),
                onCellClick = { c, r -> snapToCell(c, r) }
            )
        }

        // ---- 功能展开层（90% 覆盖，返回键退出） ----
        BackHandler(enabled = expandedRoute != null) { expandedRoute = null }

        AnimatedVisibility(
            visible = expandedRoute != null,
            enter = scaleIn(initialScale = 0.6f, animationSpec = tween(340, easing = FastOutSlowInEasing)) + fadeIn(tween(340)),
            exit = scaleOut(targetScale = 0.6f, animationSpec = tween(280)) + fadeOut(tween(280)),
            modifier = Modifier.fillMaxSize()
        ) {
            val route = expandedRoute ?: return@AnimatedVisibility
            ExpandedFeature(
                route = route,
                onClose = { expandedRoute = null },
                onRequestAuth = onRequestAuth
            )
        }
    }
}

/** 卡片配色：浅背景 + 强调色 + 图标色（按索引循环，形成多彩格子）。 */
private data class CardStyle(val bg: Color, val accent: Color, val icon: Color)

private val CardStyles = listOf(
    CardStyle(Color(0xFFE3F0FF), Color(0xFF6C8CFF), Color(0xFF4A63D6)), // 蓝
    CardStyle(Color(0xFFE6F7EC), Color(0xFF7CCB9A), Color(0xFF3E9668)), // 绿
    CardStyle(Color(0xFFFFF3E0), Color(0xFFFFC46B), Color(0xFFE09A2E)), // 橙
    CardStyle(Color(0xFFF3E8FF), Color(0xFF9A8CFF), Color(0xFF6C5CE0)), // 紫
    CardStyle(Color(0xFFFFEBEE), Color(0xFFFF7B7B), Color(0xFFE04F5F)), // 红
    CardStyle(Color(0xFFE0F7FA), Color(0xFF7AD4E8), Color(0xFF2FA8C0)), // 青
    CardStyle(Color(0xFFF1F8E9), Color(0xFF9CCC65), Color(0xFF6FA83B)), // 黄绿
    CardStyle(Color(0xFFFFF9C4), Color(0xFFFFD54F), Color(0xFFE0A800)), // 黄
    CardStyle(Color(0xFFEDE7F6), Color(0xFFB39DDB), Color(0xFF7E57C2)), // 深紫
)

/** 纯色扁平功能卡片。 */
@Composable
private fun GridCard(
    leaf: MenuLeaf,
    paletteIndex: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val style = CardStyles[paletteIndex % CardStyles.size]
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 6.dp,
        tonalElevation = 2.dp,
        modifier = modifier.padding(6.dp)
    ) {
        Column(
            Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .size(52.dp)
                    .background(style.accent.copy(alpha = 0.18f), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(leaf.icon, contentDescription = null, tint = style.icon, modifier = Modifier.size(26.dp))
            }
            Spacer(Modifier.height(8.dp))
            Text(
                leaf.label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = style.icon,
                maxLines = 1
            )
        }
    }
}

/** 屏幕四周渐变淡化内容（用背景色，通透不脏）。 */
@Composable
private fun EdgeFade(viewW: Float, viewH: Float, fade: Float) {
    val fadeColor = FrostWhite.copy(alpha = 0.9f)
    Box(Modifier.fillMaxSize()) {
        // 左侧淡化
        Box(
            Modifier
                .fillMaxHeight()
                .width(fade.dp)
                .align(Alignment.CenterStart)
                .background(
                    Brush.horizontalGradient(
                        0f to fadeColor,
                        1f to Color.Transparent
                    )
                )
        )
        // 右侧淡化
        Box(
            Modifier
                .fillMaxHeight()
                .width(fade.dp)
                .align(Alignment.CenterEnd)
                .background(
                    Brush.horizontalGradient(
                        0f to Color.Transparent,
                        1f to fadeColor
                    )
                )
        )
        // 顶部淡化
        Box(
            Modifier
                .fillMaxWidth()
                .height(fade.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        0f to fadeColor,
                        1f to Color.Transparent
                    )
                )
        )
        // 底部淡化
        Box(
            Modifier
                .fillMaxWidth()
                .height(fade.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        1f to fadeColor
                    )
                )
        )
    }
}

/** 右上角格子小地图。 */
@Composable
private fun MiniMap(
    colCount: Int,
    rowCount: Int,
    cellCount: Int,
    center: Pair<Int, Int>,
    modifier: Modifier = Modifier,
    onCellClick: (Int, Int) -> Unit
) {
    val cellPx = 16f
    val mapW = colCount * cellPx
    val mapH = rowCount * cellPx
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = Color.White.copy(alpha = 0.72f),
        tonalElevation = 3.dp,
        modifier = modifier.size(mapW.toInt().dp, mapH.toInt().dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            for (r in 0 until rowCount) {
                for (c in 0 until colCount) {
                    val isCenter = c == center.first && r == center.second
                    val idx = r * colCount + c
                    val has = idx < cellCount
                    Box(
                        Modifier
                            .offset { IntOffset((c * cellPx).roundToInt(), (r * cellPx).roundToInt()) }
                            .size(cellPx.toInt().dp)
                            .padding(1.dp)
                            .background(
                                when {
                                    isCenter -> HyperBlue
                                    has -> MaterialTheme.colorScheme.surfaceContainerHighest
                                    else -> Color.Transparent
                                },
                                RoundedCornerShape(3.dp)
                            )
                    )
                }
            }
        }
    }
}

/** 展开层：以 90% 覆盖层缩放展开对应功能。 */
@Composable
private fun ExpandedFeature(
    route: String,
    onClose: () -> Unit,
    onRequestAuth: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.35f)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(32.dp),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.9f)
        ) {
            BackActionScope(onBack = onClose) {
                when (route) {
                    Routes.DASHBOARD -> DashboardScreen()
                    Routes.HEIGHT -> HeightScreen()
                    Routes.RANKING -> RankingScreen()
                    Routes.WORLD_QUESTS -> WorldQuestsScreen()
                    Routes.GAME_INSIGHTS -> GameInsightsScreen()
                    Routes.ECONOMY -> EconomyScreen()
                    Routes.FORGE -> ForgeScreen()
                    Routes.ACCOUNTS -> AccountsScreen()
                    Routes.DEVICES -> DevicesScreen()
                    Routes.CONFIG_RULES -> ConfigRulesScreen()
                    Routes.FRIENDS -> FriendsScreen()
                    Routes.FRIEND_DETAIL -> FriendDetailScreen()
                    Routes.FRIEND_CODES -> FriendCodeScreen()
                    Routes.SPIRITS -> SpiritsScreen()
                    Routes.TASKS -> TasksScreen()
                    Routes.TASK_HISTORY -> TaskHistoryScreen()
                    Routes.DONE_TODAY -> DoneTodayScreen()
                    Routes.AI -> AiScreen()
                    Routes.CHAT -> ChatScreen()
                    Routes.NOTIFICATIONS -> NotificationsScreen()
                    Routes.ANNOUNCEMENTS -> AnnouncementsScreen()
                    Routes.INVITATIONS -> InvitationsScreen()
                    Routes.OPERATIONS -> OperationsScreen()
                    Routes.ORDERS -> OrdersScreen()
                    Routes.FEEDBACK -> FeedbackScreen()
                    Routes.WECHAT -> WechatBindingScreen()
                    Routes.SETTINGS -> SettingsScreen(onLogout = { onRequestAuth() })
                    else -> Text("功能开发中", modifier = Modifier.padding(24.dp))
                }
            }
        }

        // 显式关闭按钮（右上角），除返回键外提供清晰退出入口
        Surface(
            onClick = onClose,
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.85f),
            shadowElevation = 4.dp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
                .size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = "关闭",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

private const val MIN_FLING_SPEED = 400f      // px/s，低于此速度不触发过冲
private const val SPEED_PER_CELL = 2600f      // 速度/格 换算系数：越大越难多格