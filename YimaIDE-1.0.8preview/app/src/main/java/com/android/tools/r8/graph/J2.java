package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.internal.C3052xj0;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.utils.structural.A;
import defpackage.m87;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class J2 extends C0285r0 {
    public final int f;
    public final C3052xj0 g;

    public J2(int i, C0196e1 c0196e1, int i2, C3052xj0 c3052xj0) {
        super(i, c0196e1);
        this.f = i2;
        this.g = c3052xj0;
    }

    @Override // com.android.tools.r8.graph.C0285r0
    public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        throw new Kk0("Should not collect type annotation in DEX");
    }

    public final void b(com.android.tools.r8.utils.structural.A a) {
        new m87().a(a.a(new ToIntFunction() { // from class: k87
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return this.b.d((C0285r0) obj);
            }
        }).d(new Function() { // from class: l87
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.e((C0285r0) obj);
            }
        }));
    }

    public final /* synthetic */ int d(C0285r0 c0285r0) {
        return this.f;
    }

    public final int[] e(C0285r0 c0285r0) {
        C3052xj0 c3052xj0 = this.g;
        int i = c3052xj0.a[c3052xj0.b] * 2;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            C3052xj0 c3052xj1 = this.g;
            byte[] bArr = c3052xj1.a;
            int i4 = c3052xj1.b + i3;
            iArr[i3] = bArr[i4 + 1];
            iArr[i3 + 1] = bArr[i4 + 2];
        }
        return iArr;
    }

    @Override // com.android.tools.r8.graph.C0285r0
    public final J2 n0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.C0285r0, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: n87
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                this.a.b(a);
            }
        };
    }

    @Override // com.android.tools.r8.graph.C0285r0
    public final boolean q0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.C0285r0, com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        throw new Kk0("Should not collect type annotation in DEX");
    }
}
