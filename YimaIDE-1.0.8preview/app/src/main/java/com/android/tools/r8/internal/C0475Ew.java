package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import defpackage.cd4;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ew, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0475Ew {
    public static final /* synthetic */ boolean f = true;
    public final C0333y a;
    public final AbstractC2647t1 b;
    public int c;
    public final ArrayList d;
    public final C1441et e;

    public C0475Ew(C0333y c0333y, AbstractC2647t1 abstractC2647t1, C1441et c1441et) {
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        this.a = c0333y;
        this.b = abstractC2647t1;
        this.e = c1441et;
        this.c = 0;
        arrayList.add(new ArrayList());
    }

    public final List a(final C1868jt c1868jt) {
        if (f || this.d.stream().noneMatch(new cd4())) {
            return C2847vL.a((Collection) this.d, new Function() { // from class: ed4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a(c1868jt, (List) obj);
                }
            });
        }
        x1f.a();
        return null;
    }

    public final C0501Fw a(C1868jt c1868jt, List list) {
        return new C0501Fw(this.a, this.b, c1868jt, list, this.e, null);
    }

    public final List a(final C1868jt c1868jt, final C0397Bw c0397Bw) {
        if (f || this.d.stream().noneMatch(new cd4())) {
            return C2847vL.a((Collection) this.d, new Function() { // from class: dd4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a(c1868jt, c0397Bw, (List) obj);
                }
            });
        }
        x1f.a();
        return null;
    }

    public final /* synthetic */ C0501Fw a(C1868jt c1868jt, C0397Bw c0397Bw, List list) {
        return new C0501Fw(this.a, this.b, c1868jt, list, this.e, c0397Bw);
    }
}
