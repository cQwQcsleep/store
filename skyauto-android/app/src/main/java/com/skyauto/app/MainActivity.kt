package com.skyauto.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyauto.app.data.network.MaintenanceMonitor
import com.skyauto.app.ui.screens.AuthScreen
import com.skyauto.app.ui.screens.MaintenanceScreen
import com.skyauto.app.ui.navigation.AppNavHost
import com.skyauto.app.ui.theme.SkyAutoTheme
import com.skyauto.app.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkyAutoTheme {
                val authViewModel: AuthViewModel = hiltViewModel()
                val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
                Box(Modifier.fillMaxSize()) {
                    if (isLoggedIn) {
                        AppNavHost(onRequestAuth = {})
                    } else {
                        AuthScreen(
                            viewModel = authViewModel,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    // 服务器维护中 —— 全屏覆盖，置于一切之上
                    MaintenanceScreen()
                }
            }
        }
    }
}