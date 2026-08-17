package com.android.tools.r8.naming;

import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.position.Position;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: renamed from: com.android.tools.r8.naming.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3329j implements InterfaceC3325h {
    public final String a;
    public final String b;
    public final Position c;
    public final AbstractC0706Nu d;
    public final AbstractC0706Nu e;
    public final AbstractC0706Nu f;

    public C3329j(String str, String str2, F0 f0, HashMap map, HashMap map2, HashMap map3) {
        this.b = str;
        this.a = str2;
        this.c = f0;
        this.d = AbstractC0706Nu.a(map);
        this.e = AbstractC0706Nu.a(map2);
        this.f = AbstractC0706Nu.a(map3);
    }

    @Override // com.android.tools.r8.naming.InterfaceC3325h
    public final V a(V.c cVar) {
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3329j)) {
            return false;
        }
        C3329j c3329j = (C3329j) obj;
        return this.a.equals(c3329j.a) && this.b.equals(c3329j.b) && this.d.equals(c3329j.d) && this.e.equals(c3329j.e) && this.f.equals(c3329j.f);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.d, this.e, this.f});
    }
}
