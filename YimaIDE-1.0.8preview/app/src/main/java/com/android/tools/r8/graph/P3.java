package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B3;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.naming.AbstractC3345r0;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class P3 implements V3 {
    public static final /* synthetic */ boolean d = true;
    public final AbstractC3345r0 a;
    public final Predicate b;
    public final StringBuilder c = new StringBuilder();

    public P3(AbstractC3345r0 abstractC3345r0, Predicate<I2> predicate) {
        this.a = abstractC3345r0;
        this.b = predicate;
    }

    public final void a(B3.e eVar, boolean z) {
        if (eVar.n()) {
            this.c.append("*");
            return;
        }
        if (eVar.o()) {
            StringBuilder sb = this.c;
            sb.append("T");
            sb.append(eVar.i().b);
            sb.append(";");
            return;
        }
        if (eVar.l()) {
            this.c.append("[");
            eVar.g().a(this);
            return;
        }
        boolean z2 = d;
        if (!z2 && !eVar.m()) {
            x1f.a();
            return;
        }
        B3.c cVarH = eVar.h();
        if (cVarH.a()) {
            return;
        }
        B3.c cVar = cVarH.d;
        if (cVar != null) {
            a((B3.e) cVar, true);
        }
        String string = this.a.c(cVarH.b).toString();
        B3.c cVar2 = cVarH.d;
        if (cVar2 == null) {
            StringBuilder sb2 = this.c;
            sb2.append("L");
            sb2.append(C0929Wj.f(string));
        } else {
            I2 i2 = cVar2.b;
            String strA = C0929Wj.a(this.a.c(i2).toString(), string);
            if (strA == null && this.b.test(cVarH.b)) {
                if (!z2 && !string.equals(cVarH.b.Z0())) {
                    x1f.a();
                    return;
                }
                strA = C0929Wj.a(i2.Z0(), string);
            }
            if (strA == null) {
                return;
            }
            StringBuilder sb3 = this.c;
            sb3.append(".");
            sb3.append(strA);
        }
        a(null, null, cVarH.c);
        if (z) {
            return;
        }
        this.c.append(";");
    }

    @Override // com.android.tools.r8.graph.V3
    public final List b(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            B3.i iVar = (B3.i) it.next();
            this.c.append("^");
            a(iVar);
        }
        return list;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List c(List list) {
        if (list.isEmpty()) {
            return list;
        }
        this.c.append("<");
        list.forEach(new Consumer() { // from class: nua
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((B3.f) obj);
            }
        });
        this.c.append(">");
        return list;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List d(List list) {
        this.c.append("(");
        list.forEach(new Consumer() { // from class: mua
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((B3.i) obj);
            }
        });
        this.c.append(")");
        return list;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List e(List list) {
        list.forEach(new Consumer() { // from class: pua
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b((B3.c) obj);
            }
        });
        return list;
    }

    public String toString() {
        return this.c.toString();
    }

    public B3.e b(B3.e eVar) {
        a(eVar, false);
        return eVar;
    }

    public final B3.c b(B3.c cVar) {
        a((B3.e) cVar, false);
        return cVar;
    }

    public final B3.e c(B3.e eVar) {
        this.c.append(":");
        a(eVar, false);
        return eVar;
    }

    public B3.g a(B3.g gVar) {
        return gVar.a(this);
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.h a(B3.h hVar) {
        if (hVar.a()) {
            this.c.append("V");
            return hVar;
        }
        a(hVar.a);
        return hVar;
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.e a(B3.e eVar) {
        this.c.append(":");
        if (eVar.a()) {
            return eVar;
        }
        a(eVar, false);
        return eVar;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List a(List list) {
        list.forEach(new Consumer() { // from class: oua
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c((B3.e) obj);
            }
        });
        return list;
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.c a(B3.c cVar) {
        if (cVar == null) {
            this.c.append("Ljava/lang/Object;");
            return cVar;
        }
        a((B3.e) cVar, false);
        return cVar;
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.i a(B3.i iVar) {
        iVar.getClass();
        if (iVar instanceof C3) {
            this.c.append(iVar.c().a.Z0());
            return iVar;
        }
        a(iVar.d(), false);
        return iVar;
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.c a(B3.c cVar, B3.c cVar2) {
        a((B3.e) cVar, true);
        return cVar;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List a(I2 i2, I2 i3, List list) {
        if (list.isEmpty()) {
            return list;
        }
        this.c.append("<");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            B3.e eVar = (B3.e) it.next();
            B3.k kVarJ = eVar.j();
            if (kVarJ != B3.k.c) {
                if (!d && kVarJ == B3.k.b) {
                    x1f.a();
                    return null;
                }
                this.c.append(kVarJ == B3.k.e ? "+" : "-");
            }
            a((B3.i) eVar);
        }
        this.c.append(">");
        return list;
    }

    public final B3.f a(B3.f fVar) {
        this.c.append(fVar.a);
        return fVar.a(this);
    }

    @Override // com.android.tools.r8.graph.V3
    public final I2 a(I2 i2) {
        return i2;
    }

    public B3.b a(B3.b bVar) {
        c(bVar.a);
        a(bVar.b);
        e(bVar.c);
        return bVar;
    }
}
