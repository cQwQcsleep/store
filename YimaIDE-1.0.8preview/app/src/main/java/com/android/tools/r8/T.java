package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T {
    public static CompilationFailedException a() {
        return new CompilationFailedException("Compilation failed to complete", null, false);
    }

    public static CompilationFailedException a(Throwable th) {
        return new CompilationFailedException("Compilation failed to complete", th, false);
    }

    public static CompilationFailedException a(String str, Throwable th, boolean z) {
        return new CompilationFailedException(str, th, z);
    }

    public static CompilationFailedException a(String str) {
        return new CompilationFailedException(str, null, false);
    }
}
