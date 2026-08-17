package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1605gm0;
import com.android.tools.r8.shaking.S3;
import defpackage.j38;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class K3 {

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class a {
        public static final a b = new a(0, "CLASS");
        public static final a c = new a(1, "TYPE");

        public a(int i, String str) {
            super(str, i);
        }
    }

    public static K3 a(T2.a aVar, a aVar2, com.android.tools.r8.graph.B1 b1) {
        String str;
        if (aVar == null || (str = aVar.a) == null) {
            return null;
        }
        switch (str) {
            case "%":
                return N3.b;
            case "*":
                return O3.d;
            case "**":
                return O3.c;
            case "***":
                return L3.b;
            case "...":
                return M3.a;
            default:
                return aVar.b.isEmpty() ? new b(b1.e(C0929Wj.I(aVar.a))) : new P3(aVar, aVar2);
        }
    }

    public com.android.tools.r8.graph.I2 b() {
        return null;
    }

    public abstract boolean b(com.android.tools.r8.graph.I2 i2);

    public Iterable c() {
        return new defpackage.s();
    }

    public boolean d() {
        return this instanceof L3;
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract String toString();

    public static class b extends K3 {
        public final com.android.tools.r8.graph.I2 a;

        public b(com.android.tools.r8.graph.I2 i2) {
            this.a = i2;
        }

        @Override // com.android.tools.r8.shaking.K3
        public final b a() {
            return this;
        }

        @Override // com.android.tools.r8.shaking.K3
        public final boolean b(com.android.tools.r8.graph.I2 i2) {
            return this.a == i2;
        }

        @Override // com.android.tools.r8.shaking.K3
        public final boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.a.equals(((b) obj).a);
            }
            return false;
        }

        @Override // com.android.tools.r8.shaking.K3
        public final int hashCode() {
            return this.a.hashCode();
        }

        @Override // com.android.tools.r8.shaking.K3
        public final String toString() {
            return this.a.m0();
        }

        @Override // com.android.tools.r8.shaking.K3
        public com.android.tools.r8.graph.I2 b() {
            return this.a;
        }
    }

    public K3 a(com.android.tools.r8.graph.B1 b1) {
        return this;
    }

    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.I2 i2) {
        if (b(i2)) {
            return true;
        }
        C1605gm0 c1605gm0 = c0333y.I;
        if (c1605gm0 != null) {
            return c1605gm0.a.a(i2).stream().anyMatch(new j38(this));
        }
        return false;
    }

    public static Iterable a(K3 k3) {
        return k3 == null ? new defpackage.s() : k3.c();
    }

    public static ArrayList a(List list) {
        final ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((K3) it.next()).c().forEach(new Consumer() { // from class: k38
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList.add((S3) obj);
                }
            });
        }
        return arrayList;
    }

    public static List a(List list, com.android.tools.r8.graph.B1 b1) {
        if (list.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            c0473EuG.a(((K3) it.next()).a(b1));
        }
        return c0473EuG.a();
    }

    public b a() {
        return null;
    }

    public static K3 a(com.android.tools.r8.graph.I2 i2) {
        return new b(i2);
    }
}
