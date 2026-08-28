# 星河Mode_v3.2.6 脱壳与分析报告

> 生成时间：2026-08-28 16:19

> 分析方式：GM1容器明文carve → jadx反编译 → 云壳l2双base64-XOR穷举解密

> 注意：应用层使用 YJ-VMP 保护，方法体加密于 .jgapp，**脚本指令不可静态提取**（详见末节）

---

## 一、元信息

| 项目 | 值 |
|---|---|
| 包名 | `com.Mode.toolbox` |
| 版本 | v3.2.6 |
| 文件大小 | 10,345,603 字节 (9.9 MB) |
| 签名 | MYKEYS（自定义证书） |
| 壳结构 | dpt-shell(stub) + NP管理器控制流混淆8.0 + **YJ-VMP** + yunzhuru云壳 |
| 提权 | Shizuku (`moe.shizuku.privileged.api`) |
| native库 | libcom.Mode.toolbox.so(VMP引擎) + libyj-vmp-lib.so(VM解释器) + libArkSafe.so(Virbox) + libtoolChecker.so(RootBeer检测) |
| 云壳配置 | APP_ID=49089, VERSION=161, bcrypt卡密哈希 |
| 脱壳产物 | 5个dex明文嵌入于classes.dex的GM1容器，全部carve成功 |
| 声明文件 | assets/声明.txt（免责声明） + assets/还原/一键还原.sh（伪装脚本） |
---

## 二、脱壳过程

### GM1容器结构

与陌尘的 JDE1 加密容器不同，星河Mode 使用 **NP管理器 GM1 合并格式**：

- 外层 stub dex（仅 9.8KB，4个类：StubApp/DtcLoader/Configuration/a）

- stub dex 之后为 GM1 索引区（TLV结构，4MB）

- 5个原始 dex **明文嵌入**于容器内（无需解密，直接 carve）

```
| # | 偏移 | 大小 | 字符串数 | 类数 | 内容 |
|---|---|---|---|---|---|
| 1 | 0x27bc | 138KB | 1937 | 62 | Shizuku API + NPStringFog + Ark.VMProtect |
| 2 | 0x24480 | 3.3MB | 30368 | 802 | shadow-okhttp3/kotlin/okio + 云壳(log/location/video + vm/debug/Ss) |
| 3 | 0x34fb10 | 478KB | 2114 | 542 | j$ desugar runtime |
| 4 | 0x3c481c | 104KB | 1124 | 90 | **应用主体**（com.Mode.toolbox.* Activity类） |
| 5 | 0x3ddf38 | 15KB | 201 | 54 | com.qvpx.ndhe（VMP stub桩类） |
```

### YJ-VMP 保护

应用主体 dex（#4）的所有业务方法被 **YJ-VMP** 抽取为 native：

- `MainActivity.exec(String)` → `private native void exec(String)`（方法体在 .jgapp）

- `vmInterpret`（自定义VM解释器）+ `YjStr_decode`（字符串解密）+ `StrParse`

- `.jgapp`（1.2MB）存储加密的方法字节码，熵 6.84，流/块加密（非循环XOR）

- 宿主机加载测试：`YjStr_decode` 可调用但输出为二进制base64层，需完整Android运行时才能由VM解释器执行

---

## 三、应用功能结构

### Activity/Service 类清单（从类名还原功能）

