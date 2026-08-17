package com.intellij.idea;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class AppMode {
    private static boolean disableNonBundledPlugins;
    private static boolean isRemoteDevHost;

    public static boolean isDevServer() {
        return Boolean.getBoolean("idea.use.dev.build.server");
    }

    public static boolean isDisableNonBundledPlugins() {
        return disableNonBundledPlugins;
    }

    public static boolean isRemoteDevHost() {
        return isRemoteDevHost;
    }
}
