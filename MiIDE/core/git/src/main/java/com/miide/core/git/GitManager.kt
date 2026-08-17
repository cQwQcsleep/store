package com.miide.core.git

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.GitAPIException
import org.eclipse.jgit.diff.DiffEntry
import org.eclipse.jgit.diff.DiffFormatter
import org.eclipse.jgit.diff.RawTextComparator
import org.eclipse.jgit.lib.Constants
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.transport.RefSpec
import org.eclipse.jgit.transport.RemoteConfig
import org.eclipse.jgit.transport.URIish
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider
import org.eclipse.jgit.treewalk.AbstractTreeIterator
import org.eclipse.jgit.treewalk.EmptyTreeIterator
import org.eclipse.jgit.treewalk.FileTreeIterator
import org.eclipse.jgit.treewalk.TreeWalk
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

/**
 * 基于 JGit 的 [GitService] 实现。
 * 纯 Java 实现，可在 Android 沙箱内运行，无需系统 git 二进制。
 */
class GitManager(
    /** 认证信息：username 为用户名或 Access Token，password 为 Token/密码（可空）。 */
    private val auth: Credentials = Credentials()
) : GitService {

    data class Credentials(
        val username: String = "",
        val password: String = ""
    )

    override suspend fun isRepository(repoDir: String): Boolean = withContext(Dispatchers.IO) {
        runCatching { open(repoDir); true }.getOrDefault(false)
    }

    override suspend fun clone(url: String, dest: String, branch: String?): GitResult<Unit> =
        withContext(Dispatchers.IO) {
            runCatching {
                val dir = File(dest)
                dir.parentFile?.mkdirs()
                val cmd = Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(dir)
                    .setCloneAllBranches(true)
                if (!branch.isNullOrBlank()) cmd.setBranch(branch)
                credentialProvider()?.let { cmd.setCredentialsProvider(it) }
                cmd.call().close()
                GitResult<Unit>(success = true)
            }.getOrElse { GitResult(false, error = it.message ?: "克隆失败") }
        }

    override suspend fun init(repoDir: String): GitResult<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            Git.init().setDirectory(File(repoDir)).call().close()
            GitResult<Unit>(success = true)
        }.getOrElse { GitResult(false, error = it.message ?: "初始化失败") }
    }

    override suspend fun branches(repoDir: String): GitResult<List<GitBranch>> = withContext(Dispatchers.IO) {
        withRepo(repoDir) { git, repo ->
            val current = repo.fullBranch
            val list = git.branchList().call().map { ref ->
                GitBranch(name = ref.name.removePrefix("refs/heads/"), isCurrent = ref.name == current)
            }
            GitResult(success = true, data = list)
        }
    }

    override suspend fun checkout(repoDir: String, branch: String, create: Boolean): GitResult<Unit> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, _ ->
                val existing = git.branchList().call().any { it.name.removePrefix("refs/heads/") == branch }
                if (create && !existing) {
                    git.checkout().setCreateBranch(true).setName(branch).call()
                } else {
                    git.checkout().setName(branch).call()
                }
                GitResult<Unit>(success = true)
            }
        }

    override suspend fun status(repoDir: String, paths: List<String>?): GitResult<List<GitStatusEntry>> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, _ ->
                val status = git.status().call()
                val entries = mutableListOf<GitStatusEntry>()
                status.added.mapTo(entries) { GitStatusEntry(it, StatusKind.ADDED) }
                status.changed.mapTo(entries) { GitStatusEntry(it, StatusKind.MODIFIED) }
                status.modified.mapTo(entries) { GitStatusEntry(it, StatusKind.MODIFIED) }
                status.removed.mapTo(entries) { GitStatusEntry(it, StatusKind.DELETED) }
                status.missing.mapTo(entries) { GitStatusEntry(it, StatusKind.DELETED) }
                status.untracked.mapTo(entries) { GitStatusEntry(it, StatusKind.UNTRACKED) }
                status.conflicting.mapTo(entries) { GitStatusEntry(it, StatusKind.CONFLICTING) }
                GitResult(success = true, data = entries.distinct())
            }
        }

    override suspend fun add(repoDir: String, paths: List<String>): GitResult<Unit> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, _ ->
                if (paths.isEmpty() || paths.contains(".")) {
                    git.add().addFilepattern(".").call()
                } else {
                    paths.forEach { git.add().addFilepattern(it).call() }
                }
                GitResult<Unit>(success = true)
            }
        }

    override suspend fun reset(repoDir: String, paths: List<String>): GitResult<Unit> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, _ ->
                if (paths.isEmpty() || paths.contains(".")) {
                    git.reset().call()
                } else {
                    git.reset().addPath(paths.first()).call()
                }
                GitResult<Unit>(success = true)
            }
        }

    override suspend fun commit(repoDir: String, message: String, amend: Boolean): GitResult<Unit> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, _ ->
                if (message.isBlank()) return@withRepo GitResult(false, error = "提交信息不能为空")
                git.commit().setMessage(message).setAmend(amend).call()
                GitResult<Unit>(success = true)
            }
        }

    override suspend fun pull(repoDir: String, remote: String, branch: String?): GitResult<String> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, repo ->
                val ref = branch ?: repo.branch
                val result = git.pull()
                    .setRemote(remote)
                    .setRemoteBranchName(ref)
                    .apply { credentialProvider()?.let { setCredentialsProvider(it) } }
                    .call()
                GitResult(success = true, data = result.mergeResult?.mergeStatus?.name ?: "已是最新")
            }
        }

    override suspend fun push(repoDir: String, remote: String, branch: String?): GitResult<String> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, repo ->
                val ref = branch ?: repo.branch
                val cmd = git.push()
                    .setRemote(remote)
                    .setPushTags()
                if (!ref.isNullOrBlank()) cmd.setRefSpecs(RefSpec("$ref:$ref"))
                credentialProvider()?.let { cmd.setCredentialsProvider(it) }
                val result = cmd.call()
                val messages = result.flatMap { it.remoteUpdates.map { u -> u.status.name } }
                GitResult(success = true, data = messages.joinToString(", "))
            }
        }

    override suspend fun log(repoDir: String, maxCount: Int): GitResult<List<GitCommit>> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, _ ->
                val commits = git.log().setMaxCount(maxCount).call().map { rev ->
                    GitCommit(
                        id = rev.name,
                        shortId = rev.name.take(7),
                        message = rev.shortMessage,
                        author = rev.authorIdent.name,
                        time = rev.commitTime * 1000L
                    )
                }
                GitResult(success = true, data = commits)
            }
        }

    override suspend fun remotes(repoDir: String): GitResult<List<GitRemote>> = withContext(Dispatchers.IO) {
        withRepo(repoDir) { git, _ ->
            val list = git.remoteList().call().map { remote: RemoteConfig ->
                GitRemote(remote.name, remote.getURIs().firstOrNull()?.toString() ?: "")
            }
            GitResult(success = true, data = list)
        }
    }

    override suspend fun addRemote(repoDir: String, name: String, url: String): GitResult<Unit> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, _ ->
                git.remoteAdd().setName(name).setUri(URIish(url)).call()
                GitResult<Unit>(success = true)
            }
        }

    override suspend fun removeRemote(repoDir: String, name: String): GitResult<Unit> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, _ ->
                val cmd = git.remoteRemove()
                cmd.setName(name)
                cmd.call()
                GitResult<Unit>(success = true)
            }
        }

    override suspend fun diff(repoDir: String, paths: List<String>?): GitResult<String> =
        withContext(Dispatchers.IO) {
            withRepo(repoDir) { git, repo ->
                val out = ByteArrayOutputStream()
                DiffFormatter(out).use { formatter ->
                    formatter.setRepository(repo)
                    formatter.setDiffComparator(RawTextComparator.DEFAULT)
                    val reader = repo.newObjectReader()
                    reader.use { r ->
                        val headTree: AbstractTreeIterator = repo.resolve(Constants.HEAD)?.let { rev ->
                            val parser = org.eclipse.jgit.treewalk.CanonicalTreeParser()
                            parser.reset(r, org.eclipse.jgit.revwalk.RevWalk(repo).parseTree(rev))
                            parser
                        } ?: EmptyTreeIterator()
                        val workTree = FileTreeIterator(repo)
                        val walker = TreeWalk(repo)
                        walker.addTree(headTree)
                        walker.addTree(workTree)
                        val entries = DiffEntry.scan(walker)
                        val filtered = if (paths.isNullOrEmpty()) entries
                        else entries.filter { d -> paths.any { p -> d.newPath == p || d.oldPath == p } }
                        formatter.format(filtered)
                    }
                }
                val text = out.toString(StandardCharsets.UTF_8.name())
                GitResult(success = true, data = text.ifBlank { "（无差异）" })
            }
        }

    // ---- 内部工具 ----

    private fun open(repoDir: String): Repository =
        FileRepositoryBuilder()
            .setGitDir(File(repoDir, ".git"))
            .setWorkTree(File(repoDir))
            .readEnvironment()
            .build()

    private suspend fun <T> withRepo(
        repoDir: String,
        block: (Git, Repository) -> GitResult<T>
    ): GitResult<T> = withContext(Dispatchers.IO) {
        runCatching {
            val repo = open(repoDir)
            Git(repo).use { git -> block(git, repo) }
        }.getOrElse {
            val msg = if (it is GitAPIException) it.message else it.message
            GitResult(false, error = msg ?: "Git 操作失败")
        }
    }

    private fun credentialProvider(): UsernamePasswordCredentialsProvider? =
        if (auth.username.isNotBlank())
            UsernamePasswordCredentialsProvider(auth.username, auth.password)
        else null
}