| 类名 | 功能 |
|---|---|
| `MainActivity` | 主界面，Shizuku授权 + native exec执行脚本 + 音量键交互 |
| `AboutMeActivity` | 关于作者 |
| `AdvancedPowerSavingActivity` | 高级省电 |
| `BackupActivity` | 备份/还原 |
| `BatterytemperaturefloatingwindowService` | 电池温度悬浮窗服务 |
| `DirectDrivePowerSupply` | 直驱供电 |
| `LandscapeHelper` | 横屏辅助 |
| `LoginPageActivity` | 登录/卡密验证 |
| `MoreFeaturesActivity` | 更多功能 |
| `OutputPageActivity` | 脚本输出页 |
| `ShizukuToast` | Shizuku状态提示 |
| `MyApplication` | 应用初始化（加载云壳） |
| `NativeUtil` | native工具方法 |
| `C0002/C0003` | 辅助类 |
| `CxflxsyFAnw` | 混淆功能类 |
| `OekYgCGFrr` | 混淆功能类 |
| `aOjxydnBJHxLjORd` | 混淆功能类 |
| `xbLcSnGDrRkiicqgN` | 混淆功能类 |
| `yHGvnmaPMINdUMdJJT` | 混淆功能类 |

### SharedPreferences 键（功能开关名）

> 从 app dex 字符串表提取，对应界面上的开关/功能项：

```
APPLICATION
AboutMe
ActivationFailed
AdvancedPowerSaving
AndroidID
BackupRestore
BalancMode
BatteryMode
CLINIT
ClearBackground
ClearShellCommandFailed
ColorOS
ColorOSContent
ColorOSExpanded
Common
CommonContent
CommonExpanded
ContentResolverAPI
CopyOutput
DEVICES
DOMAINS
Developer
DeviceInfo
GameDeveloper
ISMMAINPROCESS
Ignorebatteryoptimization
Intelligentoptimizationapplication
JoinUs
KipGrN
LAUNCHER
LAoHFpj
LLIIIL
LLLLLL
Modifybattery
Modifytemperature
MoreFeatures
Moreoptimization
NETWORK
NoPermission
OSvRgw
OneClickClear
OriginOS
OriginOSContent
OriginOSExpanded
PACKAGE
PDXOaOA
PerfMode
QRAeEYaXx
RQOMYE
ReMwduy
RestoreFail
ShellContext
ShellContextThis
ShizukuExec
ShizukuStatusDetection
StartFail
Terminal
TmbOIAGI
ToggleColorOS
ToggleCommon
ToggleOriginOS
ToggleXiaomi
Troubleshooting
VERSION
VPNCHECK
WLJnIZ
Xiaomi
XiaomiContent
XiaomiExpanded
ZWZlro
```

### MainActivity 字段

- `private static final short[] f26short ~ f43short`（18个短整型数组，存储VMP加密的脚本数据）

- `public Process p`（Shizuku进程）

- `private SharedPreferences prefs`（开关状态持久化）

- `public TextView t1`（脚本输出显示）

- `Shizuku.OnRequestPermissionResultListener RL`（权限回调）

---

## 四、云壳(yunzhuru)解密字符串全录

> 从主 dex 字符串表穷举双 base64-XOR 解密，共恢复 **765** 条字符串

### 网络端点

| 类型 | 地址 |
|---|---|
| 端点 | `assets/yunzhuru/origin.apk` |
| 端点 | `http://*.yunzhuru.cn/shell.php?version=161&appid=` |
| 端点 | `http://*.yunzhuru.com/shell.php?version=161&appid=` |
| 端点 | `https://*.yunzhuru.cn/shell.php?version=161&appid=` |
| 端点 | `https://*.yunzhuru.com/shell.php?version=161&appid=` |
| 端点 | `https://shell.yunzhuru.cn/shell.php?version=161&appid=` |
| 端点 | `https://shell.yunzhuru.com/shell.php?version=161&appid=` |
| 端点 | `https://www.baidu.com` |
| 端点 | `https://yunzhuru.com` |
| 端点 | `mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26jump_from%3Dwebapi%26k%3D` |
| 端点 | `wss://ws.yunzhuru.com/ws` |
| 端点 | `wss://ws.yunzhuru.com/ws?appid=` |
| 端点 | `yunzhuru Mozilla/5.0` |
| 端点 | `【JS接口】http 回调失败` |
| 端点 | `【JS接口】http 找不到 WebView` |
| 端点 | `【JS接口】http 方法执行结果回调` |
| 端点 | `【JS接口】http 方法被调用 popupId：` |
| 端点 | `【JS接口】http 方法请求结果:` |
| 端点 | `【JS接口】http 的 httpRequest 方法被调用 url：` |

