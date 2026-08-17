package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1613gu {
    public static boolean a(int i) {
        return c(i);
    }

    public static boolean b(int i) {
        return i == 63;
    }

    public static boolean c(int i) {
        if (65 <= i && i <= 90) {
            return true;
        }
        if (97 <= i && i <= 122) {
            return true;
        }
        if ((48 <= i && i <= 57) || i == 36 || i == 45 || i == 95) {
            return true;
        }
        if (161 <= i && i <= 8191) {
            return true;
        }
        if (8208 <= i && i <= 8231) {
            return true;
        }
        if (8240 <= i && i <= 55295) {
            return true;
        }
        if (57344 <= i && i < 65279) {
            return true;
        }
        if (65279 >= i || i > 65519) {
            return 65536 <= i && i <= 1114111;
        }
        return true;
    }

    public static boolean d(int i) {
        if (i == 32 || i == 160 || i == 5760) {
            return true;
        }
        return (8192 <= i && i <= 8202) || i == 8239 || i == 8287 || i == 12288;
    }
}
