package com.android.tools.r8.internal;

import java.util.HashSet;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ov, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2298ov extends AbstractC2469qv {
    public final HashSet c;

    public C2298ov(AbstractC2469qv abstractC2469qv) {
        super(abstractC2469qv);
        this.c = new HashSet(AbstractC1739iN.a(this.b));
        for (int i = 0; i < this.b; i++) {
            HashSet hashSet = this.c;
            Object obj = this.a[i];
            Objects.requireNonNull(obj);
            hashSet.add(obj);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2469qv
    public final AbstractC2554rv a() {
        int i = this.b;
        if (i == 0) {
            int i2 = AbstractC2554rv.c;
            return W40.j;
        }
        if (i != 1) {
            return new ZC(this.c, AbstractC0551Hu.b(this.b, this.a));
        }
        Object obj = this.a[0];
        Objects.requireNonNull(obj);
        int i3 = AbstractC2554rv.c;
        return new Cc0(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC2469qv
    public final AbstractC2469qv b() {
        return new C2298ov(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2469qv
    public final AbstractC2469qv a(Object obj) {
        obj.getClass();
        if (this.c.add(obj)) {
            b(obj);
        }
        return this;
    }
}
