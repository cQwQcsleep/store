package com.intellij.util;

import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DefaultBundleService {
    private static final DefaultBundleService INSTANCE = new DefaultBundleService();
    private static final ThreadLocal<Boolean> ourDefaultBundle = ThreadLocal.withInitial(new Supplier() { // from class: sf3
        @Override // java.util.function.Supplier
        public final Object get() {
            return Boolean.FALSE;
        }
    });

    public static boolean isDefaultBundle() {
        return ourDefaultBundle.get().booleanValue();
    }
}
