package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3036xb {
    public static final boolean a(char c, char c2, boolean z) {
        if (c == c2) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c);
        char upperCase2 = Character.toUpperCase(c2);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static void a() {
        if (10 <= new C3092yA(2, 36).c) {
            return;
        }
        z01.a("radix 10 was not in valid range ", new C3092yA(2, 36));
    }
}
