package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1558gD extends AbstractC1643hD implements Iterable {
    public final ArrayList b = new ArrayList();

    @Override // com.android.tools.r8.internal.AbstractC1643hD
    public final boolean a() {
        return i().a();
    }

    @Override // com.android.tools.r8.internal.AbstractC1643hD
    public final int b() {
        return i().b();
    }

    @Override // com.android.tools.r8.internal.AbstractC1643hD
    public final long e() {
        return i().e();
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof C1558gD) && ((C1558gD) obj).b.equals(this.b);
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1643hD
    public final String g() {
        return i().g();
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final AbstractC1643hD i() {
        int size = this.b.size();
        if (size == 1) {
            return (AbstractC1643hD) this.b.get(0);
        }
        k2d.a(CX.a(size, "Array must have size 1, but has size "));
        return null;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.b.iterator();
    }

    public final int size() {
        return this.b.size();
    }
}
