package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1090aj0 implements Ai0 {
    public final /* synthetic */ Class b;
    public final /* synthetic */ Class c;
    public final /* synthetic */ AbstractC3220zi0 d;

    public C1090aj0(Class cls, Class cls2, AbstractC3220zi0 abstractC3220zi0) {
        this.b = cls;
        this.c = cls2;
        this.d = abstractC3220zi0;
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        Class cls = fj0.a;
        if (cls == this.b || cls == this.c) {
            return this.d;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.c.getName() + "+" + this.b.getName() + ",adapter=" + this.d + "]";
    }
}
