package com.android.tools.r8.internal;

import java.util.BitSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2438qb0 extends AbstractC1279d0 {
    public final BitSet d;
    public final /* synthetic */ C2523rb0 e;

    public C2438qb0(C2523rb0 c2523rb0) {
        this.e = c2523rb0;
        this.d = new BitSet(c2523rb0.c.size());
    }

    @Override // com.android.tools.r8.internal.AbstractC1279d0
    public final Object a() {
        boolean zIsEmpty = this.d.isEmpty();
        BitSet bitSet = this.d;
        if (zIsEmpty) {
            bitSet.set(0, this.e.b);
        } else {
            int iNextSetBit = bitSet.nextSetBit(0);
            int iNextClearBit = this.d.nextClearBit(iNextSetBit);
            if (iNextClearBit == this.e.c.size()) {
                this.b = 3;
                return null;
            }
            int i = (iNextClearBit - iNextSetBit) - 1;
            this.d.set(0, i);
            this.d.clear(i, iNextClearBit);
            this.d.set(iNextClearBit);
        }
        return new C2353pb0(this, (BitSet) this.d.clone());
    }
}
