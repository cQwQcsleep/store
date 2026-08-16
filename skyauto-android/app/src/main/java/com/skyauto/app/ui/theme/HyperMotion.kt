package com.skyauto.app.ui.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

/**
 * HyperOS 3.0 动画规范。
 * 所有 UI 状态变化统一走此处时长/缓动，保证全 App 动画节奏一致。
 * 原则：并行动画、可打断、动画优先。
 */
object HyperMotion {
    /** 快速（点击反馈、微小位移） */
    val fast: FiniteAnimationSpec<Float> = tween(280, easing = FastOutSlowInEasing)

    /** 标准（页面切换、卡片展开） */
    val standard: FiniteAnimationSpec<Float> = tween(400, easing = FastOutSlowInEasing)

    /** 慢速（大范围过渡、全屏覆盖） */
    val slow: FiniteAnimationSpec<Float> = tween(520, easing = FastOutSlowInEasing)

    /** 弹性（尺寸/缩放，可打断） */
    val springy: FiniteAnimationSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMedium
    )

    /** 分页切换动画（可被新目标打断） */
    val pager: FiniteAnimationSpec<Float> = tween(420, easing = FastOutSlowInEasing)
}