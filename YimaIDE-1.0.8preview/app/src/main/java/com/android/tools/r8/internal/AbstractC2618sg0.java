package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2618sg0 extends AbstractC2925wD {
    public static final /* synthetic */ boolean l = true;
    public final int[] j;
    public int k;

    public AbstractC2618sg0(C2543rl0 c2543rl0, int[] iArr, int i) {
        super(c2543rl0);
        this.j = iArr;
        this.k = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean B2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2925wD
    public final H5 K2() {
        return i().t().get(this.k);
    }

    public int L2() {
        return this.j.length;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2618sg0 M0() {
        return this;
    }

    public final H5 b(int i) {
        return i().t().get(this.j[i]);
    }

    @Override // com.android.tools.r8.internal.AbstractC2925wD
    public final void b(H5 h5) {
        i().m().set(this.k, h5);
    }
}
