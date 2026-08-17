package com.android.tools.r8.ir.optimize;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T {
    public static final T b = new T(0, "ALWAYS");
    public static final T c = new T(1, "SINGLE_CALLER");
    public static final T d = new T(2, "MULTI_CALLER_CANDIDATE");
    public static final T e = new T(3, "SIMPLE");
    public static final T f = new T(4, "NEVER");

    public T(int i, String str) {
        super(str, i);
    }
}
