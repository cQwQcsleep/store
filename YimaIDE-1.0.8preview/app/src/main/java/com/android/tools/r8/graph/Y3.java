package com.android.tools.r8.graph;

import com.android.tools.r8.shaking.C3403i;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Y3 {
    public static final /* synthetic */ boolean e = true;
    public final int a;
    public final I2 b;
    public final I2 c;
    public final H2 d;

    public Y3(int i, I2 i2, I2 i3, H2 h2) {
        if (!e && i2 == null) {
            x1f.a();
            throw null;
        }
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = h2;
    }

    public final I2 a(C0333y c0333y) {
        E0 e0D;
        I2 i2D = d();
        if (i2D != null || (e0D = c0333y.d(b())) == null || e0D.R0() == null) {
            return i2D;
        }
        C0191d3 c0191d3R0 = e0D.R0();
        I2 i2 = c0191d3R0.a;
        if (i2 != null) {
            return i2;
        }
        C0322w2 c0322w2B = c0191d3R0.b();
        if (((C3403i) c0333y.g()).r.contains(c0322w2B)) {
            return c0322w2B.f;
        }
        return null;
    }

    public I2 b() {
        return this.b;
    }

    public H2 c() {
        return this.d;
    }

    public I2 d() {
        return this.c;
    }

    public final boolean e() {
        return this.d != null;
    }

    public final String toString() {
        String hexString = Integer.toHexString(this.a);
        String strZ0 = this.b.Z0();
        I2 i2 = this.c;
        String strZ1 = i2 == null ? "null" : i2.Z0();
        H2 h2 = this.d;
        return "[access : " + hexString + ", inner: " + strZ0 + ", outer: " + strZ1 + ", innerName: " + (h2 == null ? "(anonymous)" : h2.toString()) + "]";
    }

    public int a() {
        return this.a;
    }

    public final void a(Consumer consumer) {
        I2 i2 = this.b;
        if (i2 != null) {
            consumer.accept(i2);
        }
        I2 i3 = this.c;
        if (i3 != null) {
            consumer.accept(i3);
        }
    }
}
