package com.miide.core.plugin

/**
 * 内置插件市场目录（基础版离线源）。
 *
 * 完整版将由 GitHub 托管的 manifest 仓库动态拉取；基础版先内置一批
 * 官方脚本插件 / 主题包 / 功能包作为「可安装」示范。
 */
object PluginCatalog {

    private fun pkg(manifest: PluginManifest) = PluginPackage(manifest)

    /** 市场可见的全部插件包。 */
    val packages: List<PluginPackage> = listOf(
        pkg(
            PluginManifest(
                id = "miide.json-formatter",
                name = "JSON 格式化助手",
                version = "1.0.0",
                description = "将一段 JSON 文本格式化为缩进友好的样式，支持从剪贴板读取。",
                author = "MiIDE 官方",
                type = PluginType.SCRIPT,
                entry = "main.js",
                icon = "data",
                script = """
                    (function () {
                      MiIDE.emit('json_formatter:start');
                      function format(input) {
                        try {
                          var obj = JSON.parse(input);
                          MiIDE.log('解析成功');
                          return JSON.stringify(obj, null, 2);
                        } catch (e) {
                          MiIDE.log('解析失败: ' + e.message);
                          return null;
                        }
                      }
                      var input = MiIDE.getPref('json_input');
                      if (!input) { input = '{"hello":"world","items":[1,2,3]}'; }
                      var out = format(input);
                      if (out) {
                        MiIDE.setPref('json_output', out);
                        console.log(out);
                        MiIDE.emit('json_formatter:done');
                      } else {
                        MiIDE.emit('json_formatter:error');
                      }
                    })();
                """.trimIndent(),
                permissions = listOf("prefs")
            )
        ),
        pkg(
            PluginManifest(
                id = "miide.ts-converter",
                name = "时间戳转换器",
                version = "1.0.0",
                description = "秒级 / 毫秒级时间戳与本地日期时间互转，支持批量行处理。",
                author = "MiIDE 官方",
                type = PluginType.SCRIPT,
                entry = "main.js",
                icon = "schedule",
                script = """
                    (function () {
                      MiIDE.emit('ts_converter:start');
                      function pad(n) { return (n < 10 ? '0' : '') + n; }
                      function fmt(ms) {
                        var d = new Date(ms);
                        return d.getFullYear() + '-' + pad(d.getMonth() + 1) + '-' + pad(d.getDate()) +
                          ' ' + pad(d.getHours()) + ':' + pad(d.getMinutes()) + ':' + pad(d.getSeconds());
                      }
                      var s = MiIDE.getPref('ts_value');
                      var ms = s ? parseInt(s) * 1000 : Date.now();
                      console.log('时间戳(ms): ' + ms);
                      console.log('本地时间:   ' + fmt(ms));
                      MiIDE.setPref('ts_output', fmt(ms));
                      MiIDE.emit('ts_converter:done');
                    })();
                """.trimIndent(),
                permissions = listOf("prefs")
            )
        ),
        pkg(
            PluginManifest(
                id = "miide.markdown-toc",
                name = "Markdown 目录生成器",
                version = "1.0.0",
                description = "扫描 Markdown 标题，生成可点击的目录列表。",
                author = "MiIDE 官方",
                type = PluginType.SCRIPT,
                entry = "main.js",
                icon = "list",
                script = """
                    (function () {
                      MiIDE.emit('md_toc:start');
                      var md = MiIDE.getPref('md_input');
                      if (!md) { md = '# 标题一\n## 子标题\n# 标题二'; }
                      var lines = md.split('\n');
                      var toc = [];
                      for (var i = 0; i < lines.length; i++) {
                        var m = /^(#{1,6})\s+(.*)$/.exec(lines[i]);
                        if (m) {
                          var level = m[1].length;
                          var indent = new Array(level).join('  ');
                          toc.push(indent + '- ' + m[2]);
                          MiIDE.log('L' + (i + 1) + ' 找到标题: ' + m[2]);
                        }
                      }
                      console.log('共发现 ' + toc.length + ' 个标题');
                      if (toc.length) console.log(toc.join('\n'));
                      MiIDE.setPref('toc_output', toc.join('\n'));
                      MiIDE.emit('md_toc:done');
                    })();
                """.trimIndent(),
                permissions = listOf("prefs")
            )
        ),
        pkg(
            PluginManifest(
                id = "miide.dark-theme",
                name = "暗夜护眼主题",
                version = "1.0.0",
                description = "低蓝光护眼配色方案（基础版：登记为主题包，视觉落地待完整版）。",
                author = "MiIDE 官方",
                type = PluginType.THEME,
                icon = "palette"
            )
        ),
        pkg(
            PluginManifest(
                id = "miide.git-pack",
                name = "Git 官方功能包",
                version = "1.0.0",
                description = "启用完整 Git 集成能力（克隆 / 提交 / 推送 / 拉取 / 分支 / diff）。",
                author = "MiIDE 官方",
                type = PluginType.FEATURE,
                icon = "commit",
                permissions = listOf("git")
            )
        ),
        pkg(
            PluginManifest(
                id = "miide.remote-pack",
                name = "远程开发官方功能包",
                version = "1.0.0",
                description = "启用 SSH / SFTP 远程开发能力（文件浏览 / 读写 / 命令执行）。",
                author = "MiIDE 官方",
                type = PluginType.FEATURE,
                icon = "cloud",
                permissions = listOf("remote")
            )
        )
    )

    /** 按 id 查找目录中的插件包。 */
    fun findById(id: String): PluginPackage? = packages.firstOrNull { it.manifest.id == id }
}
