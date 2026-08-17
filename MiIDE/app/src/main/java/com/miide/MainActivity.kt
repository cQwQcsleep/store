package com.miide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.miide.core.designsystem.theme.MiIdeTheme
import com.miide.tools.mcp.McpInitializer
import com.miide.ui.navigation.MiNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /** 触发 MCP 初始化（@Singleton 惰性创建即开始同步服务器）。 */
    @Inject
    lateinit var mcpInitializer: McpInitializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiIdeTheme {
                MiNavHost()
            }
        }
    }
}
