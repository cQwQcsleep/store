package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ev, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1443ev extends AbstractC0655Lv {
    public final /* synthetic */ AbstractC1529fv e;

    public C1443ev(AbstractC1529fv abstractC1529fv) {
        this.e = abstractC1529fv;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof AbstractC1314dQ) {
            AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) obj;
            if (abstractC1314dQ.a() > 0 && this.e.b(abstractC1314dQ.b()) == abstractC1314dQ.a()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return this.e.e();
    }

    @Override // com.android.tools.r8.internal.AbstractC0655Lv
    public final Object get(int i) {
        return this.e.j(i);
    }

    @Override // com.android.tools.r8.internal.AbstractC2554rv, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.e.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.e.F().size();
    }
}