### 云壳能力与日志

```

shell跳转,不劫持
 文字弹窗 间隔时间未到，不弹（剩余约 
 次连接失败：
Activity生命周期回调注册成功（含黑名单拦截）
Hook控件重写成功：
Instrumentation 替换失败：
Instrumentation2 替换失败：
Load SignatureKiller library failed
WebSocket连接成功
[DialogHook] Hook 初始化完成
[DialogHook] applyViewRewrite异常：
[DialogHook] hideParentByLevel 失败 level=
[DialogHook] removeViewImmediate 失败：
[DialogHook] removeViewImmediate 成功
[DialogHook] 关键词处理异常：
[DialogHook] 关键词替换：
[DialogHook] 初始化异常：
[DialogHook] 命中关键词：
[DialogHook] 处理弹窗：
[DialogHook] 已初始化，跳过
[DialogHook] 已存在代理，跳过
[DialogHook] 弹窗处理失败：
[DialogHook] 打开链接失败: 
[DialogHook] 非 Application Context，忽略
[LaunchCheck] 启动失败：LAUNCHER 无效
[LaunchCheck] 弹窗尚未全部关闭，继续等待
[LaunchCheck] 跳转异常：
[PopupClear] 已清空全部弹窗ID缓存（need + closed）
[缓存]使用缓存配置加载
[缓存]使用缓存配置加载失败：
[缓存]无缓存,本次不调用缓存加载
[缓存]有缓存,使用缓存配置加载弹窗
[缓存]缓存配置加载成功
[缓存]触发栈顶立即弹窗
[缓存]黑名单写入失败：
[菜鸟云验证] killPM解除安卓9+隐藏API限制
[菜鸟云验证] 加载 SO 失败
[菜鸟云验证] 加载 SO 成功
[菜鸟云验证] 加载 yzrSignatureKiller库 失败
[菜鸟云验证] 加载 yzrSignatureKiller库 成功
[菜鸟云验证] 未找到APK路径,加载xhook
[菜鸟云验证] 解除限制失败：
[菜鸟云验证] 读取 origin.apk 失败
[菜鸟云验证] 调用 hookApkPath 完成
activit可用,弹出shell弹窗
activit已不可用,未弹出shell弹窗
cainiaosockethook.so 加载失败
cainiaosockethook.so 加载成功
com.saurik.substrate.MS$Hook
enable_popup_kill_all
hookInstrumentation 初始化失败：
hookInstrumentation2 初始化失败：
http://*.yunzhuru.cn/shell.php?version=161&appid=
http://*.yunzhuru.com/shell.php?version=161&appid=
https://*.yunzhuru.cn/shell.php?version=161&appid=
https://*.yunzhuru.com/shell.php?version=161&appid=
https://shell.yunzhuru.cn/shell.php?version=161&appid=
https://shell.yunzhuru.com/shell.php?version=161&appid=
initNativeHook 加载失败
newActivity 创建失败
newActivity 创建实例失败：
newActivity 反射失败，尝试 fallback 创建 → 
shell 跳转放行：
shell_protectedother
yzrByteHook.so 加载失败
yzrByteHook.so 加载成功
”，已尝试关闭弹窗
⚠️ SP类型转换失败：
⚠️ 类型转换失败：
✅ PackageInfo.CREATOR 替换成功
✅ 签名伪造成功：
✅ 远程dex执行成功：
❌ CREATOR 替换失败，仍为原始对象
❌ DEX下载失败：
❌ 伪造签名失败：
❌ 关闭指定弹窗失败：
❌ 加载远程DEX失败：
❌ 打开类失败：
❌ 打开链接失败：
❌ 控件信息获取失败
❌ 无法获取远程配置，跳过弹窗关键字判断
❌ 未在弹窗中发现关键字
❌ 签名伪造失败，签名不一致
❌ 签名伪造失败，读取为空
❌ 获取窗口信息失败：
❌ 远程dex下载失败：
【JS接口】Toast 方法被调用
【JS接口】close 方法被调用
【JS接口】close() 方法被调用
【JS接口】copyText 方法被调用
【JS接口】getAppInfo 方法被调用
【JS接口】hasPermission 方法被调用 检查权限：
【JS接口】http 回调失败
【JS接口】http 找不到 WebView
【JS接口】http 方法执行结果回调
【JS接口】http 方法被调用 popupId：
【JS接口】http 方法请求结果:
【JS接口】http 的 httpRequest 方法被调用 url：
【JS接口】isAppInstalled 方法被调用 检测包名：
【JS接口】log 方法被调用
【JS接口】openActivity 方法被调用
【JS接口】openUrl 方法被调用
【JS接口】readSP 方法被调用 读取配置：
【JS接口】verifyCardKey 回调失败
【JS接口】verifyCardKey 找不到 WebView
【JS接口】verifyCardKey 方法执行结果回调
【JS接口】verifyCardKey 方法被调用 popupId：
【JS接口】verifyCardKey2 回调失败：
【JS接口】verifyCardKey2 找不到 WebView
【JS接口】verifyCardKey2 方法执行结果回调
【JS接口】verifyCardKey2 方法被调用 popupId：
【JS接口】writeSP 方法被调用 写入配置：
【JS接口】包不存在：
【JS接口】包存在：
【JS接口】权限 
【JS接口】权限名为空
【JS接口】检查权限失败：
【目标类名解析失败】Intent 关键信息：
【目标类名解析失败】将启动的 Intent 关键信息：
【跳转参数】共 
【隐式跳转】无法解析目标类，URI: 
【隐式跳转】无法解析目标类，action: 
【隐式跳转】未解析到目标类名，URI: 
【隐式跳转】未解析到目标类名，action: 
】成功解析IP：
主进程,已进行 attachBaseContext 初始化
关闭输入框弹窗
加载远程DEX失败
加载黑名单失败：
动态重写控件成功：
反射获取Activity失败：
命中精细化规则不弹窗: 
子进程,未进行 attachBaseContext 初始化
布局JSON获取失败
开启悬浮窗失败
开屏广告弹窗: 
当前无有效Activity，无法显示消息弹窗
所有请求失败，1 秒后重试
所有请求失败，5秒后重试
打开窗口失败：
打开链接失败：
执行PATH_X_HOOK初始化失败
文字弹窗 已写入关闭记录
文字弹窗 消息内容发生变化，弹窗
文字弹窗 被点击遮罩关闭
方法1：移除窗口失败：
方法1：移除窗口成功：
方法2：移除弹窗失败：
方法2：移除弹窗成功: 
方法A：WindowManager.removeView 移除成功：
方法B：removeViewImmediate 移除成功：
方法C：removeView(view, true) 移除成功：
无规则 → 默认允许弹窗
显示消息弹窗失败
替换失败：目标类不存在 → 
最终移除失败：
最终请求：自定义DNS解析成功：
未开启Hook功能,关键字拦截功能不可用
未开启远程dex加载
检测失败：Application 继承链中不存在目标类：
检测成功：Application 继承链完整
正在加载远程dex
注册生命周期回调失败：
添加遮罩失败：
清除遮罩失败：
签名校验失败，终止重试：
系统DNS解析失败：
获取 APK 路径失败
解析接收数据失败
触发栈监听弹窗
触发栈顶立即弹窗
设置图片失败：
设置图片成功：
设置文本成功：
请求成功 code=200
读取 origin.apk 失败
调用 callActivityOnDestroy 失败: 
调用 execStartActivity 失败
调用 execStartActivity 失败：
调用 hookApkPath 完成
远程配置获取成功
远程配置获取成功：
黑名单写入失败：
黑名单写入成功，共 
黑名单已加载，数量：
（Dialog 弹窗 TYPE_APPLICATION）
🎯 已关闭一个匹配弹窗: 
🎯 点击 × 已关闭弹窗：
```

