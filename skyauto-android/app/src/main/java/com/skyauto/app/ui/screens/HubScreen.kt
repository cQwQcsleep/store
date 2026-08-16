package com.skyauto.app.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skyauto.app.ui.navigation.AppMenu
import com.skyauto.app.ui.navigation.MenuGroup
import com.skyauto.app.ui.navigation.MenuLeaf
import com.skyauto.app.ui.theme.HyperBlue
import com.skyauto.app.ui.theme.HyperLavender
import com.skyauto.app.ui.viewmodel.HubViewModel
import kotlinx.coroutines.launch

/**
 * 十字 UI 主页 —— 横向分页主导航。
 * 每个 [MenuGroup] 为一页，左右滑动切换；页内功能卡片向下平铺。
 * 仅渲染当前页 ± 3 页；当前页 ± 5 页异步预加载（由 ViewModel 处理数据）。
 */
@Composable
fun HubScreen(
    onNavigate: (String) -> Unit,
    viewModel: HubViewModel = hiltViewModel()
) {
    val groups = AppMenu.groups()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { groups.size }
    )
    val scope = rememberCoroutineScope()

    // 异步预加载：初始进入预加载首页附近 (±5 页)；当前页变化时预加载新窗口内数据，
    // 均在后台 IO 线程池并发执行，不阻塞主线程。
    LaunchedEffect(pagerState.currentPage) {
        viewModel.onPageSelected(pagerState.currentPage)
    }
    LaunchedEffect(Unit) {
        viewModel.onFirstShown()
    }

    Column(Modifier.fillMaxSize()) {
        // 顶部：品牌区 + 类目指示器
        HubHeader(
            groups = groups,
            currentPage = pagerState.currentPage,
            onPageSelected = { target ->
                scope.launch { pagerState.animateScrollToPage(target) }
            },
            onNavigate = onNavigate
        )

        // 横向分页主体：按需渲染 ±3 页
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 3,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.weight(1f)
        ) { page ->
            val group = groups[page]
            // 并行动画：位移 + 缩放 + 透明度 随翻页偏移同步变化（可打断）
            val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
            val scale = 0.92f + 0.08f * (1f - kotlin.math.abs(pageOffset).coerceIn(0f, 1f))
            val alpha = 1f - 0.35f * kotlin.math.abs(pageOffset).coerceIn(0f, 1f)
            GroupPage(
                group = group,
                onNavigate = onNavigate,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val pag = pageOffset.coerceIn(-1f, 1f)
                        translationX = pag * 48f
                        this.scaleX = scale
                        this.scaleY = scale
                        this.alpha = alpha
                    }
            )
        }
    }
}

/** 顶部品牌区与类目指示器。 */
@Composable
private fun HubHeader(
    groups: List<MenuGroup>,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    onNavigate: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 品牌图标
            Box(
                Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Brush.linearGradient(listOf(HyperBlue, HyperLavender))),
                contentAlignment = Alignment.Center
            ) {
                Text("光", color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("光遇助手", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Text("SkyAuto · HyperOS", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Spacer(Modifier.height(16.dp))

        // 类目指示器（当前页高亮）
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            groups.forEachIndexed { index, group ->
                val selected = index == currentPage
                val alpha by animateFloatAsState(
                    targetValue = if (selected) 1f else 0.45f,
                    animationSpec = tween(280),
                    label = "tabAlpha"
                )
                Surface(
                    onClick = { onPageSelected(index) },
                    shape = RoundedCornerShape(20.dp),
                    color = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)
                    else MaterialTheme.colorScheme.surfaceContainerHigh,
                    modifier = Modifier.alpha(alpha)
                ) {
                    Row(
                        Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            group.icon,
                            contentDescription = null,
                            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            group.label,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

/** 单个类目页：向下平铺功能卡片。 */
@Composable
private fun GroupPage(
    group: MenuGroup,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // 类目渐变头
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(36.dp))
                    .background(Brush.linearGradient(listOf(HyperBlue, HyperLavender)))
                    .padding(20.dp)
            ) {
                Column {
                    Text(group.label, style = MaterialTheme.typography.headlineMedium, color = Color.White, fontWeight = FontWeight.Bold)
                    Text("左右滑动切换类目 · 向下查看更多", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.9f))
                }
            }
        }
        items(group.children, key = { it.route }) { leaf ->
            FeatureCard(
                leaf = leaf,
                onClick = { onNavigate(leaf.route) }
            )
        }
    }
}

/** 功能入口卡片。 */
@Composable
private fun FeatureCard(
    leaf: MenuLeaf,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(30.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.82f),
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(HyperBlue.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(leaf.icon, contentDescription = null, tint = HyperBlue, modifier = Modifier.size(24.dp))
            }
            Spacer(Modifier.width(14.dp))
            Text(
                leaf.label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer { rotationY = 180f }
            )
        }
    }
}