package com.android.tools.r8.naming.mappinginformation;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.AbstractC1643hD;
import com.android.tools.r8.internal.C1898kD;
import com.android.tools.r8.internal.C2155nD;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class a extends d {
    public final String a;

    public a(String str) {
        this.a = str;
    }

    public static void a(C1898kD c1898kD, DiagnosticsHandler diagnosticsHandler, int i, Consumer consumer) {
        try {
            AbstractC1643hD abstractC1643hD = (AbstractC1643hD) c1898kD.b.get("fileName");
            if (abstractC1643hD == null) {
                diagnosticsHandler.info(MappingInformationDiagnostics.a(i, "fileName", "sourceFile"));
            }
            if (abstractC1643hD != null) {
                consumer.accept(new a(abstractC1643hD.g()));
            }
        } catch (IllegalStateException | UnsupportedOperationException unused) {
            diagnosticsHandler.info(MappingInformationDiagnostics.a(i));
        }
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final e b(e eVar) {
        return eVar;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final String r() {
        C1898kD c1898kD = new C1898kD();
        c1898kD.b.put(TypeBlock.NAME_id, new C2155nD("sourceFile"));
        c1898kD.b.put("fileName", new C2155nD(this.a));
        return c1898kD.toString();
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(e eVar) {
        eVar.getClass();
        return !(eVar instanceof a);
    }

    public static a a(String str) {
        return new a(str);
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final a a() {
        return this;
    }
}
