package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C3100yI;
import com.android.tools.r8.internal.II;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import defpackage.w33;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class v0 implements InterfaceC3369b0 {
    public static final P40 e;
    public final String a;
    public final int b;
    public final r0 c;
    public final r0 d;

    static {
        int i = AbstractC0551Hu.c;
        e = P40.e;
    }

    public v0(int i, String str, r0 r0Var, r0 r0Var2) {
        this.a = str;
        this.b = i;
        this.c = r0Var;
        this.d = r0Var2;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        this.c.a(interfaceC0189d1);
        r0 r0Var = this.d;
        if (r0Var != null) {
            r0Var.a(interfaceC0189d1);
        }
    }

    public final boolean b(Consumer consumer, C0333y c0333y) {
        final II ii = (II) d0.a(consumer, new II(this.b, this.a));
        r0 r0Var = this.c;
        Objects.requireNonNull(ii);
        return d0.a(c0333y, this.d, new Consumer() { // from class: oii
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ii.b((C3100yI) obj);
            }
        }, new w33()) | r0Var.b(new Consumer() { // from class: nii
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ii.a((C3100yI) obj);
            }
        }, c0333y);
    }
}
