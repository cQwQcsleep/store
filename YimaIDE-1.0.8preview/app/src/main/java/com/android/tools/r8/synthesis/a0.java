package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.XR;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class a0 {
    public static final /* synthetic */ boolean c = true;
    public final S.b a;
    public final C3502k b;

    public a0(S.b bVar, C3502k c3502k) {
        boolean z = c;
        if (!z && bVar == null) {
            x1f.a();
            throw null;
        }
        if (!z && c3502k == null) {
            x1f.a();
            throw null;
        }
        this.a = bVar;
        this.b = c3502k;
    }

    public abstract I2 a();

    public abstract a0 a(C3502k c3502k, XR xr);

    public /* bridge */ /* synthetic */ InterfaceC3501j a(XR xr) {
        return (InterfaceC3501j) b(xr);
    }

    public abstract AbstractC3509s a(Function function);

    public final a0 b(XR xr) {
        C3502k c3502k = this.b;
        I2 i2 = c3502k.c;
        xr.getClass();
        I2 i2C = xr.c(AbstractC3148ys.g(), i2);
        I2 i2C2 = xr.c(AbstractC3148ys.g(), c3502k.b);
        if (i2C != c3502k.c || i2C2 != c3502k.b) {
            c3502k = new C3502k(i2C2, i2C, c3502k.d, c3502k.e);
        }
        return a(c3502k, xr);
    }

    public final S.b c() {
        return this.a;
    }

    public abstract F2 d();

    public final C3502k b() {
        return this.b;
    }
}
