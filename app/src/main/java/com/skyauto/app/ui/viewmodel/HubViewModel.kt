package com.skyauto.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.skyauto.app.data.preload.HubPreloader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 十字 UI 主页的 ViewModel：负责驱动 ±5 页数据的后台预加载。
 */
@HiltViewModel
class HubViewModel @Inject constructor(
    private val preloader: HubPreloader
) : ViewModel() {

    /** 当分页主界面当前页变化时调用，预加载当前页 ±5 页的数据。 */
    fun onPageSelected(currentPage: Int) {
        preloader.preloadNearby(currentPage)
    }

    /** 初始进入时预加载首页附近数据。 */
    fun onFirstShown() {
        preloader.preloadNearby(0)
    }
}