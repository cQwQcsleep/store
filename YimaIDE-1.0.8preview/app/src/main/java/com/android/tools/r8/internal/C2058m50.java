package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2058m50 extends CQ {
    public static final /* synthetic */ boolean p = true;
    public final InterfaceC1375e6 n;
    public final Map o;

    public C2058m50(C0333y c0333y, InterfaceC1375e6 interfaceC1375e6, InterfaceC1375e6 interfaceC1375e7, InterfaceC1375e6 interfaceC1375e8, Map map) {
        super(c0333y, interfaceC1375e6, interfaceC1375e7.e(), interfaceC1375e8, interfaceC1375e7);
        this.n = interfaceC1375e8;
        this.o = map;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean a(com.android.tools.r8.graph.F2 f2, com.android.tools.r8.graph.F2 f3) {
        if (f2 != f3) {
            super.a(f2, f3);
            return ((Boolean) com.android.tools.r8.graph.F2.a(f2, f3, new BiFunction() { // from class: llh
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Boolean.valueOf(this.b.a((I2) obj, (I2) obj2));
                }
            }, new BiFunction() { // from class: mlh
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Boolean.valueOf(this.b.a((AbstractC0287r2) obj, (AbstractC0287r2) obj2));
                }
            }, new BiFunction() { // from class: nlh
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Boolean.valueOf(this.b.a((AbstractC0287r2) obj, (AbstractC0287r2) obj2));
                }
            })).booleanValue();
        }
        if (p) {
            return false;
        }
        x01.a("The from and to references should not be equal");
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean o() {
        return true;
    }

    public boolean r() {
        return false;
    }

    @Override // com.android.tools.r8.internal.XR, com.android.tools.r8.internal.AbstractC3148ys
    public final String a(String str) {
        String strA = this.d.a(str);
        return (String) this.o.getOrDefault(strA, strA);
    }

    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        return i2 == i3 || this.n.get(i2) == i3;
    }

    public final boolean a(AbstractC0287r2 abstractC0287r2, AbstractC0287r2 abstractC0287r3) {
        if (a(abstractC0287r2.w0(), abstractC0287r3.w0())) {
            return C2753uC.a(new BiPredicate() { // from class: klh
                @Override // java.util.function.BiPredicate
                public final boolean test(Object obj, Object obj2) {
                    return this.b.a((I2) obj, (I2) obj2);
                }
            }, abstractC0287r2.a(this.c), abstractC0287r3.a(this.c));
        }
        return false;
    }
}
