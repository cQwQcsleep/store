package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Collection;
import java.util.LinkedList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1186bq extends NP {
    public static final /* synthetic */ boolean c = true;
    public final C0333y b;

    public C1186bq(C0333y c0333y) {
        this.b = c0333y;
    }

    @Override // com.android.tools.r8.internal.NP
    public final Collection a(C1868jt c1868jt) {
        if (this.b.o()) {
            c1868jt.b(this.b);
            c1868jt.a(this.b.U());
        } else {
            boolean z = c;
            if (!z && c1868jt.g()) {
                x1f.a();
                return null;
            }
            if (!z && c1868jt.e()) {
                x1f.a();
                return null;
            }
            c1868jt.b(this.b);
            C0698Nm c0698Nm = new C0698Nm();
            if (!C1868jt.f && c1868jt.e()) {
                x1f.a();
                return null;
            }
            c1868jt.e = c0698Nm;
        }
        boolean z2 = C2847vL.a;
        LinkedList linkedList = new LinkedList();
        linkedList.add(c1868jt);
        return linkedList;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "FinalizeMergeGroup";
    }
}
