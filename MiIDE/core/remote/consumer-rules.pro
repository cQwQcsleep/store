# 远程模块混淆规则：JSch 需要保留部分动态调用的内部类
-keep class com.jcraft.jsch.** { *; }
