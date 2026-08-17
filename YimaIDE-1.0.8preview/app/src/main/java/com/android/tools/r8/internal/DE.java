package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DE {
    public static final DE c = new DE(0, "RUNTIME_VISIBLE_ANNOTATIONS", "RuntimeVisibleAnnotations");
    public static final DE d = new DE(1, "RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS", "RuntimeVisibleParameterAnnotations");
    public static final DE e = new DE(2, "RUNTIME_VISIBLE_TYPE_ANNOTATIONS", "RuntimeVisibleTypeAnnotations");
    public static final DE f = new DE(3, "RUNTIME_INVISIBLE_ANNOTATIONS", "RuntimeInvisibleAnnotations");
    public static final DE g = new DE(4, "RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS", "RuntimeInvisibleParameterAnnotations");
    public static final DE h = new DE(5, "RUNTIME_INVISIBLE_TYPE_ANNOTATIONS", "RuntimeInvisibleTypeAnnotations");
    public final String b;

    public DE(int i, String str, String str2) {
        super(str, i);
        this.b = str2;
    }
}
