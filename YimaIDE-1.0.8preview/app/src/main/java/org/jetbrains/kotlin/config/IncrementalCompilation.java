package org.jetbrains.kotlin.config;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class IncrementalCompilation {
    public static final String INCREMENTAL_COMPILATION_JS_PROPERTY = "kotlin.incremental.compilation.js";
    public static final String INCREMENTAL_COMPILATION_JVM_PROPERTY = "kotlin.incremental.compilation";

    private static void addJvmSystemFlag(List<String> list, String str) {
        list.add("D" + str + "=true");
    }

    public static boolean isEnabledForJs() {
        return Boolean.valueOf(System.getProperty(INCREMENTAL_COMPILATION_JS_PROPERTY)).booleanValue();
    }

    public static boolean isEnabledForJvm() {
        return Boolean.valueOf(System.getProperty(INCREMENTAL_COMPILATION_JVM_PROPERTY)).booleanValue();
    }

    @Deprecated
    public static void setIsEnabledForJs(boolean z) {
        System.setProperty(INCREMENTAL_COMPILATION_JS_PROPERTY, String.valueOf(z));
    }

    @Deprecated
    public static void setIsEnabledForJvm(boolean z) {
        System.setProperty(INCREMENTAL_COMPILATION_JVM_PROPERTY, String.valueOf(z));
    }

    @Deprecated
    public static void toJvmArgs(List<String> list) {
        if (isEnabledForJvm()) {
            addJvmSystemFlag(list, INCREMENTAL_COMPILATION_JVM_PROPERTY);
        }
        if (isEnabledForJs()) {
            addJvmSystemFlag(list, INCREMENTAL_COMPILATION_JS_PROPERTY);
        }
    }
}
