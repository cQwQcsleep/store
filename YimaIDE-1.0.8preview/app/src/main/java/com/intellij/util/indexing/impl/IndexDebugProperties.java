package com.intellij.util.indexing.impl;

import com.intellij.util.SystemProperties;
import com.intellij.util.indexing.IndexId;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class IndexDebugProperties {
    public static final ThreadLocal<IndexId<?, ?>> DEBUG_INDEX_ID = new ThreadLocal<>();
    public static volatile boolean DEBUG = SystemProperties.getBooleanProperty("intellij.idea.indices.debug", false);
    public static volatile boolean IS_UNIT_TEST_MODE = false;
    public static volatile boolean IS_IN_STRESS_TESTS = false;
    public static final boolean EXTRA_SANITY_CHECKS = SystemProperties.getBooleanProperty("intellij.idea.indices.debug.extra.sanity", false);
}