### 其它解密字符串（可读）

```
 not found in class 
#4]!xbk!++Cbi,}"5iK"cl' 0(H#rnn0&
%s(%b, %d, '%s', '%s')
&card_type=group&source=qrcode
&m7%G<9S.r)fVr/P00!&\2uR5q"'M0<B#
(/Y-tEoE(.B)x_bP#(N>u
/system/lib/libc_malloc_debug_qemu.so
6!w~'5<y6 lz+/1l=&`m&
8JR6bgz:2TF$QiL&1{R8b
:~BN%HTr'ZFo	TUz9o_i"_
=$^Ibjjb45Offrzd=/Oza
ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789
AES/CBC/PKCS5Padding
ActivityKeeper window ready; running remote rules: 
B0Y>C4d@B1B:O.iUI7N-B
B5mDxF0vU2lA9x=6G4~{vA5?F)NZxM54
E$@&=\G"E%["1FJ7N#W5<
E>TpBL9DL/E_FT)BE5ECA
Failed to set hidden API exemptions
Get apk path failed
HTML close ignored: dialog instance does not match
HsJ~Nj7P@lT=_$!S^.\}Ud{Q[o_|Df2AM
JM\~?h:5ftB{"b:-Nk\
Landroid/content/pm
Landroid/os/Parcel;
OBIi>w'$FNFS2g()mt\t4v.
Popup show was not observed: 
Unable to obtain activity decor view: 
Unexpected end of chunked stream
[SelfType2] viewNames=null
android.app.ActivityOptions
android.app.ActivityThread
android.app.AppComponentFactory
android.app.Application
android.app.activity_class
android.content.pm.PackageManager
android.intent.action.SEND
android.intent.action.VIEW
android.intent.extra.TEXT
android.os.SystemProperties
android.settings.action.MANAGE_OVERLAY_PERMISSION
android.view.IWindowManager
android.view.IWindowSession
android.view.WindowManagerGlobal
application/x-www-form-urlencoded
b7BF-i7.t2UL2_3>_8BF.Sx's4M
black_activities_cache.json
com.frida.server.Frida
com.saurik.substrate
content-encoding: gzip
currentActivityThread
dalvik.system.VMRuntime
data:image/png;base64,
de.robv.android.xposed
de.robv.android.xposed.XposedBridge
de.robv.android.xposed.XposedHelpers
de/robv/android/xposed
enableMessagePopups
enable_popup_keywords
exks{Qs:cyfhzVip<7b}wO
file:///android_asset/
g=p>;WFVg<k:7MKCl:g-:
getApkContentsSigners
getCurrentActivity source=ActivityThreadReflectionFallback, activity=
getWindowManagerService
i _0:eajkaA{.am|$	@w8v
iiTB4k7%
kUH2a05
xW
mCanceledOnTouchOutside
mInitialApplication
mqqapi://card/show_pslcard?src_type=internal&version=1&uin=
new_black_package_list
n|zW2v+
y{{RsH&Jk}ih<q.Cj`YI2}.H
r5b7%"$]r4y3)8)Hy2u$$
removeViewImmediate
sCurrentActivityThread
sWindowManagerService
setHiddenApiExemptions
transfer-encoding: chunked
window['%s'](JSON.parse(%s))
xQC+(]lHxPX/$Ga]sVT8)
z(]:yt-"z6IfW~=em/M1Bu;iz"
}o|d)aS=xU{Z(fS9vUgR8y
```

---

## 五、YJ-VMP 脱壳尝试与限制

### 已完成

1. 在宿主机 x86_64 加载 `libyj-vmp-lib.so` + `libcom.Mode.toolbox.so`（打补丁去除符号版本+提供bionic stub）

2. 成功调用 `YjStr_decode`、`YjStr_decode_inplace`、`StrParse`、`vmInterpret` 等导出函数

3. 挂载真实 JVM 触发 `JNI_OnLoad`（崩溃于 env 路径，但云壳字符串已在内存中解密）

4. 穷举解密云壳 dex 的双 base64-XOR 字符串（765条）

### 不可静态完成

| 障碍 | 说明 |
|---|---|
| `.jgapp` 流加密 | 熵 6.84，非循环XOR/RC4，已知明文攻击未命中（密钥流无周期性） |
| YJ VM 解释器 | `vmInterpret` 需完整 Android ART 运行时（JNI/反射/类加载），宿主机环境不满足 |
| 方法体抽取 | `exec()` 等业务方法的字节码不在 dex 内，而在 .jgapp 加密区 |
| NP checksum 破坏 | 主 dex 的 field/method 索引被 NP 混淆篡改，jadx 反编译 OOM |
| NPStringFog5 | fix_10172 含 162 条 hex 雾化串，非简单 XOR（暴力 256 key + 449 候选 key 均未命中可读文本） |
> 完整脱壳需在 root 真机/模拟器上运行 APK，用 Frida hook `vmInterpret` 或 dump ART 内存

---

## 六、与陌尘优化对比

| 项目 | 陌尘优化_4.3.6 | 星河Mode_v3.2.6 |
|---|---|---|
| 包名 | com.iqoocg.nm | com.Mode.toolbox |
| 壳 | dpt(JDE1+魔改RC4) | dpt(GM1明文) + YJ-VMP |
| 字符串保护 | NPStringFog(hex+XOR"npmanager") 可解 | NPStringFog5(未知算法) + YJ-VMP 不可静态解 |
| 脚本提取 | 47个完整脚本全部提取 | **不可提取**（VMP加密于.jgapp） |
| 云壳 | yunzhuru（同源） | yunzhuru（同源，APP_ID=49089） |
| 远程端点 | shell.yunzhuru.cn/.com + wss://ws.yunzhuru.com/ws | 完全相同 |
| 功能定位 | vivo/iQOO 优化工具箱 | vivo/iQOO 优化工具箱（同类型） |
| 卡密 | 无 | 有（bcrypt哈希 + LoginPageActivity） |
---

## 七、风险汇总

| # | 能力/风险 | 级别 | 说明 |
|---|---|---|---|
| 1 | yunzhuru WebSocket 远程控制 + JS桥任意HTTP | 高 | 与陌尘同源云壳，服务器可下发任意指令 |
| 2 | SignatureKiller 签名伪造 | 高 | hook PackageManager 伪造签名 |
| 3 | DialogHook 弹窗劫持 | 高 | 可拦截/替换系统弹窗，控制用户可见内容 |
| 4 | Shizuku 提权执行 native exec | 高 | 以 root 权限执行任意脚本（内容不可见） |
| 5 | 卡密验证 + 远程激活 | 中 | 运行时联网验证，行为受服务器控制 |
| 6 | RootBeer root检测 | 低 | 检测root环境但未阻止运行 |
| 7 | 电池温度悬浮窗服务 | 中 | 持久后台服务 + 悬浮窗 |
| 8 | 18个short[]加密脚本数组 | 高 | 功能脚本完全不可审计 |

---

## 八、分析产物清单

| 产物 | 位置 |
|---|---|
| 原始APK | xinghe.apk |
| APK解包 | ex/ |
| 脱壳dex(5个) | carved_*.dex / fix_*.dex |
| 部分反编译源码 | jd_fix_3950620/（app类） |
| 云壳解密字符串 | l2_hits.json（765条） |
| YJ字符串 | yj_strings.json（101条） |
| 脱壳工具链 | host/（harness + 补丁so） |
| 分析日志 | jadx_main*.log |