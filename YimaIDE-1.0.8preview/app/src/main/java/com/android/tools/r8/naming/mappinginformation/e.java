package com.android.tools.r8.naming.mappinginformation;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.AbstractC1643hD;
import com.android.tools.r8.internal.AbstractC3084y50;
import com.android.tools.r8.internal.BW;
import com.android.tools.r8.internal.C0639Le;
import com.android.tools.r8.internal.C1898kD;
import com.android.tools.r8.internal.C2351pa0;
import com.android.tools.r8.internal.C2883vk0;
import com.android.tools.r8.internal.C2913w50;
import com.android.tools.r8.internal.C2999x50;
import com.android.tools.r8.internal.EV;
import com.android.tools.r8.internal.GV;
import com.android.tools.r8.naming.MapVersion;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class e {
    public static void a(MapVersion mapVersion, C1898kD c1898kD, DiagnosticsHandler diagnosticsHandler, int i, Consumer consumer) {
        AbstractC1643hD abstractC1643hD = (AbstractC1643hD) c1898kD.b.get(TypeBlock.NAME_id);
        if (abstractC1643hD == null) {
            diagnosticsHandler.info(MappingInformationDiagnostics.b(i));
        }
        String strG = abstractC1643hD.g();
        if (strG == null) {
            diagnosticsHandler.info(MappingInformationDiagnostics.c(i));
            return;
        }
        switch (strG) {
            case "com.android.tools.r8.outlineCallsite":
                EV.a(mapVersion, c1898kD, consumer);
                break;
            case "com.android.tools.r8.outline":
                GV.a(mapVersion, consumer);
                break;
            case "com.android.tools.r8.residualsignature":
                AbstractC3084y50.a(mapVersion, c1898kD, consumer);
                break;
            case "sourceFile":
                a.a(c1898kD, diagnosticsHandler, i, consumer);
                break;
            case "com.android.tools.r8.synthesized":
                C0639Le.a(mapVersion, consumer);
                break;
            case "com.android.tools.r8.mapping":
                b.a(c1898kD, i, consumer);
                break;
            case "com.android.tools.r8.rewriteFrame":
                C2351pa0.a(mapVersion, c1898kD, consumer);
                break;
            case "partitionSourceFiles":
                BW.a(c1898kD, consumer);
                break;
            default:
                diagnosticsHandler.info(MappingInformationDiagnostics.a(i, strG));
                C2883vk0.a(strG, c1898kD, consumer);
                break;
        }
    }

    public abstract boolean a(e eVar);

    public b b() {
        return null;
    }

    public abstract e b(e eVar);

    public EV c() {
        return null;
    }

    public GV d() {
        return null;
    }

    public BW e() {
        return null;
    }

    public d f() {
        return null;
    }

    public C2913w50 g() {
        return null;
    }

    public C2999x50 h() {
        return null;
    }

    public AbstractC3084y50 i() {
        return null;
    }

    public C2351pa0 j() {
        return null;
    }

    public C2883vk0 k() {
        return null;
    }

    public boolean l() {
        return false;
    }

    public boolean m() {
        return this instanceof EV;
    }

    public boolean n() {
        return this instanceof GV;
    }

    public boolean o() {
        return false;
    }

    public boolean p() {
        return false;
    }

    public boolean q() {
        return this instanceof C2351pa0;
    }

    public abstract String r();

    public a a() {
        return null;
    }

    public static void a(List list, e eVar, Consumer consumer) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            e eVar2 = (e) it.next();
            if (!eVar2.a(eVar)) {
                consumer.accept(eVar2);
                return;
            }
        }
        list.add(eVar);
    }
}
