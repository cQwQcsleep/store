package com.android.tools.r8.internal;

import java.util.Arrays;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ng, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2190ng {
    public final String a;
    public final String b;
    public final C0497Fs c;
    public final Object[] d;

    public C2190ng(String str, String str2, C0497Fs c0497Fs, Object... objArr) {
        this.a = str;
        this.b = str2;
        this.c = c0497Fs;
        this.d = objArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2190ng)) {
            return false;
        }
        C2190ng c2190ng = (C2190ng) obj;
        return this.a.equals(c2190ng.a) && this.b.equals(c2190ng.b) && this.c.equals(c2190ng.c) && Arrays.equals(this.d, c2190ng.d);
    }

    public final int hashCode() {
        return Integer.rotateLeft(Arrays.hashCode(this.d), 24) ^ ((this.a.hashCode() ^ Integer.rotateLeft(this.b.hashCode(), 8)) ^ Integer.rotateLeft(this.c.hashCode(), 16));
    }

    public final String toString() {
        return this.a + " : " + this.b + " " + this.c + " " + Arrays.toString(this.d);
    }
}
