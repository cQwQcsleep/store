package com.android.tools.r8.internal;

import com.android.tools.r8.shaking.AbstractC3385e1;
import com.android.tools.r8.shaking.AbstractC3390f1;
import java.util.HashSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TE extends AbstractC1560gF {
    public static final TE b = new TE(BE.c);
    public static final TE c = new TE(BE.d);
    public static final TE d = new TE(BE.e);
    public static final /* synthetic */ boolean e = true;
    public final BE a;

    public TE(BE be) {
        if (e || be != null) {
            this.a = be;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1560gF
    public final void a(HashSet hashSet) {
        if ((this.a.b & 1) > 0) {
            hashSet.add(DE.c);
            hashSet.add(DE.d);
            hashSet.add(DE.e);
        }
        if ((this.a.b & 2) > 0) {
            hashSet.add(DE.f);
            hashSet.add(DE.g);
            hashSet.add(DE.h);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1560gF
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TE) {
            return this.a.equals(((TE) obj).a);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1560gF
    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC1560gF
    public final void a(RG rg) {
        rg.b.add(SG.f);
    }

    @Override // com.android.tools.r8.internal.AbstractC1560gF
    public final void a(AbstractC1645hF abstractC1645hF) {
        AbstractC3390f1 abstractC3390f1 = ((C2498rE) abstractC1645hF).a;
        AbstractC3385e1 abstractC3385e1 = abstractC3390f1.a;
        abstractC3385e1.d = false;
        abstractC3385e1.j();
        abstractC3390f1.o();
    }
}
