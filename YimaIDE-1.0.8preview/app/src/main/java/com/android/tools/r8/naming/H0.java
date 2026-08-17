package com.android.tools.r8.naming;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.AbstractC1613gu;
import com.android.tools.r8.internal.AbstractC3084y50;
import com.android.tools.r8.internal.BW;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1898kD;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.C2070mD;
import com.android.tools.r8.internal.C2413qD;
import com.android.tools.r8.internal.C2883vk0;
import com.android.tools.r8.internal.C2913w50;
import com.android.tools.r8.internal.C2999x50;
import com.android.tools.r8.internal.E6;
import com.android.tools.r8.internal.R3;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.naming.mappinginformation.MappingInformationDiagnostics;
import com.android.tools.r8.naming.mappinginformation.e;
import defpackage.rfh;
import defpackage.vef;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H0 implements AutoCloseable {
    public static final /* synthetic */ boolean p = true;
    public final P a;
    public final DiagnosticsHandler c;
    public final boolean d;
    public final boolean e;
    public String k;
    public MapVersion l;
    public final C2070mD b = new C2070mD();
    public boolean f = false;
    public final C3347s0 g = new C3347s0();
    public final C3349t0 h = new C3349t0();
    public int i = 0;
    public int j = 0;
    public final String[] m = new String[64];
    public final HashMap n = new HashMap();
    public final HashMap o = new HashMap();

    public H0(P p2, DiagnosticsHandler diagnosticsHandler, boolean z, boolean z2, MapVersion mapVersion) {
        this.a = p2;
        this.c = diagnosticsHandler;
        this.d = z;
        this.e = z2;
        this.l = mapVersion;
        if (p || diagnosticsHandler != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x024b  */
    /* JADX WARN: Code duplicated, block: B:112:0x0261  */
    /* JADX WARN: Code duplicated, block: B:118:0x0270  */
    /* JADX WARN: Code duplicated, block: B:124:0x027b  */
    /* JADX WARN: Code duplicated, block: B:129:0x028c  */
    /* JADX WARN: Code duplicated, block: B:134:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:151:0x02b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x020a  */
    /* JADX WARN: Code duplicated, block: B:92:0x021d  */
    /* JADX WARN: Code duplicated, block: B:95:0x022a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:96:0x022c  */
    /* JADX WARN: Code duplicated, block: B:97:0x0230  */
    public final void a(x0 x0Var, final AbstractC3323g abstractC3323g) {
        H0 h0;
        V.c aVar;
        N0 n0A;
        C1975l7 c1975l7;
        AbstractC3323g abstractC3323g2;
        boolean zEquals;
        boolean z;
        boolean z2;
        V.b bVarC;
        String[] strArr;
        char c;
        C1975l7 c1975l8 = new C1975l7();
        final C1975l7 c1975l9 = new C1975l7();
        int i = -1;
        final C1975l7 c1975l10 = c1975l8;
        V vA = null;
        final String str = null;
        final C3331k.b bVarA = null;
        V.c cVar = null;
        N0 n0 = null;
        do {
            if (c()) {
                final E6 e6 = new E6(false);
                final V v = vA;
                h0 = this;
                vA = v;
                h0.a(new Consumer() { // from class: u16
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.b.a(e6, str, abstractC3323g, bVarA, c1975l9, c1975l10, v, (e) obj);
                    }
                });
                if (e6.e()) {
                    break;
                }
                h0.j = h0.k.length();
                if (h0.k.length() != h0.j) {
                    throw new G0(h0, "Expected end of line", false);
                }
            } else {
                h0 = this;
                if (!Wf0.b(h0.b(0))) {
                    break;
                }
                h0.f();
                N0 n0A2 = h0.a(true);
                if (n0A2 != null) {
                    if (n0A2.c) {
                        throw new G0(h0, String.format("Invalid obfuscated line number range (%s).", n0A2));
                    }
                    h0.f();
                    h0.a(':');
                }
                h0.f();
                String strB = h0.b(true);
                boolean z3 = false;
                while (h0.j < h0.k.length() && Wf0.b(h0.b(0))) {
                    z3 = z3 || !Wf0.a(h0.b(0));
                    h0.e();
                }
                if (!z3) {
                    throw new G0(h0, "Expected whitespace", true);
                }
                int i2 = h0.j;
                h0.c(true);
                while (h0.a(0) == '.') {
                    h0.d();
                    h0.c(true);
                }
                int i3 = h0.j - i2;
                int i4 = i3 % 64;
                String str2 = h0.m[i4];
                if (str2 == null || str2.length() != i3 || !h0.k.regionMatches(i2, str2, 0, i3)) {
                    String strSubstring = h0.k.substring(i2, h0.j);
                    String[] strArr2 = h0.m;
                    str2 = (String) h0.n.computeIfAbsent(strSubstring, Function.identity());
                    strArr2[i4] = str2;
                }
                h0.f();
                if (h0.a(0) == '(') {
                    h0.d();
                    h0.f();
                    if (h0.a(0) == ')') {
                        strArr = Wf0.b;
                        c = ')';
                    } else {
                        ArrayList arrayList = new ArrayList();
                        boolean z4 = true;
                        arrayList.add(h0.b(true));
                        h0.f();
                        for (int i5 = 0; h0.a(i5) != ')'; i5 = 0) {
                            h0.f();
                            h0.a(',');
                            h0.f();
                            arrayList.add(h0.b(z4));
                            z4 = true;
                        }
                        strArr = (String[]) arrayList.toArray(Wf0.b);
                        c = ')';
                    }
                    h0.a(c);
                    aVar = new V.b(str2, strB, strArr);
                } else {
                    aVar = new V.a(str2, strB);
                }
                V.c cVar2 = (V.c) h0.o.computeIfAbsent(aVar, Function.identity());
                h0.f();
                if (h0.a(0) == ':') {
                    h0.d();
                    h0.f();
                    n0A = h0.a(false);
                    if (n0A == null) {
                        throw new G0(h0, "No number follows the colon after the method signature.");
                    }
                } else {
                    n0A = null;
                }
                if (!h0.d && n0A2 == null && n0A != null) {
                    throw new G0(h0, "No mapping for original range " + n0A + ".");
                }
                h0.f();
                h0.a('-');
                h0.a('>');
                h0.f();
                int i6 = h0.j;
                h0.c(true);
                while (h0.a(0) == '.') {
                    h0.d();
                    h0.c(true);
                }
                int i7 = h0.j - i6;
                int i8 = i7 % 64;
                String str3 = h0.m[i8];
                V v2 = vA;
                if (str3 == null || str3.length() != i7) {
                    c1975l7 = c1975l9;
                } else {
                    c1975l7 = c1975l9;
                    if (!h0.k.regionMatches(i6, str3, 0, i7)) {
                    }
                    if (cVar2.d()) {
                        if (bVarA == null && bVarA.c == cVar2 && bVarA.e.equals(str3)) {
                            bVarC = bVarA.c();
                        } else {
                            bVarC = null;
                        }
                        abstractC3323g2 = abstractC3323g;
                        bVarA = abstractC3323g2.a(n0A2, cVar2.b(), n0A, str3);
                        if (bVarA != null) {
                            if (bVarC != null) {
                                bVarA.a(bVarC);
                            } else if (v2 != null && v2.b().equals(bVarA.c)) {
                                bVarA.a(v2.c().b());
                            }
                        }
                    } else {
                        abstractC3323g2 = abstractC3323g;
                    }
                    C3331k.b bVar = bVarA;
                    if (p && n0A2 != null && !cVar2.d()) {
                        x1f.a();
                        return;
                    }
                    if (cVar != null) {
                        zEquals = str.equals(str3);
                        if (n0 == null && n0.equals(n0A2)) {
                            z = false;
                        } else {
                            z = true;
                        }
                        if (n0A == null && n0A.c) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        if (zEquals || z || z2) {
                            C1975l7 c1975l11 = c1975l10;
                            c1975l9 = c1975l7;
                            vA = h0.a(v2, cVar, str, i, c1975l11, c1975l9, n0, abstractC3323g2);
                            c1975l10 = c1975l11;
                        } else {
                            vA = v2;
                            c1975l9 = c1975l7;
                        }
                    } else {
                        vA = v2;
                        c1975l9 = c1975l7;
                    }
                    i = h0.i;
                    cVar = cVar2;
                    bVarA = bVar;
                    n0 = n0A2;
                    str = str3;
                    if (h0.k.length() != h0.j) {
                        throw new G0(h0, "Expected end of line", false);
                    }
                }
                String strSubstring2 = h0.k.substring(i6, h0.j);
                String[] strArr3 = h0.m;
                String str4 = (String) h0.n.computeIfAbsent(strSubstring2, Function.identity());
                strArr3[i8] = str4;
                str3 = str4;
                if (cVar2.d()) {
                    if (bVarA == null) {
                        bVarC = null;
                    } else {
                        bVarC = null;
                    }
                    abstractC3323g2 = abstractC3323g;
                    bVarA = abstractC3323g2.a(n0A2, cVar2.b(), n0A, str3);
                    if (bVarA != null) {
                        if (bVarC != null) {
                            bVarA.a(bVarC);
                        } else if (v2 != null) {
                            bVarA.a(v2.c().b());
                        }
                    }
                } else {
                    abstractC3323g2 = abstractC3323g;
                }
                C3331k.b bVar2 = bVarA;
                if (p) {
                }
                if (cVar != null) {
                    zEquals = str.equals(str3);
                    if (n0 == null) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (n0A == null) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    if (zEquals) {
                    }
                    C1975l7 c1975l12 = c1975l10;
                    c1975l9 = c1975l7;
                    vA = h0.a(v2, cVar, str, i, c1975l12, c1975l9, n0, abstractC3323g2);
                    c1975l10 = c1975l12;
                } else {
                    vA = v2;
                    c1975l9 = c1975l7;
                }
                i = h0.i;
                cVar = cVar2;
                bVarA = bVar2;
                n0 = n0A2;
                str = str3;
                if (h0.k.length() != h0.j) {
                    throw new G0(h0, "Expected end of line", false);
                }
            }
        } while (b(x0Var));
        int i9 = i;
        V.c cVar3 = cVar;
        if (cVar3 != null) {
            h0.a(vA, cVar3, str, i9, c1975l10, c1975l9, n0, abstractC3323g);
        }
    }

    public final boolean b(x0 x0Var) {
        boolean z;
        String str;
        boolean z2;
        this.j = 0;
        do {
            String strA = this.a.a();
            this.k = strA;
            this.i++;
            if (strA != null) {
                int i = 0;
                while (true) {
                    if (i >= strA.length()) {
                        z = true;
                        break;
                    }
                    char cCharAt = strA.charAt(i);
                    if (cCharAt == '#') {
                        while (true) {
                            i++;
                            if (i < strA.length()) {
                                char cCharAt2 = strA.charAt(i);
                                if (cCharAt2 == '{') {
                                    z2 = true;
                                    break;
                                }
                                if (!Character.isWhitespace(cCharAt2)) {
                                }
                            }
                            z2 = false;
                            break;
                        }
                        z = !z2;
                        break;
                    }
                    if (!Wf0.b(cCharAt)) {
                        z = false;
                        break;
                    }
                    i++;
                }
            } else {
                z = true;
                break;
            }
            if (!this.f && z) {
                x0Var.a(this.k);
            }
            str = this.k;
            if (str == null) {
                break;
            }
        } while (z);
        return str != null;
    }

    public final void c(boolean z) {
        boolean z2;
        if (z && a(0) == '<') {
            d();
            z2 = true;
        } else {
            z2 = false;
        }
        int iB = b(0);
        if (!AbstractC1613gu.c(iB) && !AbstractC1613gu.b(iB) && iB != 46) {
            throw new G0(this, "Identifier expected");
        }
        e();
        while (true) {
            if (!AbstractC1613gu.a(b(0)) && !AbstractC1613gu.b(b(0))) {
                break;
            } else {
                e();
            }
        }
        if (z2) {
            a('>');
            while (AbstractC1613gu.a(b(0))) {
                e();
            }
        }
        if (AbstractC1613gu.a(b(0))) {
            throw new G0(this, "End of identifier expected (was 0x" + Integer.toHexString(b(0)) + ")");
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.a.close();
    }

    public final char d() {
        if (!p && this.j >= this.k.length()) {
            x1f.a();
            return (char) 0;
        }
        try {
            String str = this.k;
            int i = this.j;
            this.j = i + 1;
            return str.charAt(i);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new G0(this, "Unexpected end of line", false);
        }
    }

    public final void e() {
        try {
            this.j += Character.charCount(this.k.codePointAt(this.j));
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new G0(this, "Unexpected end of line", false);
        }
    }

    public final void f() {
        while (this.j < this.k.length() && Wf0.b(b(0))) {
            e();
        }
    }

    public final int b(int i) {
        if (this.j + i < this.k.length()) {
            return this.k.codePointAt(this.j + i);
        }
        return 10;
    }

    public final /* synthetic */ void b(com.android.tools.r8.naming.mappinginformation.e eVar, com.android.tools.r8.naming.mappinginformation.e eVar2) {
        this.c.warning(MappingInformationDiagnostics.notAllowedCombination(eVar, eVar2, this.i));
    }

    public final String b(boolean z) {
        int i = this.j;
        c(false);
        while (a(0) == '.') {
            d();
            c(false);
        }
        if (z) {
            while (a(0) == '[') {
                d();
                a(']');
            }
        }
        int i2 = this.j - i;
        int i3 = i2 % 64;
        String str = this.m[i3];
        if (str != null && str.length() == i2 && this.k.regionMatches(i, str, 0, i2)) {
            return str;
        }
        String strSubstring = this.k.substring(i, this.j);
        String[] strArr = this.m;
        String str2 = (String) this.n.computeIfAbsent(strSubstring, Function.identity());
        strArr[i3] = str2;
        return str2;
    }

    public final /* synthetic */ void c(com.android.tools.r8.naming.mappinginformation.e eVar, com.android.tools.r8.naming.mappinginformation.e eVar2) {
        this.c.warning(MappingInformationDiagnostics.notAllowedCombination(eVar, eVar2, this.i));
    }

    public final boolean c() {
        char cCharAt;
        if (this.k == null) {
            return false;
        }
        int i = 0;
        while (i < this.k.length()) {
            char cCharAt2 = this.k.charAt(i);
            if (cCharAt2 == '#') {
                String str = this.k;
                do {
                    i++;
                    if (i >= str.length()) {
                        break;
                    }
                    cCharAt = str.charAt(i);
                    if (cCharAt == '{') {
                        return true;
                    }
                } while (Character.isWhitespace(cCharAt));
                return false;
            }
            if (!Character.isWhitespace(cCharAt2)) {
                return false;
            }
            i++;
        }
        return false;
    }

    public final void a(char c) {
        if (this.j < this.k.length()) {
            if (d() == c) {
                return;
            }
            throw new G0(this, "Expected '" + c + "'");
        }
        throw new G0(this, "Expected '" + c + "'", true);
    }

    public final void a(final x0 x0Var) {
        while (this.k != null) {
            f();
            if (c()) {
                if (!a(new Consumer() { // from class: y16
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.b.a(x0Var, (e) obj);
                    }
                }) && !this.f) {
                    x0Var.a(this.k);
                }
                this.j = this.k.length();
                if (this.k.length() == this.j) {
                    b(x0Var);
                } else {
                    throw new G0(this, "Expected end of line", false);
                }
            } else {
                String strB = b(false);
                f();
                if (!p && !AbstractC1613gu.c(45)) {
                    x1f.a();
                    return;
                }
                if (strB.endsWith("-") && a(0) == ">".charAt(0)) {
                    d();
                    strB = strB.substring(0, strB.length() - 1);
                } else {
                    f();
                    if (a(0) == '-' && a(1) == '>') {
                        d();
                        d();
                    }
                }
                f();
                String strB2 = b(false);
                f();
                a(':');
                this.f = true;
                AbstractC3323g abstractC3323gA = x0Var.a(strB2, strB, new F0(this.i));
                f();
                if (this.k.length() == this.j) {
                    if (b(x0Var)) {
                        a(x0Var, abstractC3323gA);
                    }
                } else {
                    throw new G0(this, "Expected end of line", false);
                }
            }
        }
    }

    public final void a(final x0 x0Var, com.android.tools.r8.naming.mappinginformation.e eVar) {
        if (!p) {
            eVar.getClass();
            if (!(eVar instanceof com.android.tools.r8.naming.mappinginformation.b) && !(eVar instanceof C2883vk0) && !(eVar instanceof BW)) {
                x1f.a();
                return;
            }
        }
        eVar.getClass();
        if (eVar instanceof com.android.tools.r8.naming.mappinginformation.b) {
            x0Var.a(eVar.b());
            return;
        }
        if (eVar instanceof BW) {
            Map map = eVar.e().a;
            Objects.requireNonNull(x0Var);
            map.forEach(new BiConsumer() { // from class: w16
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    x0Var.a((String) obj, (String) obj2);
                }
            });
        } else {
            if (this.f) {
                return;
            }
            x0Var.a(this.k);
        }
    }

    public final /* synthetic */ void a(Consumer consumer, com.android.tools.r8.naming.mappinginformation.e eVar) {
        com.android.tools.r8.naming.mappinginformation.b bVarB = eVar.b();
        if (bVarB != null) {
            MapVersion mapVersionS = bVarB.s();
            MapVersion mapVersion = MapVersion.MAP_VERSION_EXPERIMENTAL;
            if (mapVersionS.equals(mapVersion)) {
                if (!this.e) {
                    mapVersion = MapVersion.MAP_VERSION_NONE;
                }
                this.l = mapVersion;
            } else {
                this.l = bVarB.s();
            }
        }
        consumer.accept(eVar);
    }

    public final char a(int i) {
        if (this.j + i < this.k.length()) {
            return this.k.charAt(this.j + i);
        }
        return '\n';
    }

    public final void a(E6 e6, String str, AbstractC3323g abstractC3323g, C3331k.b bVar, C1975l7 c1975l7, C1975l7 c1975l8, V v, final com.android.tools.r8.naming.mappinginformation.e eVar) {
        eVar.getClass();
        e6.b(eVar instanceof com.android.tools.r8.naming.mappinginformation.b);
        if (str == null) {
            abstractC3323g.a(new Consumer() { // from class: d16
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(eVar, (e) obj);
                }
            }, eVar);
            return;
        }
        if (bVar != null) {
            bVar.a(new Consumer() { // from class: o16
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b(eVar, (e) obj);
                }
            }, eVar);
        }
        if (eVar.o()) {
            com.android.tools.r8.naming.mappinginformation.e.a((List) c1975l7.a((Supplier) new vef()), eVar.f(), new Consumer() { // from class: q16
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.c(eVar, (e) obj);
                }
            });
            if (eVar.p()) {
                AbstractC3084y50 abstractC3084y50I = eVar.i();
                if (!abstractC3084y50I.s()) {
                    this.c.warning(MappingInformationDiagnostics.invalidResidualSignature(this.k.trim(), this.i));
                    return;
                }
                V.c cVarA = a(eVar.i(), str);
                c1975l8.a(cVarA);
                if (bVar != null) {
                    if (!(abstractC3084y50I instanceof C2999x50)) {
                        this.c.warning(MappingInformationDiagnostics.invalidResidualSignatureType(eVar.r(), this.i));
                        c1975l8.a((Object) null);
                    } else if (v == null || !v.b().equals(bVar.c)) {
                        bVar.a(cVarA.b());
                    }
                }
            }
        }
    }

    public final /* synthetic */ void a(com.android.tools.r8.naming.mappinginformation.e eVar, com.android.tools.r8.naming.mappinginformation.e eVar2) {
        this.c.warning(MappingInformationDiagnostics.notAllowedCombination(eVar, eVar2, this.i));
    }

    public final V a(V v, V.c cVar, String str, int i, C1975l7 c1975l7, C1975l7 c1975l8, N0 n0, AbstractC3323g abstractC3323g) {
        V.c cVar2;
        if (n0 != null && v != null && v.a().equals(str) && v.b().equals(cVar)) {
            if (c1975l8.b()) {
                List<com.android.tools.r8.naming.mappinginformation.d> list = (List) c1975l8.a();
                Consumer consumerB = C0822Sg.b();
                for (com.android.tools.r8.naming.mappinginformation.d dVar : list) {
                    if (v.e == V.f) {
                        v.e = new ArrayList();
                    }
                    com.android.tools.r8.naming.mappinginformation.e.a(v.e, dVar, consumerB);
                }
                c1975l8.a((Object) null);
            }
            c1975l7.a((Object) null);
            return v;
        }
        if (c1975l7.b()) {
            if (((V.c) c1975l7.a()).f() != cVar.f()) {
                this.c.warning(MappingInformationDiagnostics.invalidResidualSignatureType(((V.c) c1975l7.a()).toString(), this.i));
                cVar2 = (V.c) this.o.computeIfAbsent(cVar.a(str), Function.identity());
            } else {
                cVar2 = (V.c) c1975l7.a();
            }
        } else {
            cVar2 = (V.c) this.o.computeIfAbsent(cVar.a(str), Function.identity());
        }
        V vA = abstractC3323g.a(cVar2);
        if (vA == null) {
            vA = new V(cVar, cVar2, new F0(i));
        }
        if (c1975l8.b()) {
            List<com.android.tools.r8.naming.mappinginformation.d> list2 = (List) c1975l8.a();
            Consumer consumerB2 = C0822Sg.b();
            for (com.android.tools.r8.naming.mappinginformation.d dVar2 : list2) {
                if (vA.e == V.f) {
                    vA.e = new ArrayList();
                }
                com.android.tools.r8.naming.mappinginformation.e.a(vA.e, dVar2, consumerB2);
            }
        }
        abstractC3323g.a(vA);
        c1975l7.a((Object) null);
        c1975l8.a((Object) null);
        return vA;
    }

    public final V.c a(AbstractC3084y50 abstractC3084y50, String str) {
        abstractC3084y50.getClass();
        if (abstractC3084y50 instanceof C2999x50) {
            C2999x50 c2999x50H = abstractC3084y50.h();
            return ((V.c) this.o.computeIfAbsent(new V.b(str, C0929Wj.b(c2999x50H.b), R3.a((Object[]) c2999x50H.c, (Function) new rfh())), Function.identity())).b();
        }
        if (!p && !(abstractC3084y50 instanceof C2913w50)) {
            x1f.a();
            return null;
        }
        return ((V.c) this.o.computeIfAbsent(new V.a(str, C0929Wj.b(abstractC3084y50.g().b)), Function.identity())).a();
    }

    public final N0 a(boolean z) {
        char cA;
        char cA2;
        char cA3 = a(0);
        if ('0' > cA3 || cA3 > '9') {
            return null;
        }
        if (z) {
            int i = 1;
            while (true) {
                char cA4 = a(i);
                if ('0' > cA4 || cA4 > '9') {
                    break;
                }
                i++;
            }
            while (Wf0.b(b(i))) {
                i++;
            }
            if (a(i) != ':') {
                return null;
            }
        }
        char cA5 = a(0);
        if ('0' <= cA5 && cA5 <= '9') {
            int numericValue = 0;
            do {
                numericValue = (numericValue * 10) + Character.getNumericValue(d());
                cA = a(0);
                if ('0' > cA) {
                    break;
                }
            } while (cA <= '9');
            f();
            if (a(0) != ':') {
                C3347s0 c3347s0 = this.g;
                c3347s0.getClass();
                if (numericValue >= 0 && numericValue < 256) {
                    return c3347s0.a[numericValue];
                }
                return new N0(numericValue, numericValue, true);
            }
            a(':');
            f();
            char cA6 = a(0);
            if ('0' <= cA6 && cA6 <= '9') {
                int numericValue2 = 0;
                do {
                    numericValue2 = (numericValue2 * 10) + Character.getNumericValue(d());
                    cA2 = a(0);
                    if ('0' > cA2) {
                        break;
                    }
                } while (cA2 <= '9');
                if (numericValue > numericValue2) {
                    if (numericValue2 == 0) {
                        numericValue2 = numericValue;
                    } else {
                        numericValue2 = numericValue;
                        numericValue = numericValue2;
                    }
                }
                return this.h.a(numericValue, numericValue2);
            }
            throw new G0(this, "Number expected", false);
        }
        throw new G0(this, "Number expected", false);
    }

    public final boolean a(final Consumer consumer) {
        C1898kD c1898kDD;
        if (!p && !c()) {
            x1f.a();
            return false;
        }
        int i = 0;
        while (this.k.charAt(i) != '{') {
            try {
                i++;
            } catch (C2413qD unused) {
                c1898kDD = null;
            }
        }
        C2070mD c2070mD = this.b;
        String strSubstring = this.k.substring(i);
        c2070mD.getClass();
        c1898kDD = C2070mD.a(strSubstring).d();
        if (c1898kDD == null) {
            return false;
        }
        com.android.tools.r8.naming.mappinginformation.e.a(this.l, c1898kDD, this.c, this.i, new Consumer() { // from class: s16
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(consumer, (e) obj);
            }
        });
        return true;
    }
}
