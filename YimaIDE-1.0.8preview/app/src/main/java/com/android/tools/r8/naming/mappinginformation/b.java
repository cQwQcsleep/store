package com.android.tools.r8.naming.mappinginformation;

import com.android.tools.r8.internal.AbstractC1643hD;
import com.android.tools.r8.internal.C1898kD;
import com.android.tools.r8.internal.C2155nD;
import com.android.tools.r8.naming.MapVersion;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class b extends e {
    public static final /* synthetic */ boolean c = true;
    public final MapVersion a;
    public final String b;

    public b(MapVersion mapVersion, String str) {
        this.a = mapVersion;
        this.b = str;
    }

    public static void a(C1898kD c1898kD, int i, Consumer consumer) {
        String strG = ((AbstractC1643hD) c1898kD.b.get("version")).g();
        if (strG == null) {
            MappingInformationDiagnostics.a(i, "version", "com.android.tools.r8.mapping");
            return;
        }
        MapVersion mapVersionFromName = MapVersion.fromName(strG);
        if (mapVersionFromName == null) {
            mapVersionFromName = MapVersion.MAP_VERSION_UNKNOWN;
        }
        consumer.accept(new b(mapVersionFromName, strG));
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final e b(e eVar) {
        if (!c) {
            eVar.getClass();
            if (!(eVar instanceof b)) {
                x1f.a();
                return null;
            }
        }
        return this.a.b(eVar.b().s()) ? eVar : this;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final String r() {
        C1898kD c1898kD = new C1898kD();
        c1898kD.b.put(TypeBlock.NAME_id, new C2155nD("com.android.tools.r8.mapping"));
        c1898kD.b.put("version", new C2155nD(this.a.getName()));
        return c1898kD.toString();
    }

    public MapVersion s() {
        return this.a;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final b b() {
        return this;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(e eVar) {
        return true;
    }
}
