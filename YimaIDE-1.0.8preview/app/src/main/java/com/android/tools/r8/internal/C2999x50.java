package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import defpackage.oyg;
import java.util.Arrays;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2999x50 extends AbstractC3084y50 {
    public static final C2999x50 d = new C2999x50("LINVALID;", new String[0]);
    public static final /* synthetic */ boolean e = true;
    public final String b;
    public final String[] c;

    public C2999x50(String str, String[] strArr) {
        if (!e && !C0929Wj.A(str) && !C0929Wj.H(str)) {
            x1f.a();
            throw null;
        }
        this.b = str;
        this.c = strArr;
    }

    public static C2999x50 a(String str) {
        String[] strArrE = C0929Wj.e(str);
        String strU = C0929Wj.u(str);
        boolean zA = C0929Wj.A(strU) || C0929Wj.H(strU);
        for (String str2 : strArrE) {
            zA &= C0929Wj.A(str2);
        }
        return zA ? new C2999x50(strU, strArrE) : d;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final com.android.tools.r8.naming.mappinginformation.e b(com.android.tools.r8.naming.mappinginformation.e eVar) {
        if (e) {
            return this;
        }
        eVar.getClass();
        if (eVar instanceof C2999x50) {
            return this;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final C2999x50 h() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3084y50
    public final boolean s() {
        return this != d;
    }

    @Override // com.android.tools.r8.internal.AbstractC3084y50
    public final String t() {
        return Wf0.a(XmlPullParser.NO_NAMESPACE, Arrays.asList(this.c), Wf0.a.b) + this.b;
    }

    public static C2999x50 a(C0322w2 c0322w2) {
        return new C2999x50(c0322w2.D0().Z0(), R3.a((Object[]) c0322w2.B0().b, (Function) new oyg()));
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(com.android.tools.r8.naming.mappinginformation.e eVar) {
        eVar.getClass();
        if (!(eVar instanceof C2999x50)) {
            return true;
        }
        C2999x50 c2999x50H = eVar.h();
        return this.b.equals(c2999x50H.b) && Arrays.equals(this.c, c2999x50H.c);
    }
}
