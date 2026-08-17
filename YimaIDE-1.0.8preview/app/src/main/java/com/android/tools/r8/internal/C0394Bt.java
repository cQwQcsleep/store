package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0394Bt {
    public final EnumC3077y2 a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final List f;

    public C0394Bt(EnumC3077y2 enumC3077y2, String str, String str2, String str3, boolean z, List list) {
        this.a = enumC3077y2;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = list;
    }

    public static C0368At a() {
        return new C0368At();
    }

    public static C0394Bt f() {
        EnumC3077y2 enumC3077y2 = EnumC3077y2.B;
        int i = AbstractC0551Hu.c;
        return new C0394Bt(enumC3077y2, "unused", "testing", null, true, P40.e);
    }

    public List<String> b() {
        return this.f;
    }

    public String c() {
        return this.c;
    }

    public EnumC3077y2 d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }
}
