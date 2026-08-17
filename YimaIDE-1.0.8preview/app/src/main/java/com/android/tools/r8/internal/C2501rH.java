package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1306dH;
import com.android.tools.r8.internal.C2501rH;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2501rH extends AbstractC2587sH {
    public static final AbstractC0706Nu b;
    public final C1306dH a;

    static {
        C1306dH c1306dH = C1306dH.b;
        b = a();
    }

    public C2501rH(C1306dH c1306dH) {
        this.a = c1306dH;
    }

    public static void a(C0629Ku c0629Ku, C1306dH c1306dH) {
        c0629Ku.a(Character.toString(c1306dH.a()), new C2501rH(c1306dH));
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return Character.toString(this.a.a());
    }

    public static AbstractC0706Nu a() {
        final C0629Ku c0629KuE = AbstractC0706Nu.e();
        C1306dH.a(new Consumer() { // from class: q7i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2501rH.a(c0629KuE, (C1306dH) obj);
            }
        });
        return c0629KuE.b();
    }

    @Override // com.android.tools.r8.internal.AbstractC2587sH
    public final Object a(Supplier supplier, Function function, Function function2, Function function3) {
        return function.apply(this.a);
    }
}
