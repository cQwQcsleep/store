package com.android.tools.r8.internal;

import com.android.tools.r8.naming.MapVersion;
import com.reandroid.arsc.chunk.TypeBlock;
import defpackage.f44;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3084y50 extends com.android.tools.r8.naming.mappinginformation.d {
    public static final MapVersion a = MapVersion.MAP_VERSION_2_2;

    public static void a(MapVersion mapVersion, C1898kD c1898kD, Consumer consumer) {
        if (a(mapVersion)) {
            AbstractC1643hD abstractC1643hDA = c1898kD.a("signature");
            if (abstractC1643hDA == null) {
                f44.a("Expected 'signature' to be present: ", c1898kD);
                return;
            }
            String strG = abstractC1643hDA.g();
            if (strG.contains("(")) {
                consumer.accept(C2999x50.a(strG));
            } else {
                consumer.accept(C2913w50.a(strG));
            }
        }
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final AbstractC3084y50 i() {
        return this;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean p() {
        return true;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final String r() {
        C1898kD c1898kD = new C1898kD();
        c1898kD.b.put(TypeBlock.NAME_id, new C2155nD("com.android.tools.r8.residualsignature"));
        c1898kD.b.put("signature", new C2155nD(t()));
        return c1898kD.toString();
    }

    public abstract boolean s();

    public abstract String t();

    public static boolean a(MapVersion mapVersion) {
        return mapVersion.a(a);
    }
}
