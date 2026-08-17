package com.android.tools.r8.internal;

import com.android.tools.r8.internal.KK;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KK extends GK implements com.android.tools.r8.utils.structural.x {
    public static final /* synthetic */ boolean d = true;
    public final int[] b;
    public final int[] c;

    public KK(int[] iArr, int[] iArr2) {
        if (!d && iArr.length != iArr2.length) {
            x1f.a();
            throw null;
        }
        this.b = iArr;
        this.c = iArr2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.d(new Function() { // from class: s58
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((KK) obj).b;
            }
        }).d(new Function() { // from class: t58
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((KK) obj).c;
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
        return new com.android.tools.r8.utils.structural.y() { // from class: r58
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                KK.a(a);
            }
        };
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 9;
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return a((KK) uk, abstractC3519a);
    }
}
