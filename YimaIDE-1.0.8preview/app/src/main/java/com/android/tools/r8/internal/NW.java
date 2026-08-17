package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NW {
    public static final NW c = new NW(0, "JUST_MEMBERS", "-keepclassmembers");
    public static final NW d = new NW(1, "CLASS_OR_MEMBERS", "-keep");
    public static final NW e = new NW(2, "CLASS_AND_MEMBERS", "-keepclasseswithmembers");
    public static final NW f = new NW(3, "CHECK_DISCARD", "-checkdiscard");
    public final String b;

    public NW(int i, String str, String str2) {
        super(str, i);
        this.b = str2;
    }
}
