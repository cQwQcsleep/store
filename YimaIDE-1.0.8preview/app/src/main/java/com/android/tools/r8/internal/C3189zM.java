package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3189zM {
    public static final /* synthetic */ boolean g = true;
    public final EnumC3077y2 a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final List f;

    public C3189zM(EnumC3077y2 enumC3077y2, String str, String str2, String str3, boolean z, List list) {
        this.a = enumC3077y2;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = list;
    }

    public final C3189zM a(String str) {
        if (!g && !str.endsWith(String.valueOf('.'))) {
            x1f.a();
            return null;
        }
        String strN = C0929Wj.n(this.b);
        String str2 = strN + C0929Wj.n(str);
        String strO = C0929Wj.o(str2);
        ArrayList arrayList = new ArrayList(this.f.size());
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            arrayList.add(((String) it.next()).replace(strN, str2));
        }
        return new C3189zM(this.a, strO, this.c, this.d, this.e, arrayList);
    }

    public final String b() {
        return String.join("\n", this.f);
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

    public List<String> a() {
        return this.f;
    }
}
