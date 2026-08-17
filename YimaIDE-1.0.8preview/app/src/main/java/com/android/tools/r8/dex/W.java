package com.android.tools.r8.dex;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.AbstractC1643hD;
import com.android.tools.r8.internal.C1558gD;
import com.android.tools.r8.internal.C1898kD;
import com.android.tools.r8.internal.C2070mD;
import com.android.tools.r8.internal.C2155nD;
import com.android.tools.r8.internal.C2413qD;
import com.android.tools.r8.internal.Wf0;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class W {
    public static final String c = "~~" + b.b + "{";
    public static final String d = "~~" + b.e + "{";
    public static final String e = "~~" + b.d + "{";
    public static final String f = "~~" + b.f + "{";
    public static final /* synthetic */ boolean g = true;
    public final C1898kD a;
    public final b b;

    public enum a {
        b,
        c;

        a() {
        }
    }

    public enum b {
        b,
        c,
        d,
        e,
        f,
        g;

        b() {
        }

        public static b[] a() {
            return new b[]{b, e};
        }
    }

    public W(b bVar) {
        C1898kD c1898kD = new C1898kD();
        this.b = bVar;
        this.a = c1898kD;
    }

    public static W a(H2 h2) {
        if (!a(h2.f)) {
            return null;
        }
        String string = h2.toString();
        String str = c;
        if (string.startsWith(str)) {
            return a(b.b, string.substring(str.length() - 1));
        }
        String str2 = d;
        if (string.startsWith(str2)) {
            return a(b.e, string.substring(str2.length() - 1));
        }
        String str3 = e;
        if (string.startsWith(str3)) {
            return a(b.d, string.substring(str3.length() - 1));
        }
        String str4 = f;
        if (string.startsWith(str4)) {
            return a(b.f, string.substring(str4.length() - 1));
        }
        return null;
    }

    public String b() {
        if (this.a.b.containsKey("compilation-mode")) {
            return ((AbstractC1643hD) this.a.b.get("compilation-mode")).g();
        }
        return null;
    }

    public String[] c() {
        if (!this.a.b.containsKey("desugared-library-identifiers")) {
            return new String[0];
        }
        C1558gD c1558gDC = ((AbstractC1643hD) this.a.b.get("desugared-library-identifiers")).c();
        String[] strArr = new String[c1558gDC.b.size()];
        for (int i = 0; i < c1558gDC.b.size(); i++) {
            strArr[i] = ((AbstractC1643hD) c1558gDC.b.get(i)).g();
        }
        return strArr;
    }

    public boolean d() {
        return ((AbstractC1643hD) this.a.b.get("has-checksums")).a();
    }

    public Long e() {
        return Long.valueOf(((AbstractC1643hD) this.a.b.get("min-api")).e());
    }

    public final boolean equals(Object obj) {
        if (obj instanceof W) {
            W w = (W) obj;
            if (this.b == w.b && this.a.equals(w.a)) {
                return true;
            }
        }
        return false;
    }

    public String f() {
        return ((AbstractC1643hD) this.a.b.get("pg-map-id")).g();
    }

    public String g() {
        return ((AbstractC1643hD) this.a.b.get("r8-mode")).g();
    }

    public b h() {
        return this.b;
    }

    public final int hashCode() {
        return (this.a.b.hashCode() * 3) + this.b.hashCode();
    }

    public String i() {
        return ((AbstractC1643hD) this.a.b.get("version")).g();
    }

    public boolean j() {
        return this.a.b.containsKey("desugared-library-identifiers");
    }

    public boolean k() {
        return this.a.b.containsKey("min-api");
    }

    public boolean l() {
        return this.a.b.containsKey("platform") && ((AbstractC1643hD) this.a.b.get("platform")).a();
    }

    public boolean m() {
        return k();
    }

    public final String toString() {
        final C1898kD c1898kD = new C1898kD();
        this.a.i().stream().sorted(Comparator.comparing(new Function() { // from class: vdf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) ((Map.Entry) obj).getKey();
            }
        })).forEach(new Consumer() { // from class: xdf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                c1898kD.a((String) entry.getKey(), (AbstractC1643hD) entry.getValue());
            }
        });
        return "~~" + this.b + c1898kD;
    }

    public W(b bVar, C1898kD c1898kD) {
        this.b = bVar;
        this.a = c1898kD;
    }

    public W a(long j) {
        if (!g && this.a.b.containsKey("min-api")) {
            x1f.a();
            return null;
        }
        C1898kD c1898kD = this.a;
        Long lValueOf = Long.valueOf(j);
        c1898kD.getClass();
        c1898kD.a("min-api", new C2155nD(lValueOf));
        return this;
    }

    public String a() {
        if (this.a.b.containsKey("backend")) {
            return ((AbstractC1643hD) this.a.b.get("backend")).g();
        }
        int i = V.a[this.b.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            return null;
        }
        if (k()) {
            return Wf0.i(a.c.name());
        }
        return Wf0.i("CF");
    }

    public W a(String str) {
        if (!g && this.a.b.containsKey("version")) {
            x1f.a();
            return null;
        }
        this.a.a("version", str);
        return this;
    }

    public static boolean a(byte[] bArr) {
        return bArr.length > 2 && bArr[0] == 126 && bArr[1] == 126;
    }

    public static W a(b bVar, String str) {
        try {
            AbstractC1643hD abstractC1643hDA = C2070mD.a(str);
            if (abstractC1643hDA instanceof C1898kD) {
                return new W(bVar, abstractC1643hDA.d());
            }
            return null;
        } catch (C2413qD unused) {
            return null;
        }
    }
}
