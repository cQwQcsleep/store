package com.android.tools.r8.ir.desugar.desugaredlibrary.lint;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.P40;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class l {
    public static final l e = new l();
    public final boolean a;
    public final boolean b;
    public final List c;
    public final AbstractC0551Hu d;

    public l(boolean z, ArrayList arrayList, ArrayList arrayList2) {
        this.a = false;
        this.b = z;
        arrayList.sort(Comparator.naturalOrder());
        this.c = arrayList;
        arrayList2.sort(Comparator.naturalOrder());
        this.d = AbstractC0551Hu.a(arrayList2);
    }

    public final List a() {
        return this.c;
    }

    public final List b() {
        return this.d;
    }

    public final boolean c() {
        return this.a;
    }

    public final boolean d() {
        return this.b;
    }

    public l() {
        this.a = true;
        this.b = false;
        int i = AbstractC0551Hu.c;
        P40 p40 = P40.e;
        this.c = p40;
        this.d = p40;
    }
}
