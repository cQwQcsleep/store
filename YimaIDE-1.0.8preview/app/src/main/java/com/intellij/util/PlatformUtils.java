package com.intellij.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PlatformUtils {
    private static final Set<String> COMMERCIAL_EDITIONS = new HashSet(Arrays.asList("idea", "AppCode", "CLion", "Python", "DataSpell", "Ruby", "PhpStorm", "WebStorm", "DataGrip", "Rider", "GoLand", "RustRover", "Aqua"));

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/PlatformUtils", "getPlatformPrefix"));
    }

    public static String getPlatformPrefix() {
        String platformPrefix = getPlatformPrefix("idea");
        if (platformPrefix == null) {
            $$$reportNull$$$0(0);
        }
        return platformPrefix;
    }

    private static boolean is(String str) {
        return str.equals(getPlatformPrefix());
    }

    public static boolean isJetBrainsClient() {
        return is("JetBrainsClient");
    }

    public static boolean isQodana() {
        return SystemProperties.getBooleanProperty("qodana.application", false);
    }

    public static String getPlatformPrefix(String str) {
        return System.getProperty("idea.platform.prefix", str);
    }
}
