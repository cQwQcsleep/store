package com.android.tools.r8.internal;

import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class FG {
    public static final /* synthetic */ boolean a = true;

    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        Function functionA = E4.a(consumer);
        Function functionA2 = E4.a(consumer2);
        Function functionA3 = E4.a(consumer3);
        if (g()) {
            functionA.apply(this);
            return;
        }
        if (f()) {
            functionA2.apply(b());
        } else if (a || h()) {
            functionA3.apply(c());
        } else {
            x1f.a();
        }
    }

    public C2158nG b() {
        return null;
    }

    public NG c() {
        return null;
    }

    public abstract C3014xG d();

    public abstract AbstractC2515rV e();

    public final boolean f() {
        return b() != null;
    }

    public final boolean g() {
        return (h() || f()) ? false : true;
    }

    public final boolean h() {
        return c() != null;
    }

    public static EG a() {
        return EG.d;
    }
}
