package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.C2685tU;
import com.android.tools.r8.internal.C2753uC;
import com.android.tools.r8.internal.S0;
import com.android.tools.r8.internal.XS;
import com.android.tools.r8.internal.YS;
import com.android.tools.r8.internal.ZS;
import com.android.tools.r8.shaking.K3;
import defpackage.kw5;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G2 extends F2 {
    public final ZS a;

    public G2(ZS zs) {
        this.a = zs;
    }

    @Override // com.android.tools.r8.shaking.F2
    public final void a(StringBuilder sb) {
        ZS zs = this.a;
        zs.getClass();
        XS xs = new XS(new YS(zs));
        boolean z = true;
        while (xs.hasNext()) {
            com.android.tools.r8.internal.S0 s0 = (com.android.tools.r8.internal.S0) xs.next();
            if (!z) {
                sb.append(',');
            }
            if (s0.c) {
                sb.append('!');
            }
            sb.append(((K3) s0.b).toString());
            z = false;
        }
    }

    @Override // com.android.tools.r8.shaking.F2
    public final Iterable c() {
        ZS zs = this.a;
        zs.getClass();
        return C2753uC.b(new C2685tU(zs.d, zs.b), new kw5());
    }

    @Override // com.android.tools.r8.shaking.F2
    public final int e() {
        return this.a.d;
    }

    @Override // com.android.tools.r8.shaking.F2
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || G2.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((G2) obj).a);
    }

    @Override // com.android.tools.r8.shaking.F2
    public final int hashCode() {
        return Objects.hash(this.a);
    }

    @Override // com.android.tools.r8.shaking.F2
    public final List a() {
        return null;
    }

    @Override // com.android.tools.r8.shaking.F2
    public final boolean a(com.android.tools.r8.graph.I2 i2) {
        ZS zs = this.a;
        zs.getClass();
        XS xs = new XS(new YS(zs));
        boolean z = false;
        while (xs.hasNext()) {
            com.android.tools.r8.internal.S0 s0 = (com.android.tools.r8.internal.S0) xs.next();
            boolean zB = ((K3) s0.b).b(i2);
            z = s0.c;
            if (zB) {
                return !z;
            }
        }
        return z;
    }

    @Override // com.android.tools.r8.shaking.F2
    public final F2 a(final com.android.tools.r8.graph.B1 b1) {
        final F2.a aVarB = F2.b();
        this.a.forEach(new BiConsumer() { // from class: lw5
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Boolean bool = (Boolean) obj2;
                aVarB.a(bool.booleanValue(), ((K3) obj).a(b1));
            }
        });
        return aVarB.a();
    }

    @Override // com.android.tools.r8.shaking.F2
    public final void a(final Consumer consumer) {
        ZS zs = this.a;
        zs.getClass();
        new YS(zs).forEach(new Consumer() { // from class: mw5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                consumer.accept((K3) ((S0) obj).getKey());
            }
        });
    }
}
