package com.android.tools.r8.graph;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.InterfaceC1640hA;
import com.android.tools.r8.internal.InterfaceC2045lz;
import com.android.tools.r8.utils.structural.A;
import defpackage.hkh;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.graph.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0230j0 implements com.android.tools.r8.utils.structural.x<C0230j0> {
    public static final /* synthetic */ int e = 0;
    public final H2 b;
    public final I2 c;
    public final H2 d;

    public C0230j0(H2 h2, I2 i2, H2 h3) {
        this.b = h2;
        this.c = i2;
        this.d = h3;
    }

    public static boolean a(InterfaceC2045lz interfaceC2045lz, InterfaceC2045lz interfaceC2045lz2) {
        if (interfaceC2045lz == null) {
            return interfaceC2045lz2 == null;
        }
        if (interfaceC2045lz2 == null || interfaceC2045lz.keySet().size() != interfaceC2045lz2.keySet().size()) {
            return false;
        }
        InterfaceC1640hA it = interfaceC2045lz.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            if (!((C0230j0) interfaceC2045lz.get(iIntValue)).equals(interfaceC2045lz2.get(iIntValue))) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final String b(int i) {
        int iB = AbstractC0007c.b(i);
        if (iB == 0) {
            return XmlPullParser.NO_NAMESPACE;
        }
        if (iB == 1) {
            return this.b.toString();
        }
        if (iB != 2) {
            hkh.a();
            return null;
        }
        H2 h2 = this.b;
        H2 h3 = this.d;
        return h2 + ":" + (h3 == null ? this.c : C0929Wj.b(h3.toString()));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0230j0)) {
            return false;
        }
        C0230j0 c0230j0 = (C0230j0) obj;
        return this.b == c0230j0.b && this.c == c0230j0.c && this.d == c0230j0.d;
    }

    public final int hashCode() {
        int iHashCode = (this.c.hashCode() * 13) + (this.b.hashCode() * 7);
        H2 h2 = this.d;
        return h2 != null ? (h2.hashCode() * 31) + iHashCode : iHashCode;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: kah
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0230j0.a(a);
            }
        };
    }

    public final String toString() {
        return b(2);
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: mah
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0230j0) obj).b;
            }
        }).e(new Function() { // from class: nah
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0230j0) obj).c;
            }
        }).j(new Function() { // from class: oah
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0230j0) obj).d;
            }
        });
    }
}
