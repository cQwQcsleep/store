package com.intellij.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ExceptionUtilRt {

    public static class RethrownStack extends Throwable {
        public RethrownStack() {
            super("Rethrown at");
        }
    }

    public static <T extends Throwable> T addRethrownStackAsSuppressed(T t) {
        t.addSuppressed(new RethrownStack());
        return t;
    }

    public static void rethrowUnchecked(Throwable th) throws Error, RuntimeException {
        if (th instanceof Error) {
            throw ((Error) addRethrownStackAsSuppressed((Error) th));
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) addRethrownStackAsSuppressed((RuntimeException) th));
        }
    }
}
