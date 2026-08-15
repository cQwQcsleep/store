package com.skyauto.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skyauto.app.ui.components.HyperTopBar
import com.skyauto.app.ui.navigation.LocalDrawerOpener

/**
 * 统一页面外壳：带全局抽屉菜单按钮的顶栏 + 可滚动内容区。
 */
@Composable
fun AppScreen(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
    content: @Composable () -> Unit
) {
    val openDrawer = LocalDrawerOpener.current
    Column(modifier.fillMaxSize()) {
        HyperTopBar(
            title = title,
            onMenuClick = { openDrawer() }
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