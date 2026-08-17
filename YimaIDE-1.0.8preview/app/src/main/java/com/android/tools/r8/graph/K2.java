package com.android.tools.r8.graph;

import com.android.tools.r8.graph.K2;
import com.android.tools.r8.internal.NC;
import com.android.tools.r8.utils.structural.A;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class K2 extends AbstractC0259n1 implements Iterable<I2>, com.android.tools.r8.utils.structural.x<K2> {
    public static final K2 c = new K2();
    public static final /* synthetic */ boolean d = true;
    public final I2[] b;

    public K2(I2[] i2Arr) {
        if (d || (i2Arr != null && i2Arr.length > 0)) {
            this.b = i2Arr;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static K2 n0() {
        return c;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        for (I2 i2 : this.b) {
            i2.a(c0333y, m);
        }
    }

    public final void d(Consumer consumer) {
        for (int length = this.b.length - 1; length >= 0; length--) {
            consumer.accept(this.b[length]);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof K2) && Arrays.equals(this.b, ((K2) obj).b);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super I2> consumer) {
        for (I2 i2 : this.b) {
            consumer.accept(i2);
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public boolean isEmpty() {
        return this.b.length == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<I2> iterator() {
        I2[] i2Arr = this.b;
        return NC.a(i2Arr.length, 0, i2Arr);
    }

    public I2 j(int i) {
        return this.b[i];
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: h38
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                K2.a(a);
            }
        };
    }

    public int size() {
        return this.b.length;
    }

    public Stream<I2> stream() {
        return Stream.of((Object[]) this.b);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        I2[] i2Arr = this.b;
        if (i2Arr.length > 0) {
            sb.append(i2Arr[0]);
            for (int i = 1; i < this.b.length; i++) {
                sb.append(' ');
                sb.append(this.b[i]);
            }
        }
        return sb.toString();
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.f(new Function() { // from class: g38
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((K2) obj).b;
            }
        });
    }

    public boolean a(I2 i2) {
        return com.android.tools.r8.internal.R3.c(this.b, i2);
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        x.a(this);
    }

    public K2() {
        this.b = I2.h;
    }
}
