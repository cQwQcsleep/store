package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.z1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3159z1 implements Cloneable, Vh0 {
    public boolean a(C0333y c0333y, AbstractC3159z1 abstractC3159z1) {
        return equals(b(c0333y, abstractC3159z1));
    }

    public abstract AbstractC3159z1 b(C0333y c0333y, AbstractC3159z1 abstractC3159z1);

    @Override // com.android.tools.r8.internal.Vh0
    public final boolean c() {
        return true;
    }

    public abstract boolean equals(Object obj);

    @Override // 
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AbstractC3159z1 mo16clone() {
        return a();
    }
}
