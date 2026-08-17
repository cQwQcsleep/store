package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2415qF;
import com.android.tools.r8.internal.C2585sF;
import defpackage.g5c;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SF extends AbstractC0779Qp {
    public final InterfaceC1221cG c;
    public final String d;
    public final String e;
    public final String f;
    public final C2687tW g;

    public SF(C2601sW c2601sW, InterfaceC1221cG interfaceC1221cG, String str, String str2, String str3) {
        super(589824, null);
        this.c = interfaceC1221cG;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = new C2687tW(c2601sW, str2, str3);
    }

    public static K2 a(String str, boolean z, boolean z2, Consumer consumer, C2516rW c2516rW, String str2, String str3, String str4, Consumer consumer2) {
        if (z || !z2) {
            return null;
        }
        if (str.equals("Lcom/android/tools/r8/keepanno/annotations/KeepEdge;")) {
            Objects.requireNonNull(consumer);
            return new UF(c2516rW, new g5c(consumer), consumer2);
        }
        if (str.equals("Lcom/android/tools/r8/keepanno/annotations/UsesReflection;")) {
            Objects.requireNonNull(consumer);
            return new C1561gG(c2516rW, new g5c(consumer), consumer2, a(str2, str3, str4));
        }
        if (str.equals("Lcom/android/tools/r8/keepanno/annotations/KeepForApi;")) {
            Objects.requireNonNull(consumer);
            return new LF(c2516rW, new g5c(consumer), consumer2, a(str2, str3, str4));
        }
        if (!str.equals("Lcom/android/tools/r8/keepanno/annotations/UsedByReflection;") && !str.equals("Lcom/android/tools/r8/keepanno/annotations/UsedByNative;")) {
            return null;
        }
        Objects.requireNonNull(consumer);
        return new C1389eG(c2516rW, new g5c(consumer), consumer2, a(str2, str3, str4));
    }

    public final void a(C2585sF c2585sF) {
        String strC = AbstractC1732iG.c(this.d);
        String str = this.e;
        String str2 = this.f;
        c2585sF.getClass();
        c2585sF.a = new C2927wF(strC, str, str2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final J2 a(String str, boolean z) {
        final InterfaceC1221cG interfaceC1221cG = this.c;
        Objects.requireNonNull(interfaceC1221cG);
        Consumer consumer = new Consumer() { // from class: inc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                interfaceC1221cG.accept((C2415qF) obj);
            }
        };
        C2687tW c2687tW = this.g;
        c2687tW.getClass();
        return a(str, z, true, consumer, new C2516rW(c2687tW, str), this.d, this.e, this.f, new Consumer() { // from class: jnc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C2585sF) obj);
            }
        });
    }

    public static AG a(String str, String str2, String str3) {
        C1987lG c1987lG;
        AbstractC2330pG abstractC2330pGA = AbstractC2330pG.a(AbstractC2587sH.a(str3));
        ME.g();
        EG eg = EG.d;
        C1476fH c1476fHA = C1476fH.a(str);
        C1476fH.a();
        C2416qG c2416qG = C2416qG.c;
        C2345pV c2345pV = C2345pV.b;
        OE oe = new OE(new ME(c1476fHA, c2416qG, c2345pV));
        C1901kG c1901kG = C1901kG.h;
        C1987lG c1987lG2 = C1987lG.b;
        C2244oG c2244oG = C2244oG.b;
        C1988lH c1988lHA = C1988lH.a(str2);
        if (C1988lH.d == c1988lHA) {
            c1987lG = C1987lG.b;
        } else {
            c1987lG = new C1987lG(c1988lHA);
        }
        return new AG(oe, new C2158nG(c2345pV, c1901kG, c1987lG, abstractC2330pGA));
    }
}
