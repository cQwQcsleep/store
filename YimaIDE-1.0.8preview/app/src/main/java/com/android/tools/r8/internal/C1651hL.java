package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1651hL extends AbstractC1737iL {
    public static final /* synthetic */ boolean b = true;

    @Override // com.android.tools.r8.internal.AbstractC1737iL
    public final int a(int i, Object obj) {
        Integer num = (Integer) obj;
        if (b || num != null) {
            return i - num.intValue();
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1737iL
    public final Object a(int i, int i2) {
        return Integer.valueOf(i2 - i);
    }
}
