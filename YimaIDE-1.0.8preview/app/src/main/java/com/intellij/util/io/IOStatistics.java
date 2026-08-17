package com.intellij.util.io;

import com.intellij.openapi.diagnostic.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class IOStatistics {
    static final boolean DEBUG;
    static final Logger LOG = Logger.getInstance(IOStatistics.class);

    static {
        DEBUG = System.getProperty("io.access.debug") != null;
    }

    private IOStatistics() {
        throw new AssertionError("Not for instantiation");
    }

    public static void dump(String str) {
        LOG.info(str);
    }
}
