package com.android.tools.r8.ir.regalloc;

import java.util.BitSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class l extends j {
    public static final /* synthetic */ boolean c = true;
    public final j a;
    public final BitSet b;

    public l(k kVar) {
        this.a = kVar;
        this.b = new BitSet(kVar.a);
    }

    @Override // com.android.tools.r8.ir.regalloc.j
    public final boolean a(int i, i iVar) {
        if (c || !this.b.get(i)) {
            return this.a.a(i, iVar);
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.ir.regalloc.j
    public final boolean b(int i) {
        return this.a.b(i) || this.b.get(i);
    }

    public final void c(int i) {
        this.b.set(i);
    }

    @Override // com.android.tools.r8.ir.regalloc.j
    public final int a(int i) {
        if (!c && this.b.get(i)) {
            x1f.a();
            return 0;
        }
        return this.a.a(i);
    }
}
