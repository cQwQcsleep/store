package com.android.tools.r8.internal;

import defpackage.go7;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1689hl0 {
    public static void a(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws RB {
        if (!a(b2)) {
            if ((((b2 + 112) + (b << 28)) >> 30) == 0 && !a(b3) && !a(b4)) {
                int i2 = ((b & 7) << 18) | ((b2 & 63) << 12) | ((b3 & 63) << 6) | (b4 & 63);
                cArr[i] = (char) ((i2 >>> 10) + 55232);
                cArr[i + 1] = (char) ((i2 & 1023) + 56320);
                return;
            }
        }
        go7.a("Protocol message had invalid UTF-8.");
    }

    public static void a(byte b, byte b2, byte b3, char[] cArr, int i) throws RB {
        if (!a(b2) && ((b != -32 || b2 >= -96) && ((b != -19 || b2 < -96) && !a(b3)))) {
            cArr[i] = (char) (((b & 15) << 12) | ((b2 & 63) << 6) | (b3 & 63));
        } else {
            go7.a("Protocol message had invalid UTF-8.");
        }
    }

    public static boolean a(byte b) {
        return b > -65;
    }
}
