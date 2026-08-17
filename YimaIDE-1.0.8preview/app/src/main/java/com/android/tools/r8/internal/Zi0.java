package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Zi0 implements Ai0 {
    public final /* synthetic */ Class b;
    public final /* synthetic */ AbstractC3220zi0 c;

    public Zi0(Class cls, AbstractC3220zi0 abstractC3220zi0) {
        this.b = cls;
        this.c = abstractC3220zi0;
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        if (fj0.a == this.b) {
            return this.c;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.b.getName() + ",adapter=" + this.c + "]";
    }
}
