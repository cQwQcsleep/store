package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1485fQ extends AbstractC1314dQ implements Serializable {
    public final Object b;
    public final int c;

    public C1485fQ(int i, Object obj) {
        this.b = obj;
        this.c = i;
        AbstractC0871Ud.a(i, "count");
    }

    @Override // com.android.tools.r8.internal.AbstractC1314dQ
    public final int a() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1314dQ
    public final Object b() {
        return this.b;
    }

    public C1485fQ c() {
        return null;
    }
}
