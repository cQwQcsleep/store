package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3130yg0 {
    public final int a;
    public final int b;
    public final String c;
    public final String d;
    public final String e;
    public final long f;
    public int g;
    public final int h;
    public C3130yg0 i;

    public C3130yg0(int i, int i2, String str, String str2, String str3, long j) {
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = j;
    }

    public C3130yg0(int i, int i2, String str, String str2, String str3, long j, int i3) {
        this(i, i2, str, str2, str3, j);
        this.h = i3;
    }

    public C3130yg0(int i, int i2, int i3, String str) {
        this(i, i2, null, null, str, 0L);
        this.h = i3;
    }

    public C3130yg0(int i, int i2, String str, long j, int i3) {
        this(i, i2, null, null, str, j);
        this.h = i3;
    }

    public C3130yg0(int i, int i2, String str, String str2) {
        this(i, 12, null, str, str2, 0L);
        this.h = i2;
    }

    public C3130yg0(int i, int i2, long j, int i3) {
        this(i, i2, null, null, null, j);
        this.h = i3;
    }
}
