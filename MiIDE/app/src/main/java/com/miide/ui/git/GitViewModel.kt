package com.miide.ui.git

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.git.GitBranch
import com.miide.core.git.GitCommit
import com.miide.core.git.GitRemote
import com.miide.core.git.GitService
import com.miide.core.git.GitStatusEntry
import com.miide.core.git.StatusKind
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/** Git 界面状态。 */
data class GitUiState(
    val repoPath: String = "",
    val isRepo: Boolean = false,
    val currentBranch: String = "",
    val branches: List<GitBranch> = emptyList(),
    val status: List<GitStatusEntry> = emptyList(),
    val staged: Set<String> = emptySet(),
    val commits: List<GitCommit> = emptyList(),
    val remotes: List<GitRemote> = emptyList(),
    val diffText: String = "",
    val busy: Boolean = false,
    val message: String? = null,
    val error: String? = null
)

@HiltViewModel
class GitViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gitService: GitService
) : ViewModel() {

    private val _uiState = MutableStateFlow(GitUiState())
    val uiState: StateFlow<GitUiState> = _uiState.asStateFlow()

    /** 沙箱内默认仓库根目录（filesDir/git）。 */
    val defaultRoot: File get() = File(context.filesDir, "git")

    /** 打开指定目录作为仓库。 */
    fun openRepo(path: String) {
        val trimmed = path.trim()
        if (trimmed.isEmpty()) return
        _uiState.value = _uiState.value.copy(repoPath = trimmed, error = null, message = null)
        refresh()
    }

    /** 刷新当前仓库全部信息。 */
    fun refresh() {
        val path = _uiState.value.repoPath
        if (path.isBlank()) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null)
            val isRepo = gitService.isRepository(path)
            if (!isRepo) {
                _uiState.value = _uiState.value.copy(
                    isRepo = false,
                    branches = emptyList(),
                    status = emptyList(),
                    commits = emptyList(),
                    remotes = emptyList(),
                    busy = false
                )
                return@launch
            }
            val branches = gitService.branches(path)
            val status = gitService.status(path)
            val commits = gitService.log(path)
            val remotes = gitService.remotes(path)
            val current = branches.data?.firstOrNull { it.isCurrent }?.name ?: ""
            _uiState.value = _uiState.value.copy(
                isRepo = true,
                currentBranch = current,
                branches = branches.data ?: emptyList(),
                status = status.data ?: emptyList(),
                staged = (status.data ?: emptyList())
                    .filter { it.status == StatusKind.ADDED }
                    .map { it.path }.toSet(),
                commits = commits.data ?: emptyList(),
                remotes = remotes.data ?: emptyList(),
                diffText = "",
                busy = false
            )
        }
    }

    /** 查看指定文件（或全部）的 diff。 */
    fun showDiff(paths: List<String>?) {
        val path = _uiState.value.repoPath
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null)
            val result = gitService.diff(path, paths)
            _uiState.value = _uiState.value.copy(
                diffText = result.data ?: "",
                busy = false,
                error = if (result.success) null else result.error
            )
        }
    }

    /** 暂存指定文件（空=全部）。 */
    fun stage(paths: List<String>) {
        val path = _uiState.value.repoPath
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null)
            val result = gitService.add(path, paths)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) refresh()
            else _uiState.value = _uiState.value.copy(error = result.error)
        }
    }

    /** 取消暂存指定文件（空=全部）。 */
    fun unstage(paths: List<String>) {
        val path = _uiState.value.repoPath
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null)
            val result = gitService.reset(path, paths)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) refresh()
            else _uiState.value = _uiState.value.copy(error = result.error)
        }
    }

    /** 提交暂存内容。 */
    fun commit(message: String) {
        val path = _uiState.value.repoPath
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null, message = null)
            val result = gitService.commit(path, message)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) {
                _uiState.value = _uiState.value.copy(message = "已提交")
                refresh()
            } else {
                _uiState.value = _uiState.value.copy(error = result.error)
            }
        }
    }

    /** 推送当前分支。 */
    fun push(branch: String? = null) {
        val path = _uiState.value.repoPath
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null, message = null)
            val result = gitService.push(path, "origin", branch ?: _uiState.value.currentBranch)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) _uiState.value = _uiState.value.copy(message = "推送成功：${result.data}")
            else _uiState.value = _uiState.value.copy(error = result.error)
        }
    }

    /** 拉取当前分支。 */
    fun pull(branch: String? = null) {
        val path = _uiState.value.repoPath
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null, message = null)
            val result = gitService.pull(path, "origin", branch ?: _uiState.value.currentBranch)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) {
                _uiState.value = _uiState.value.copy(message = "拉取成功：${result.data}")
                refresh()
            } else {
                _uiState.value = _uiState.value.copy(error = result.error)
            }
        }
    }

    /** 切换分支（不存在则新建）。 */
    fun checkout(branch: String, create: Boolean = false) {
        val path = _uiState.value.repoPath
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null, message = null)
            val result = gitService.checkout(path, branch, create)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) {
                _uiState.value = _uiState.value.copy(message = "已切换到 $branch")
                refresh()
            } else {
                _uiState.value = _uiState.value.copy(error = result.error)
            }
        }
    }

    /** 初始化新仓库（目录可不存在）。 */
    fun initRepo(dirName: String) {
        val root = defaultRoot
        root.mkdirs()
        val dir = File(root, dirName.trim())
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null, message = null)
            dir.mkdirs()
            val result = gitService.init(dir.absolutePath)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) {
                _uiState.value = _uiState.value.copy(repoPath = dir.absolutePath, message = "已初始化仓库")
                refresh()
            } else {
                _uiState.value = _uiState.value.copy(error = result.error)
            }
        }
    }

    /** 克隆远程仓库。 */
    fun cloneRemote(url: String, dirName: String, branch: String? = null) {
        val root = defaultRoot
        root.mkdirs()
        val dest = File(root, dirName.trim().ifBlank { repoNameFromUrl(url) })
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null, message = null)
            val result = gitService.clone(url, dest.absolutePath, branch)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) {
                _uiState.value = _uiState.value.copy(repoPath = dest.absolutePath, message = "克隆完成")
                refresh()
            } else {
                _uiState.value = _uiState.value.copy(error = result.error)
            }
        }
    }

    /** 添加远程。 */
    fun addRemote(name: String, url: String) {
        val path = _uiState.value.repoPath
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(busy = true, error = null, message = null)
            val result = gitService.addRemote(path, name.ifBlank { "origin" }, url)
            _uiState.value = _uiState.value.copy(busy = false)
            if (result.success) {
                _uiState.value = _uiState.value.copy(message = "已添加远程 $name")
                refresh()
            } else {
                _uiState.value = _uiState.value.copy(error = result.error)
            }
        }
    }

    private fun repoNameFromUrl(url: String): String {
        val trimmed = url.trimEnd('/')
        return trimmed.substringAfterLast('/').ifBlank { "repo" }.removeSuffix(".git")
    }
}