package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0546Hp extends AbstractC2205no {
    public static final C0546Hp b = new C0546Hp();
    public final boolean a = false;

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final boolean a(Object obj, Object obj2) {
        C0245l1 c0245l1 = (C0245l1) obj;
        C0245l1 c0245l2 = (C0245l1) obj2;
        if (this.a) {
            return c0245l1.i.equals(c0245l2.i);
        }
        return c0245l1.g.equals(c0245l2.g) && c0245l1.i.equals(c0245l2.i);
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final int a(Object obj) {
        C0245l1 c0245l1 = (C0245l1) obj;
        if (this.a) {
            return c0245l1.i.hashCode();
        }
        return c0245l1.i.hashCode() + (c0245l1.g.hashCode() * 31);
    }
}
