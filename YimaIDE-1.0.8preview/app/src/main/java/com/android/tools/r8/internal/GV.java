package com.android.tools.r8.internal;

import com.android.tools.r8.naming.MapVersion;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GV extends com.android.tools.r8.naming.mappinginformation.d {
    public static final MapVersion a = MapVersion.MAP_VERSION_2_0;

    public static void a(MapVersion mapVersion, Consumer consumer) {
        if (mapVersion.a(a)) {
            consumer.accept(new GV());
        }
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final com.android.tools.r8.naming.mappinginformation.e b(com.android.tools.r8.naming.mappinginformation.e eVar) {
        return eVar;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final GV d() {
        return this;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final String r() {
        C1898kD c1898kD = new C1898kD();
        c1898kD.b.put(TypeBlock.NAME_id, new C2155nD("com.android.tools.r8.outline"));
        return c1898kD.toString();
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(com.android.tools.r8.naming.mappinginformation.e eVar) {
        return true;
    }
}
