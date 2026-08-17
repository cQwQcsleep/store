package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.synthesis.C3494c;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P1 {
    public static final /* synthetic */ boolean f = true;
    public final Set a = AbstractC2780ub0.c();
    public final Set b = AbstractC2780ub0.c();
    public final Set c = AbstractC2780ub0.c();
    public final Set d = AbstractC2780ub0.c();
    public final boolean e;

    public P1(boolean z) {
        this.e = z;
    }

    public final void a(com.android.tools.r8.synthesis.J j, com.android.tools.r8.graph.I2 i2) {
        if (!f && this.b.contains(i2)) {
            C3494c c3494c = j.c;
            if (!c3494c.b.containsKey(i2) && !c3494c.c.containsKey(i2)) {
                x1f.a();
                return;
            }
        }
        b(i2);
    }

    public final void b(com.android.tools.r8.graph.I2 i2) {
        if (this.b.contains(i2)) {
            return;
        }
        a(i2);
    }

    public final void c(com.android.tools.r8.graph.I2 i2) {
        this.a.add(i2);
    }

    public final void d(com.android.tools.r8.graph.I2 i2) {
        if (f || !this.d.contains(i2)) {
            this.b.add(i2);
        } else {
            x1f.a();
        }
    }

    public final boolean e(com.android.tools.r8.graph.I2 i2) {
        return this.b.contains(i2) || this.d.contains(i2);
    }

    public final void b(com.android.tools.r8.graph.D2 d2) {
        d(d2.getType());
    }

    public final void a(com.android.tools.r8.graph.I2 i2) {
        if (f || !this.b.contains(i2)) {
            this.d.add(i2);
        } else {
            x1f.a();
        }
    }

    public final Consumer a(final com.android.tools.r8.synthesis.J j) {
        return new Consumer() { // from class: gua
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(j, (I2) obj);
            }
        };
    }

    public final void a(com.android.tools.r8.graph.D2 d2) {
        this.a.add(d2.getType());
    }

    public final R1 a(Set set) {
        if (f || this.a.isEmpty()) {
            return new R1(set, this.b, this.c, this.d, this.e);
        }
        x1f.a();
        return null;
    }
}
