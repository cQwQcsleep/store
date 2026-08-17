package com.android.tools.r8.graph;

import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.C2924wC;
import com.android.tools.r8.internal.InterfaceC0392Br;
import com.android.tools.r8.internal.UK;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class E2 extends X3 implements InterfaceC0221h5, UK {
    public final I2 e;
    public final K2 f;

    static {
        new E2(null, null);
    }

    public E2(I2 i2, K2 k2) {
        this.e = i2;
        this.f = k2;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final String a(AbstractC3345r0 abstractC3345r0) {
        StringBuilder sb = new StringBuilder("(");
        int i = 0;
        while (true) {
            I2[] i2Arr = this.f.b;
            if (i >= i2Arr.length) {
                sb.append(")");
                sb.append(abstractC3345r0.c(this.e));
                return sb.toString();
            }
            sb.append(abstractC3345r0.c(i2Arr[i]));
            i++;
        }
    }

    public final E2 b(B1 b1, I2 i2) {
        I2[] i2Arr = new I2[q0().size() + 1];
        i2Arr[0] = i2;
        System.arraycopy(q0().b, 0, i2Arr, 1, q0().size());
        return b1.a(r0(), i2Arr);
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        if (obj instanceof E2) {
            E2 e2 = (E2) obj;
            if (this.e.equals(e2.e) && this.f.equals(e2.f)) {
                return true;
            }
        }
        return false;
    }

    public final void d(Consumer consumer) {
        consumer.accept(this.e);
        this.f.forEach(consumer);
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final String l0() {
        return a(AbstractC3345r0.a());
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        return (Arrays.hashCode(this.f.b) * 13) + (this.e.hashCode() * 7);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: c34
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                E2.a(a);
            }
        };
    }

    public final String o0() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.e.a1());
        for (I2 i2 : this.f.b) {
            sb.append(i2.a1());
        }
        return sb.toString();
    }

    public int p0() {
        return this.f.size();
    }

    public K2 q0() {
        return this.f;
    }

    public I2 r0() {
        return this.e;
    }

    public final String s0() {
        return a(AbstractC3345r0.a());
    }

    public final String toString() {
        return "Proto " + this.e + " " + this.f;
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 5;
    }

    @Override // com.android.tools.r8.internal.UK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        a(oVar);
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: z24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((E2) obj).r0();
            }
        }).e(new Function() { // from class: b34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((E2) obj).f;
            }
        });
    }

    public final C2924wC a(final B1 b1) {
        return new C2924wC(AbstractC0728Oq.a(Collections.singleton(this.e), this.f), new InterfaceC0392Br() { // from class: x24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I2) obj).a(b1);
            }
        });
    }

    public static boolean a(E2 e2, E2 e3) {
        return e2 == e3;
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return a((E2) uk, abstractC3519a);
    }

    @Override // com.android.tools.r8.graph.X3
    public final int a(C0284q5 c0284q5) {
        return C0284q5.a(this, c0284q5.f);
    }
}
