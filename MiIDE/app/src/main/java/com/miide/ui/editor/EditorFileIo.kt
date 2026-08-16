package com.miide.ui.editor

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 通过 SAF（Storage Access Framework）读写文件内容。
 * 读写均切到 IO 线程，避免阻塞主线程。
 */
object EditorFileIo {

    /** 读取 URI 对应文件文本；失败返回 null。 */
    suspend fun read(context: Context, uri: Uri): String? = withContext(Dispatchers.IO) {
        runCatching {
            context.contentResolver.openInputStream(uri)?.use { input ->
                input.readBytes().toString(Charsets.UTF_8)
            }
        }.getOrNull()
    }

    /** 写回文本；返回是否成功。 */
    suspend fun write(context: Context, uri: Uri, content: String): Boolean =
        withContext(Dispatchers.IO) {
            runCatching {
                context.contentResolver.openOutputStream(uri, "wt")?.use { output ->
                    output.write(content.toByteArray(Charsets.UTF_8))
                }
                true
            }.getOrDefault(false)
        }
}
