package com.skyauto.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skyauto.app.data.model.Account

/**
 * 账号选择器：水平排列的账号胶囊，点击弹出下拉选择。
 * 用于按账号查询的页面（游戏情报/合成/任务历史/好友深度等）。
 */
@Composable
fun AccountPicker(
    accounts: List<Account>,
    selectedId: Long?,
    onSelect: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    if (accounts.isEmpty()) return
    val selected = accounts.firstOrNull { it.id == selectedId } ?: accounts.first()
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row(
            Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .clickable { expanded = true }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                selected.displayUsername ?: selected.account ?: "账号 #${selected.id}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Icon(Icons.Rounded.KeyboardArrowDown, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            accounts.forEach { account ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(
                                account.displayUsername ?: account.account ?: "账号 #${account.id}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                "平台：${account.platform ?: "-"}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    onClick = {
                        expanded = false
                        account.id?.let { onSelect(it) }
                    }
                )
            }
        }
    }
}
