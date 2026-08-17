package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2610sc0 extends AbstractC3208zc0 implements InterfaceC1750iY, InterfaceC2355pc0 {
    @Override // com.android.tools.r8.internal.AbstractC3167z5, com.android.tools.r8.internal.InterfaceC1101ar
    public final AbstractC2610sc0 A() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3167z5, com.android.tools.r8.internal.InterfaceC1101ar
    public final boolean C() {
        return true;
    }

    public boolean K() {
        return this instanceof J6;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2355pc0
    public final InterfaceC2355pc0 a(C0333y c0333y, InterfaceC2355pc0 interfaceC2355pc0) {
        if (this == interfaceC2355pc0) {
            return this;
        }
        return (K() && interfaceC2355pc0.isPrimitive() && interfaceC2355pc0.A().K()) ? C1469fA.c : C1490fV.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC3167z5, com.android.tools.r8.internal.InterfaceC1101ar
    public final InterfaceC1750iY asPrimitive() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3167z5, com.android.tools.r8.internal.InterfaceC1101ar
    public final boolean isPrimitive() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3167z5, com.android.tools.r8.internal.InterfaceC1101ar
    public final InterfaceC2355pc0 q() {
        return this;
    }

    public final String toString() {
        return getTypeName();
    }

    @Override // com.android.tools.r8.internal.AbstractC3167z5, com.android.tools.r8.internal.InterfaceC1101ar
    public final boolean x() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3167z5, com.android.tools.r8.internal.InterfaceC1101ar
    public final BX y() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3167z5, com.android.tools.r8.internal.InterfaceC1101ar
    public final boolean a() {
        return true;
    }
}
