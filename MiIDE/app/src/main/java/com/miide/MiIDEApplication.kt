package com.miide

import android.app.Application
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MiIDEApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化 Chaquopy（内置 Python 运行时），失败不影响其余功能
        if (!Python.isStarted()) {
            runCatching { Python.start(AndroidPlatform(this)) }
        }
    }
}
