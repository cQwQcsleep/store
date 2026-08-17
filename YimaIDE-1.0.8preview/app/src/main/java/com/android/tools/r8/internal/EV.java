package com.android.tools.r8.internal;

import com.android.tools.r8.internal.EV;
import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.naming.MappingComposeException;
import com.android.tools.r8.references.MethodReference;
import com.reandroid.arsc.chunk.TypeBlock;
import defpackage.f44;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EV extends com.android.tools.r8.naming.mappinginformation.c {
    public static final MapVersion c = MapVersion.MAP_VERSION_2_0;
    public static final MapVersion d = MapVersion.MAP_VERSION_2_1;
    public C2214nx a;
    public MethodReference b;

    public EV(C2214nx c2214nx, MethodReference methodReference) {
        this.a = c2214nx;
        this.b = methodReference;
    }

    public static void a(MapVersion mapVersion, C1898kD c1898kD, Consumer consumer) {
        MethodReference methodReferenceA;
        if (mapVersion.a(c)) {
            C1898kD c1898kDJ = c1898kD.j();
            if (c1898kDJ == null) {
                f44.a("Expected 'positions' to be present: ", c1898kD);
                return;
            }
            final C2214nx c2214nx = new C2214nx();
            c1898kDJ.i().forEach(new Consumer() { // from class: h44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    EV.a(c2214nx, (Map.Entry) obj);
                }
            });
            AbstractC1643hD abstractC1643hDA = c1898kD.a("outline");
            if (abstractC1643hDA != null) {
                methodReferenceA = MO.a(abstractC1643hDA.g());
            } else {
                if (mapVersion.a(d)) {
                    f44.a("Expected 'outline' to be present: ", c1898kD);
                    return;
                }
                methodReferenceA = null;
            }
            consumer.accept(new EV(c2214nx, methodReferenceA));
        }
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final com.android.tools.r8.naming.mappinginformation.e b(com.android.tools.r8.naming.mappinginformation.e eVar) throws MappingComposeException {
        throw new MappingComposeException("Unable to compose com.android.tools.r8.outlineCallsite");
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final EV c() {
        return this;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final String r() {
        C1898kD c1898kD = new C1898kD();
        c1898kD.b.put(TypeBlock.NAME_id, new C2155nD("com.android.tools.r8.outlineCallsite"));
        final C1898kD c1898kD2 = new C1898kD();
        this.a.forEach(new BiConsumer() { // from class: g44
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                EV.a(c1898kD2, (Integer) obj, (Integer) obj2);
            }
        });
        c1898kD.b.put("positions", c1898kD2);
        MethodReference methodReference = this.b;
        if (methodReference != null) {
            c1898kD.b.put("outline", new C2155nD(methodReference.toString()));
        }
        return c1898kD.toString();
    }

    public final MethodReference s() {
        return this.b;
    }

    public final C2214nx t() {
        return this.a;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(com.android.tools.r8.naming.mappinginformation.e eVar) {
        eVar.getClass();
        return !(eVar instanceof EV);
    }

    public static /* synthetic */ void a(C1898kD c1898kD, Integer num, Integer num2) {
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        c1898kD.a(sb.toString(), new C2155nD(num2));
    }

    public static /* synthetic */ void a(C2214nx c2214nx, Map.Entry entry) {
        try {
            c2214nx.b(Integer.parseInt((String) entry.getKey()), ((AbstractC1643hD) entry.getValue()).b());
        } catch (Throwable unused) {
            throw new C0613Ke("Invalid position entry: " + entry.toString());
        }
    }
}
