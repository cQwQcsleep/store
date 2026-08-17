package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3027xT extends CT implements BU {
    public AT h;
    public final /* synthetic */ ET i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3027xT(ET et) {
        super(et);
        this.i = et;
    }

    @Override // java.util.Iterator
    public final Object next() {
        AT at = new AT(this.i, a());
        this.h = at;
        return at;
    }

    @Override // com.android.tools.r8.internal.CT, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
