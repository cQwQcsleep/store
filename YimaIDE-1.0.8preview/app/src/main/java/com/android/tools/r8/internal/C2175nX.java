package com.android.tools.r8.internal;

import com.android.tools.r8.ResourceException;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2175nX implements InterfaceC2347pX {
    public static final /* synthetic */ boolean h = true;
    public final C0333y a;
    public final com.android.tools.r8.graph.B1 b;
    public final N9 d;
    public final InterfaceC2347pX e;
    public C0231j1 f;
    public final IdentityHashMap c = new IdentityHashMap();
    public com.android.tools.r8.kotlin.n0.b g = null;

    public C2175nX(C0333y c0333y, InterfaceC2347pX interfaceC2347pX, N9 n9) {
        this.a = c0333y;
        this.b = c0333y.a();
        this.e = interfaceC2347pX;
        this.d = n9;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2347pX
    public final C1405eW a(AbstractC2004lX abstractC2004lX) {
        com.android.tools.r8.kotlin.n0.b bVar;
        Map.Entry<Integer, com.android.tools.r8.kotlin.n0.a> entryA;
        com.android.tools.r8.graph.B1 b1;
        String str = null;
        if (!h && this.f == null) {
            x1f.a();
            return null;
        }
        int iF = abstractC2004lX.f();
        com.android.tools.r8.graph.I2 i2 = abstractC2004lX.c.f;
        if (this.g == null) {
            this.g = (com.android.tools.r8.kotlin.n0.b) this.c.get(i2);
        }
        if (this.g != null || this.c.containsKey(i2)) {
            bVar = this.g;
        } else {
            com.android.tools.r8.graph.O2.j jVar = (com.android.tools.r8.graph.O2.j) this.a.M.get(this.a.d(this.f.E0()).e);
            if (jVar != null) {
                this.g = com.android.tools.r8.kotlin.n0.c(((com.android.tools.r8.graph.H2) jVar.c).toString());
            }
            this.c.put(i2, this.g);
            bVar = this.g;
        }
        if (bVar != null && (entryA = bVar.a(iF)) != null) {
            int iIntValue = entryA.getValue().a().a + (iF - entryA.getKey().intValue());
            try {
                String strB = entryA.getValue().b().b();
                N9 n9 = this.d;
                if (n9.a == null) {
                    n9.a = new HashMap();
                    n9.a();
                }
                C2986wz c2986wz = (C2986wz) n9.a.get(strB);
                if (c2986wz != null) {
                    str = (String) c2986wz.get(iIntValue);
                }
                if (str == null) {
                    return this.e.a(abstractC2004lX);
                }
                String strL = C0929Wj.l(strB);
                int iIndexOf = str.indexOf(";;");
                boolean z = N9.c;
                if (!z && iIndexOf <= 0) {
                    throw new AssertionError();
                }
                String strSubstring = str.substring(0, iIndexOf);
                int iIndexOf2 = str.indexOf(";;");
                if (!z && iIndexOf2 <= 0) {
                    throw new AssertionError();
                }
                String strSubstring2 = str.substring(iIndexOf2 + 2);
                String strU = C0929Wj.u(strSubstring2);
                String[] strArrE = C0929Wj.e(strSubstring2);
                com.android.tools.r8.graph.H2[] h2Arr = new com.android.tools.r8.graph.H2[strArrE.length];
                int i = 0;
                while (true) {
                    int length = strArrE.length;
                    b1 = this.b;
                    if (i >= length) {
                        break;
                    }
                    h2Arr[i] = b1.c(strArrE[i]);
                    i++;
                }
                C0322w2 c0322w2A = b1.a(b1.c(strL), this.b.c(strSubstring), this.b.c(strU), h2Arr);
                if (!c0322w2A.equals(abstractC2004lX.c)) {
                    Map.Entry entryA2 = bVar.b.a(Integer.valueOf(iF));
                    if (entryA2 != null) {
                        abstractC2004lX = abstractC2004lX.b().a(Math.max(0, ((com.android.tools.r8.kotlin.n0.a) entryA2.getValue()).a().a)).a();
                    }
                    InterfaceC2347pX interfaceC2347pX = this.e;
                    AbstractC2004lX.b.a aVarA = AbstractC2004lX.b.s().a(iIntValue).a(c0322w2A);
                    aVarA.c = abstractC2004lX;
                    return interfaceC2347pX.a(aVarA.a());
                }
                return this.e.a(abstractC2004lX);
            } catch (ResourceException unused) {
            }
        }
        return this.e.a(abstractC2004lX);
    }
}
