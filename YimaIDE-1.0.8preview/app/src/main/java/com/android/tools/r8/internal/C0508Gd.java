package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0508Gd {
    public final List a;

    public C0508Gd(AbstractC0482Fd... abstractC0482FdArr) {
        this.a = Arrays.asList(abstractC0482FdArr);
    }

    public final boolean a(C0705Nt c0705Nt, IO io2, C0483Fe c0483Fe, Ch0 ch0) {
        Iterator it = this.a.iterator();
        boolean zD = false;
        while (it.hasNext()) {
            zD |= ((AbstractC0482Fd) it.next()).a(c0705Nt, io2, c0483Fe, ch0).a().d();
        }
        return zD;
    }

    public C0508Gd(ArrayList arrayList) {
        this.a = arrayList;
    }
}
