package com.android.tools.r8.ir.optimize;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B0 {
    public static final /* synthetic */ boolean d = true;
    public final int a;
    public final List b;
    public final List c;

    public B0(int i, List list, List list2) {
        this.a = i;
        this.b = list;
        this.c = list2;
        if (d || !z0.b(i)) {
            return;
        }
        if (list.isEmpty() || list2.isEmpty()) {
            x1f.a();
            throw null;
        }
    }
}
