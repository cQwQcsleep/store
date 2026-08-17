package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1898kD extends AbstractC1643hD {
    public final BK b = new BK(false);

    public final void a(String str, String str2) {
        a(str, str2 == null ? C1813jD.b : new C2155nD(str2));
    }

    public final boolean b(String str) {
        return this.b.containsKey(str);
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof C1898kD) && ((C1898kD) obj).b.equals(this.b);
        }
        return true;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final C2932wK i() {
        return (C2932wK) this.b.entrySet();
    }

    public final C1898kD j() {
        return (C1898kD) this.b.get("positions");
    }

    public final void a(String str, AbstractC1643hD abstractC1643hD) {
        BK bk = this.b;
        if (abstractC1643hD == null) {
            abstractC1643hD = C1813jD.b;
        }
        bk.put(str, abstractC1643hD);
    }

    public final AbstractC1643hD a(String str) {
        return (AbstractC1643hD) this.b.get(str);
    }
}
