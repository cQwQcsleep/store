package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1343dj0 implements Ai0 {
    public final /* synthetic */ Class b;
    public final /* synthetic */ AbstractC3220zi0 c;

    public C1343dj0(Class cls, AbstractC3220zi0 abstractC3220zi0) {
        this.b = cls;
        this.c = abstractC3220zi0;
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        Class<?> cls = fj0.a;
        if (this.b.isAssignableFrom(cls)) {
            return new C1259cj0(this, cls);
        }
        return null;
    }

    public final String toString() {
        return "Factory[typeHierarchy=" + this.b.getName() + ",adapter=" + this.c + "]";
    }
}
