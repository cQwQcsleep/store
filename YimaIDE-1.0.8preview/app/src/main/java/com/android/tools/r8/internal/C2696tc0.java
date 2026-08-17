package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2696tc0 extends AbstractC2005lY {
    public static final C2696tc0 c = new C2696tc0();

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public boolean K() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2005lY
    public String P() {
        throw new Kk0("Unexpected attempt to get descriptor of " + this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2005lY
    public String Q() {
        throw new Kk0("Unexpected attempt to get type name of " + this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public int hashCode() {
        return System.identityHashCode(c);
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public String toString() {
        return "SINGLE";
    }
}
