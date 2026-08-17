package com.android.tools.r8.internal;

import com.android.tools.r8.internal.BW;
import com.android.tools.r8.naming.MappingComposeException;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BW extends com.android.tools.r8.naming.mappinginformation.d {
    public final Map a;

    public BW(HashMap map) {
        this.a = map;
    }

    public static void a(C1898kD c1898kD, Consumer consumer) {
        C1898kD c1898kD2 = (C1898kD) c1898kD.b.get("fileNameMappings");
        final AW aw = new AW();
        c1898kD2.b.entrySet().forEach(new Consumer() { // from class: ol0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BW.a(aw, (Map.Entry) obj);
            }
        });
        consumer.accept(new BW(aw.a));
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final com.android.tools.r8.naming.mappinginformation.e b(com.android.tools.r8.naming.mappinginformation.e eVar) throws MappingComposeException {
        throw new MappingComposeException("Unable to compose partitionSourceFiles");
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final BW e() {
        return this;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final String r() {
        C1898kD c1898kD = new C1898kD();
        c1898kD.b.put(TypeBlock.NAME_id, new C2155nD("partitionSourceFiles"));
        final C1898kD c1898kD2 = new C1898kD();
        this.a.forEach(new BiConsumer() { // from class: pl0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c1898kD2.a((String) obj, (String) obj2);
            }
        });
        c1898kD.b.put("fileNameMappings", c1898kD2);
        return c1898kD.toString();
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(com.android.tools.r8.naming.mappinginformation.e eVar) {
        eVar.getClass();
        return !(eVar instanceof BW);
    }

    public static void a(AW aw, Map.Entry entry) {
        aw.a.put((String) entry.getKey(), ((AbstractC1643hD) entry.getValue()).g());
    }
}
