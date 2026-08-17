package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2003lW extends AbstractC3159z1 {
    public abstract AbstractC1917kW a(int i);

    public abstract AbstractC2003lW a(int i, AbstractC1917kW abstractC1917kW);

    @Override // com.android.tools.r8.internal.Vh0
    public final AbstractC3159z1 a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3159z1
    public final AbstractC3159z1 b(C0333y c0333y, AbstractC3159z1 abstractC3159z1) {
        AbstractC2003lW abstractC2003lW = (AbstractC2003lW) abstractC3159z1;
        if (this instanceof C1463f7) {
            return abstractC2003lW;
        }
        abstractC2003lW.getClass();
        if (abstractC2003lW instanceof C1463f7) {
            return this;
        }
        return ((this instanceof C3224zk0) || (abstractC2003lW instanceof C3224zk0)) ? C3224zk0.b : d().a(abstractC2003lW.d());
    }

    public UR d() {
        return null;
    }

    public abstract AbstractC2003lW e();
}
