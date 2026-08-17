package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ObjectUtilsRt {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/intellij/util/ObjectUtilsRt", "reachabilityFence"));
    }

    public static void reachabilityFence(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(0);
        }
    }
}
