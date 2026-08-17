package com.android.tools.r8.internal;

import com.android.tools.r8.internal.FK;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FK extends GK implements com.android.tools.r8.utils.structural.x {
    public final int b;
    public final long c;
    public final short[] d;

    public FK(int i, long j, short[] sArr) {
        this.b = i;
        this.c = j;
        this.d = sArr;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: il4
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((FK) obj).b;
            }
        }).a(new ToLongFunction() { // from class: jl4
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((FK) obj).c;
            }
        }).k(new Function() { // from class: kl4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((FK) obj).d;
            }
        });
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.internal.UK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        a(oVar);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: hl4
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                FK.a(a);
            }
        };
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 10;
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return a((FK) uk, abstractC3519a);
    }
}
