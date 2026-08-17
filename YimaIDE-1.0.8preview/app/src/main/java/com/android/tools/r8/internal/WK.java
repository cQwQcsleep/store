package com.android.tools.r8.internal;

import java.util.function.Function;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class WK {
    public static final /* synthetic */ boolean c = true;
    public final boolean a;
    public final AS b;

    public WK(AS as) {
        if (!c && as == null) {
            x1f.a();
            throw null;
        }
        this.a = as.b() == 0;
        this.b = as;
    }

    public final int a(int i) {
        if (!this.a) {
            return this.b.a();
        }
        if (c || i < this.b.b()) {
            return i;
        }
        x1f.a();
        return 0;
    }

    public abstract PW a(int i, IntFunction intFunction, C1720i7 c1720i7, Function function, AbstractC2420qL abstractC2420qL);

    public abstract C2543rl0 a(int i, AbstractC2624sj0 abstractC2624sj0);

    public abstract C2543rl0 a(int i, AbstractC2624sj0 abstractC2624sj0, Function function);

    public abstract C2543rl0 a(Object obj, AbstractC2420qL abstractC2420qL);
}
