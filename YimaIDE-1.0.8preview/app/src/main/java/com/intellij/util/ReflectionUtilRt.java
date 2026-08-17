package com.intellij.util;

import com.intellij.openapi.diagnostic.LoggerRt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ReflectionUtilRt {

    public static final class MySecurityManager extends SecurityManager {
        private static final MySecurityManager INSTANCE = new MySecurityManager();

        private MySecurityManager() {
        }

        public Class<?>[] getStack() {
            return getClassContext();
        }
    }

    private ReflectionUtilRt() {
    }

    public static Class<?> findCallerClass(int i) {
        try {
            Class<?>[] stack = MySecurityManager.INSTANCE.getStack();
            int i2 = i + 1;
            if (stack.length > i2) {
                return stack[i2];
            }
            return null;
        } catch (Exception e) {
            LoggerRt.getInstance(ReflectionUtilRt.class).warn(e);
            return null;
        }
    }
}
