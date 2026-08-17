package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class JC extends F {
    public static final JC f = new JC(0, 0, new Object[0]);
    public final Object[] d;
    public final int e;

    public JC(int i, int i2, Object[] objArr) {
        super(i, i2);
        this.d = objArr;
        this.e = 0;
    }

    @Override // com.android.tools.r8.internal.F
    public final Object a(int i) {
        return this.d[this.e + i];
    }
}
