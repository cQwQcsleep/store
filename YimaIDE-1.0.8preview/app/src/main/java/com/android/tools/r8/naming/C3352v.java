package com.android.tools.r8.naming;

import com.android.tools.r8.internal.AbstractC3084y50;
import com.android.tools.r8.internal.C1242cb;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.naming.C3352v;
import com.android.tools.r8.naming.r;
import defpackage.hn9;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.naming.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3352v {
    public static final N0 e = new N0(0, 0, false);
    public com.android.tools.r8.naming.mappinginformation.b a = null;
    public final C3346s b = new C3346s();
    public C3346s c;
    public final C2752uB d;

    public C3352v(C2752uB c2752uB) {
        this.d = c2752uB;
    }

    public final void a(C3313b c3313b) throws Throwable {
        this.c = new C3346s();
        com.android.tools.r8.naming.mappinginformation.b bVarC = c3313b.c();
        if (bVarC == null) {
            throw new MappingComposeException("Composition of mapping files supported from map version 2.2.");
        }
        MapVersion mapVersionS = bVarC.s();
        if (!AbstractC3084y50.a(mapVersionS) || mapVersionS.isUnknown()) {
            throw new MappingComposeException("Composition of mapping files supported from map version " + AbstractC3084y50.a.getName() + ".");
        }
        com.android.tools.r8.naming.mappinginformation.b bVar = this.a;
        if (bVar == null) {
            this.a = bVarC;
        } else {
            this.a = bVarC.b(bVar).b();
        }
        Iterator it = c3313b.b().values().iterator();
        while (it.hasNext()) {
            a(c3313b, (C3331k) it.next());
        }
        this.b.a(this.c, c3313b);
    }

    public final void a(C3313b c3313b, C3331k c3331k) throws Throwable {
        String str = c3331k.a;
        String str2 = c3331k.b;
        r rVar = new r(str, str2, this.b, this.c, this.d);
        r rVar2 = (r) this.c.a.put(str2, rVar);
        if (rVar2 == null) {
            rVar.a(c3313b, c3331k);
            return;
        }
        throw new MappingComposeException("Duplicate class mapping. Both '" + rVar2.a() + "' and '" + str + "' maps to '" + str2 + "'.");
    }

    public final String a() {
        ArrayList arrayList = new ArrayList(this.b.a.values());
        arrayList.sort(Comparator.comparing(new Function() { // from class: aii
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((r) obj).a();
            }
        }));
        final StringBuilder sb = new StringBuilder();
        this.b.f.forEach(new Consumer() { // from class: eii
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3352v.a(sb, (String) obj);
            }
        });
        if (this.a != null) {
            sb.append("# ");
            sb.append(this.a.r());
            sb.append("\n");
        }
        C1242cb c1242cb = new C1242cb(new hn9(sb));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((r) it.next()).a(c1242cb);
        }
        return sb.toString();
    }

    public static /* synthetic */ void a(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\n");
    }
}
