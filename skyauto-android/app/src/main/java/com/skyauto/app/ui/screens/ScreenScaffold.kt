package com.skyauto.app.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skyauto.app.ui.components.HyperTopBar

/** 供详情页设置返回动作（返回按钮 + 系统返回手势） */
val LocalBackAction = staticCompositionLocalOf<(() -> Unit)?> { null }

/**
 * 统一页面外壳：返回/菜单按钮顶栏 + 可滚动内容区。
 * 详情页通过 [LocalBackAction] 提供返回回调。
 */
@Composable
fun AppScreen(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
    content: @Composable () -> Unit
) {
    val onBack = LocalBackAction.current
    BackHandler(enabled = onBack != null) { onBack?.invoke() }
    Column(modifier.fillMaxSize()) {
        HyperTopBar(
            title = title,
            onMenuClick = { onBack?.invoke() },
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            content()
        }
    }
}

/** 包裹详情页，为其提供返回动作。 */
@Composable
fun BackActionScope(
    onBack: (() -> Unit)?,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalBackAction provides onBack) {
        content()
    }
}