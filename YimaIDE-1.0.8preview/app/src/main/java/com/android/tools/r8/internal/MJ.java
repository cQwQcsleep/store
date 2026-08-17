package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class MJ {
    public final EnumC3077y2 a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final List f;

    public MJ(EnumC3077y2 enumC3077y2, String str, String str2, String str3, boolean z, List list) {
        this.a = enumC3077y2;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = list;
    }

    public static LJ a() {
        return new LJ();
    }

    public static MJ b() {
        EnumC3077y2 enumC3077y2 = EnumC3077y2.B;
        int i = AbstractC0551Hu.c;
        return new MJ(enumC3077y2, "j$/", "testing", null, true, P40.e);
    }
}
