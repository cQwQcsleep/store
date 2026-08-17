package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0252m1;
import com.android.tools.r8.utils.structural.A;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.m1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0252m1 implements com.android.tools.r8.utils.structural.x {
    public final H2 b;
    public final I2 c;

    public C0252m1(H2 h2, I2 i2) {
        this.b = h2;
        this.c = i2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: okh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0252m1) obj).a();
            }
        }).e(new Function() { // from class: pkh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0252m1) obj).b();
            }
        });
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final I2 b() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        return com.android.tools.r8.utils.structural.k.a(this, obj);
    }

    public final int hashCode() {
        return Objects.hash(this.b, this.c);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: qkh
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0252m1.a(a);
            }
        };
    }

    public final H2 a() {
        return this.b;
    }

    @Override // com.android.tools.r8.utils.structural.s, com.android.tools.r8.utils.structural.k
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final boolean isEqualTo(C0252m1 c0252m1) {
        return this.b == c0252m1.b && this.c == c0252m1.c;
    }
}
