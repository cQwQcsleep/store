package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.At, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0368At implements Kh0 {
    public static final /* synthetic */ boolean g = true;
    public EnumC3077y2 a;
    public String b;
    public String c;
    public String d;
    public Boolean e;
    public List f;

    public final C0394Bt a() {
        boolean z = g;
        if (!z && this.b == null) {
            x1f.a();
            return null;
        }
        if (z || this.e != null) {
            return new C0394Bt(this.a, this.b, this.c, this.d, this.e.booleanValue(), this.f);
        }
        x1f.a();
        return null;
    }

    public final void b(boolean z) {
        this.e = Boolean.valueOf(z);
    }

    public final void c(String str) {
        this.b = str.replace('.', DataResource.SEPARATOR);
    }

    public final void b(String str) {
        this.d = str;
    }

    public final void a(String str) {
        this.c = str;
    }

    @Override // com.android.tools.r8.internal.Kh0
    public final Kh0 a(boolean z) {
        this.e = Boolean.valueOf(z);
        return this;
    }

    public final void a(ArrayList arrayList) {
        this.f = arrayList;
    }

    public final void a(EnumC3077y2 enumC3077y2) {
        this.a = enumC3077y2;
    }
}
