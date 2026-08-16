package com.miide.ui.common

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.miide.ui.chat.ChatViewModel

/** 从当前 Compose 上下文向上查找 ComponentActivity；找不到返回 null。 */
@Composable
fun findLocalActivity(): ComponentActivity? {
    var context = LocalContext.current
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    return null
}

/**
 * 获取 Activity 级共享的 [ChatViewModel]。
 * 底部面板与全屏页共用同一实例，保证流式状态连续。
 */
@Composable
fun chatViewModelShared(): ChatViewModel {
    val activity = findLocalActivity()
    return if (activity != null) {
        hiltViewModel(viewModelStoreOwner = activity)
    } else {
        hiltViewModel()
    }
}
