package com.android.tools.r8.kotlin;

import com.android.tools.r8.DataResource;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
class u0 implements InterfaceC3369b0 {
    public static final /* synthetic */ boolean d = true;
    public final I2 a;
    public final String b;
    public final boolean c;

    public u0(String str) {
        this.a = null;
        this.c = false;
        this.b = str;
        if (d || str != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static I2 a(C0333y c0333y, I2 i2) {
        if (i2.I0()) {
            I2 i2A = a(c0333y, i2.a(c0333y.a()));
            if (i2A != null) {
                return i2.a(c0333y.a(), i2A);
            }
            return null;
        }
        if (!i2.M0()) {
            return i2;
        }
        I2 i2D = c0333y.A().d(c0333y.k, i2);
        if (!c0333y.g().i() || ((C3403i) c0333y.V().g()).k(i2D)) {
            return i2D;
        }
        return null;
    }

    public final boolean b(Consumer consumer, C0333y c0333y, String str) {
        I2 i2 = this.a;
        if (i2 == null) {
            consumer.accept(this.b);
            return false;
        }
        I2 i2A = a(c0333y, i2);
        if (i2A != null) {
            String string = c0333y.w().c(i2A).toString();
            consumer.accept(string);
            return !this.a.Z0().equals(string);
        }
        String strZ0 = this.a.Z0();
        if (AbstractC3284b.a.contains(strZ0)) {
            consumer.accept(strZ0);
            return false;
        }
        consumer.accept(str);
        return true;
    }

    public String toString() {
        I2 i2 = this.a;
        return i2 != null ? i2.f.toString() : this.b;
    }

    public final String v() {
        return this.b;
    }

    public final String w() {
        I2 i2 = this.a;
        return i2 == null ? this.b : d0.a(i2.Z0(), true);
    }

    public u0(String str, I2 i2, boolean z) {
        this.b = str;
        this.a = i2;
        this.c = z;
        if (d || i2 != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static u0 a(String str, B1 b1, String str2, boolean z) {
        if (C0929Wj.A(str)) {
            return new u0(str2, b1.e(str), z);
        }
        return new u0(str2);
    }

    public final boolean a(final Consumer consumer, C0333y c0333y, final String str) {
        if (this.a == null) {
            consumer.accept(this.b);
            return false;
        }
        return b(new Consumer() { // from class: com.android.tools.r8.kotlin.c1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(str, consumer, (String) obj);
            }
        }, c0333y, str);
    }

    public final /* synthetic */ void a(String str, Consumer consumer, String str2) {
        String strF;
        if (str2 != null && !str2.equals(str)) {
            if (this.c) {
                strF = C0929Wj.c(str2);
            } else {
                strF = C0929Wj.f(str2);
            }
            consumer.accept(strF);
            return;
        }
        consumer.accept(str2);
    }

    public static u0 a(String str, B1 b1, String str2) {
        String strL;
        AbstractC0706Nu abstractC0706Nu = C0929Wj.a;
        if (C0929Wj.F(str.replace(DataResource.SEPARATOR, '.'))) {
            boolean zContains = str.contains(".");
            if (zContains) {
                strL = C0929Wj.m(str);
            } else {
                strL = C0929Wj.l(str);
            }
            return a(strL, b1, str2, zContains);
        }
        return new u0(str);
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        I2 i2 = this.a;
        if (i2 == null || !i2.M0()) {
            return;
        }
        interfaceC0189d1.a(this.a);
    }
}
