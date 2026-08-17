package com.miide.ui.remote

/**
 * 远程文件 → 编辑器桥接。
 *
 * 浏览页读取远程文件内容后置入 [pending]；编辑器打开时消费并记为 [active]；
 * 编辑器保存时若存在 [active]，则回传到远程（而非 SAF 保存）。
 */
object RemoteOpenBridge {

    data class Pending(
        val name: String,
        val content: String,
        val profileId: String,
        val remotePath: String
    )

    @Volatile
    var pending: Pending? = null

    /** 当前编辑器打开的远程文件（用于保存回传）。 */
    @Volatile
    var active: Pending? = null

    /** 编辑器离开时调用，解除远程绑定。 */
    fun clear() {
        pending = null
        active = null
    }
}
