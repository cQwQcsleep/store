package com.miide.terminal

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.termux.terminal.TerminalSession
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** 终端 UI 状态。 */
data class TerminalUiState(
    val running: Boolean = false,
    val exitCode: Int? = null
)

/**
 * 内置虚拟终端：在应用沙箱内以 `/system/bin/sh` 启动子进程，
 * 配合 Termux TerminalView 完成渲染与输入。
 *
 * 会话生命周期随 ViewModel 存活；离开界面（onCleared）时结束子进程。
 */
@HiltViewModel
class TerminalViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(TerminalUiState())
    val state: StateFlow<TerminalUiState> = _state.asStateFlow()

    /** 同时充当 SessionClient 与 ViewClient，Session 与 View 共用同一实例。 */
    val client = TerminalClient(onFinished = { session ->
        _state.value = _state.value.copy(running = false, exitCode = 0)
    })

    private var session: TerminalSession? = null

    /** 获取（必要时创建）终端会话。视图就绪后调用 [attach] 完成绑定。 */
    fun session(): TerminalSession {
        session?.let { return it }
        val home = context.filesDir.absolutePath
        val env = arrayOf(
            "PATH=/system/bin:/system/xbin:/vendor/bin",
            "HOME=$home",
            "SHELL=/system/bin/sh",
            "TERM=xterm-256color",
            "PWD=$home"
        )
        val s = TerminalSession(
            "/system/bin/sh",
            home,
            null,
            env,
            null,
            client
        )
        session = s
        _state.value = TerminalUiState(running = true)
        return s
    }

    /** 向终端写入一行文本（追加回车），供外部（如 AI 工具）发送命令。 */
    fun sendCommand(command: String) {
        val s = session ?: return
        if (command.isBlank()) return
        val bytes = command.toByteArray(Charsets.UTF_8)
        s.write(bytes, 0, bytes.size)
        s.write(byteArrayOf('\r'.code.toByte()), 0, 1)
    }

    override fun onCleared() {
        super.onCleared()
        session?.finishIfRunning()
        session = null
    }
}
