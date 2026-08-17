package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.q40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2399q40 implements InterfaceC2801um0 {
    public final /* synthetic */ C2569s40 a;

    public C2399q40(C2569s40 c2569s40) {
        this.a = c2569s40;
    }

    public final void a(String str) {
        if (str.length() <= 0 || str.length() > 80) {
            return;
        }
        this.a.b.d.add(str);
        this.a.b.f = true;
    }

    public final void b(String str) {
        KB.c(str, "<this>");
        int iA = AbstractC1679hg0.a((CharSequence) str, DataResource.SEPARATOR, false, 2);
        C2569s40 c2569s40 = this.a;
        List listB = iA >= 0 ? c2569s40.b.b.b(str) : c2569s40.b.b.a(U50.RAW, AbstractC1679hg0.b(str, '.'));
        if (listB.isEmpty()) {
            a(str);
            return;
        }
        C2569s40 c2569s41 = this.a;
        Iterator it = listB.iterator();
        while (it.hasNext()) {
            c2569s41.d.a((W50) it.next());
        }
    }
}
