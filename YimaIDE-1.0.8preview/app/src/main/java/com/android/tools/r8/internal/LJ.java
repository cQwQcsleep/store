package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class LJ implements Kh0 {
    public EnumC3077y2 a;
    public String c;
    public String d;
    public List f;
    public String b = "j$/";
    public boolean e = true;

    public final MJ a() {
        return new MJ(this.a, this.b, this.c, this.d, this.e, this.f);
    }

    public final void b(String str) {
        this.d = str;
    }

    public final void c(String str) {
        this.b = str.replace('.', DataResource.SEPARATOR);
    }

    public final void b(boolean z) {
        this.e = z;
    }

    public final void a(String str) {
        this.c = str;
    }

    @Override // com.android.tools.r8.internal.Kh0
    public final Kh0 a(boolean z) {
        this.e = z;
        return this;
    }

    public final void a(ArrayList arrayList) {
        this.f = arrayList;
    }

    public final void a(EnumC3077y2 enumC3077y2) {
        this.a = enumC3077y2;
    }
}
