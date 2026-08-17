package com.android.tools.r8.ir.regalloc;

import com.android.tools.r8.internal.AbstractC2624sj0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class m {
    public static final /* synthetic */ boolean d = true;
    public final AbstractC2624sj0 a;
    public c b;
    public final c c;

    public m(AbstractC2624sj0 abstractC2624sj0, c cVar, c cVar2) {
        this.a = abstractC2624sj0;
        this.c = cVar;
        this.b = cVar2;
        boolean z = d;
        if (!z && cVar.l == Integer.MIN_VALUE) {
            x1f.a();
            throw null;
        }
        if (z || cVar2.l != Integer.MIN_VALUE) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        if (this.a == mVar.a) {
            c cVar = this.b;
            int i = cVar.l;
            c cVar2 = mVar.b;
            if (i == cVar2.l) {
                c cVar3 = this.c;
                int i2 = cVar3.l;
                c cVar4 = mVar.c;
                if (i2 == cVar4.l && cVar.e == cVar2.e && cVar3.e == cVar4.e) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.c.l * 5) + (this.b.l * 3) + this.a.hashCode();
    }

    public final String toString() {
        return this.c.d() + " <- " + this.b.d() + " (" + this.a + ")";
    }
}
