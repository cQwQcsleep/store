package com.skyauto.app.data.preload

import com.skyauto.app.data.repository.SkyRepository
import com.skyauto.app.ui.navigation.AppMenu
import com.skyauto.app.ui.navigation.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * “十字 UI”数据预加载器。
 *
 * 分页主导航只渲染当前页 ±3 页（[androidx.compose.foundation.pager.HorizontalPager] 的
 * `beyondViewportPageCount`），为让左右滑动更顺滑，本类把当前页 ±5 页内各功能页的数据
 * 提前在 [Dispatchers.IO]（多线程）并发加载进 [HubPreloadCache]。用户滑到对应页并点击进入时，
 * ViewModel 可直接从缓存秒出，无需等待网络。
 */
@Singleton
class HubPreloader @Inject constructor(
    private val repo: SkyRepository,
    private val cache: HubPreloadCache
) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val groups = AppMenu.groups()

    /**
     * 以当前页为中心，把 [currentPage-5, currentPage+5] 范围内所有功能目的地的数据并发预加载。
     * 幂等：已预加载过的路由不会重复请求。
     */
    fun preloadNearby(currentPage: Int) {
        val start = (currentPage - PreloadRange).coerceAtLeast(0)
        val end = (currentPage + PreloadRange).coerceAtMost(groups.size - 1)
        for (page in start..end) {
            groups[page].children.forEach { leaf ->
                val route = leaf.route
                if (cache.markLoaded(route)) {
                    scope.launch { loadRoute(route) }
                }
            }
        }
    }

    /** 单次目的地的预加载任务（每条路由一个协程，跑在 IO 线程池，天然多线程并发）。 */
    private suspend fun loadRoute(route: String) {
        // 预加载绝不能因网络异常崩溃：仓库部分方法在非 2xx/网络错误时可能直接抛出，
        // 这里统一用 safe 兜底，任何异常只跳过该路由的预加载。
        runCatching {
            when (route) {
                Routes.DASHBOARD -> {
                    cache.put(route, repo.dashboardStats().getOrNull())
                    cache.put("online", repo.onlineCount().getOrNull())
                }
                Routes.HEIGHT -> cache.put(route, repo.myHeight().getOrNull())
                Routes.RANKING -> cache.put(route, repo.heightRanking().getOrNull())
                Routes.WORLD_QUESTS -> cache.put(route, repo.worldQuests().getOrNull())
                Routes.GAME_INSIGHTS -> cache.put(route, repo.gameInsightsPublic().getOrNull())
                Routes.ECONOMY -> {
                    repo.accounts().getOrNull()?.let { accounts ->
                        cache.put(Routes.ACCOUNTS, accounts)
                        accounts.firstOrNull()?.id?.let { id ->
                            cache.put("currency_$id", repo.accountCurrency(id).getOrNull())
                        }
                    }
                }
                Routes.ACCOUNTS -> cache.put(route, repo.accounts().getOrNull())
                Routes.DEVICES -> cache.put(route, repo.devices().getOrNull())
                Routes.CONFIG_RULES -> cache.put(route, repo.accountConfigRules().getOrNull())
                Routes.FRIENDS -> cache.put(route, repo.friends().getOrNull())
                Routes.SPIRITS -> cache.put(route, repo.heartTrade().getOrNull())
                Routes.CHAT -> cache.put(route, repo.chatRooms().getOrNull())
                Routes.TASKS -> cache.put(route, repo.schedules().getOrNull())
                Routes.ANNOUNCEMENTS -> cache.put(route, repo.systemAnnouncements().getOrNull())
                Routes.INVITATIONS -> cache.put(route, repo.myInvitations().getOrNull())
                Routes.OPERATIONS -> cache.put(route, repo.operationsStats().getOrNull())
                Routes.NOTIFICATIONS -> cache.put(route, repo.notifications().getOrNull())
            }
        }
    }

    companion object {
        /** 预加载范围：当前页 ±5 页。 */
        const val PreloadRange = 5
    }
}