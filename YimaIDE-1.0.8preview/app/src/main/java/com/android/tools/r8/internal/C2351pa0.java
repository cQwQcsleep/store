package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1643hD;
import com.android.tools.r8.internal.C2179na0;
import com.android.tools.r8.internal.C2265oa0;
import com.android.tools.r8.internal.C2351pa0;
import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.naming.MappingComposeException;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pa0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2351pa0 extends com.android.tools.r8.naming.mappinginformation.c {
    public static final MapVersion c = MapVersion.MAP_VERSION_2_0;
    public final List a;
    public final List b;

    public C2351pa0(List list, List list2) {
        this.a = list;
        this.b = list2;
    }

    public static void a(MapVersion mapVersion, C1898kD c1898kD, Consumer consumer) {
        if (mapVersion.a(c)) {
            final C0473Eu c0473EuG = AbstractC0551Hu.g();
            ((AbstractC1643hD) c1898kD.b.get("conditions")).c().forEach(new Consumer() { // from class: o0i
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    c0473EuG.a(C2265oa0.a((AbstractC1643hD) obj));
                }
            });
            final C0473Eu c0473EuG2 = AbstractC0551Hu.g();
            ((AbstractC1643hD) c1898kD.b.get("actions")).c().forEach(new Consumer() { // from class: p0i
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    c0473EuG2.a(C2179na0.a((AbstractC1643hD) obj));
                }
            });
            consumer.accept(new C2351pa0(c0473EuG.a(), c0473EuG2.a()));
        }
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final C2351pa0 j() {
        return this;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final String r() {
        C1898kD c1898kD = new C1898kD();
        c1898kD.b.put(TypeBlock.NAME_id, new C2155nD("com.android.tools.r8.rewriteFrame"));
        final C1558gD c1558gD = new C1558gD();
        this.a.forEach(new Consumer() { // from class: q0i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2351pa0.a(c1558gD, (C2265oa0) obj);
            }
        });
        c1898kD.b.put("conditions", c1558gD);
        final C1558gD c1558gD2 = new C1558gD();
        this.b.forEach(new Consumer() { // from class: r0i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2351pa0.a(c1558gD2, (C2179na0) obj);
            }
        });
        c1898kD.b.put("actions", c1558gD2);
        return c1898kD.toString();
    }

    public final List s() {
        return this.a;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final com.android.tools.r8.naming.mappinginformation.e b(com.android.tools.r8.naming.mappinginformation.e eVar) throws MappingComposeException {
        throw new MappingComposeException("Unable to compose com.android.tools.r8.rewriteFrame");
    }

    public static void a(C1558gD c1558gD, C2179na0 c2179na0) {
        c1558gD.b.add(c2179na0.a());
    }

    public static void a(C1558gD c1558gD, C2265oa0 c2265oa0) {
        c1558gD.b.add(c2265oa0.a());
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(com.android.tools.r8.naming.mappinginformation.e eVar) {
        eVar.getClass();
        return !(eVar instanceof C2351pa0);
    }
}
