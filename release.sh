#!/usr/bin/env bash
# QQ文本改写助手 · 一键构建+发布脚本
# 固化环境重置后反复手动配置/发布的操作，每次发布只需一条命令：
#   ./release.sh            # 构建 + 打包 + 提交 + 推送 store 分支 + 更新 GitHub Release
#   ./release.sh --build    # 仅构建并复制 APK 到 releases/，不做发布
set -euo pipefail
cd "$(dirname "$0")"

# ---------- 1) 固定构建环境（环境重置后无需手动配置） ----------
export JAVA_HOME="${JAVA_HOME:-/root/.local/share/mise/installs/java/17.0.2}"
export ANDROID_HOME="${ANDROID_HOME:-/workspace/android-sdk}"
export ANDROID_SDK_ROOT="$ANDROID_HOME"
export PATH="$JAVA_HOME/bin:$ANDROID_HOME/platform-tools:$PATH"

# ---------- 2) 构建 ----------
echo "==> [1/4] 构建 debug APK"
./gradlew :app:assembleDebug --console=plain

APK="app/build/outputs/apk/debug/app-debug.apk"
REL="releases/app-debug-v1.0.0.apk"
mkdir -p releases
cp -f "$APK" "$REL"
echo "==> 产物: $REL ($(du -h "$REL" | cut -f1))"

if [ "${1:-}" = "--build" ]; then
  echo "==> 仅构建完成（未发布）"
  exit 0
fi

# ---------- 3) 发布参数与认证检查 ----------
REPO="cQwQcsleep/store"          # 发布目标仓库
BRANCH="globaltext-assistant"    # 提交推送的分支
TAG="globaltext-assistant-v1.0.0" # Release 标签

if ! command -v gh >/dev/null 2>&1; then
  echo "!! 缺少 gh CLI，请先安装 GitHub CLI" >&2
  exit 1
fi
if ! gh auth status >/dev/null 2>&1; then
  echo "!! gh 未登录，请先执行: gh auth login" >&2
  exit 1
fi
if ! git ls-remote --heads "https://github.com/$REPO.git" "$BRANCH" >/dev/null 2>&1; then
  echo "!! 无法访问 $REPO，请检查 git 推送凭据（GitHub token）" >&2
  exit 1
fi

# ---------- 4) 提交并推送 store 分支 ----------
echo "==> [2/4] 提交并推送 $REPO/$BRANCH"
# 仓库级提交身份：无全局配置时使用，避免每次发布都手动配置
if ! git config user.email >/dev/null 2>&1; then
  git config user.name "cQwQcsleep"
  git config user.email "cQwQcsleep@users.noreply.github.com"
fi
git add \
  app/src/main/java/com/example/globaltext/CatConfig.java \
  app/src/main/java/com/example/globaltext/GlobalAccessibilityService.java \
  app/src/main/java/com/example/globaltext/MainActivity.java
if git diff --cached --quiet; then
  echo "==> 无代码改动，跳过提交"
else
  git commit -m "$(cat <<'EOF'
新增流式处理模式：只对新增/修改片段追加喵，删除追加串不再补回

- 流式模式维护干净原文基线，从纯原文增量重写，根治长文本堆叠
- 识别用户删除追加串（喵/颜文字）则保持删除、不补回
- 颜文字固定在末尾并复用上次结果，避免写回时随机跳变
EOF
)"
fi
git push "https://github.com/$REPO.git" "$BRANCH"

# ---------- 5) 更新 GitHub Release 资产 ----------
echo "==> [3/4] 更新 GitHub Release 资产 ($TAG)"
if gh release view "$TAG" --repo "$REPO" >/dev/null 2>&1; then
  gh release upload "$TAG" "$REL" --repo "$REPO" --clobber
else
  gh release create "$TAG" "$REL" --repo "$REPO" \
    --title "QQ文本改写助手 v1.0.0" --notes "流式处理模式：只对新增/修改片段追加喵，删除追加串不再补回"
fi

echo "==> [4/4] 发布完成：https://github.com/$REPO/releases/tag/$TAG"
