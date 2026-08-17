package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.Y3;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.internal.OB;
import com.android.tools.r8.shaking.C3412j3;
import com.android.tools.r8.shaking.C3427m3;
import com.android.tools.r8.shaking.EnumC3447q3;
import com.android.tools.r8.shaking.R2;
import com.android.tools.r8.shaking.Y2;
import defpackage.wk8;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class d0 {
    public static final c0 a = new c0("NO_KOTLIN_INFO");
    public static final c0 b = new c0("INVALID_KOTLIN_INFO");
    public static final /* synthetic */ boolean c = true;

    public static boolean a(C0333y c0333y) {
        E0 e0C = c0333y.g().c(c0333y.a().w3);
        if (e0C == null || e0C.y1()) {
            return true;
        }
        R2 r2H = c0333y.M().H();
        if (r2H == null || r2H.q() == null) {
            return false;
        }
        Iterator<Y2> it = r2H.q().iterator();
        while (it.hasNext()) {
            if (a(it.next(), c0333y.M().a)) {
                return true;
            }
        }
        return false;
    }

    public static com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar, int i) {
        return new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(kVar.d() + "$default", kVar.b().replace(")", "I".repeat(i) + "Ljava/lang/Object;)"));
    }

    public static boolean a(String str) {
        try {
            for (String str2 : C0929Wj.e(str)) {
                if (str2.charAt(0) == 'L' && !C0929Wj.z(str2)) {
                    return false;
                }
            }
            return true;
        } catch (OB unused) {
            return false;
        }
    }

    public static com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k a(C0322w2 c0322w2) {
        StringBuilder sb = new StringBuilder("(");
        for (I2 i2 : c0322w2.i.f.b) {
            sb.append(i2.Z0());
        }
        sb.append(")");
        sb.append(c0322w2.i.e.Z0());
        return new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(c0322w2.g.toString(), sb.toString());
    }

    public static boolean a(Y2 y2, B1 b1) {
        y2.getClass();
        if (y2 instanceof C3412j3) {
            return a(y2.p().I(), b1);
        }
        if (!(y2 instanceof C3427m3)) {
            return false;
        }
        C3427m3 c3427m3Q = y2.q();
        if (c3427m3Q.G() == EnumC3447q3.c || c3427m3Q.F().d) {
            return false;
        }
        return c3427m3Q.c().a(b1.w3);
    }

    public static String a(E0 e0, String str) {
        Y3 y3S0 = e0.S0();
        if (y3S0 != null && y3S0.d() != null) {
            return C0929Wj.c(str);
        }
        if (!e0.u1() && !e0.p1()) {
            return C0929Wj.f(str);
        }
        return a(str, true);
    }

    public static String a(String str, boolean z) {
        if (z) {
            return "." + C0929Wj.f(str);
        }
        return C0929Wj.c(str);
    }

    public static int[] a() {
        return com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.b;
    }

    public static Object a(Consumer consumer, Object obj) {
        consumer.accept(obj);
        return obj;
    }

    public static boolean a(C0333y c0333y, Object obj, Consumer consumer, InterfaceC1938ki0 interfaceC1938ki0) {
        if (obj != null) {
            return ((Boolean) interfaceC1938ki0.a(obj, consumer, c0333y)).booleanValue();
        }
        return false;
    }

    public static boolean a(C0333y c0333y, List list, List list2, InterfaceC1938ki0 interfaceC1938ki0) {
        if (c || list2.isEmpty()) {
            Objects.requireNonNull(list2);
            return a(c0333y, list, (Consumer) new wk8(list2), interfaceC1938ki0);
        }
        x1f.a();
        return false;
    }

    public static boolean a(C0333y c0333y, List list, Consumer consumer, InterfaceC1938ki0 interfaceC1938ki0) {
        Iterator it = list.iterator();
        boolean zBooleanValue = false;
        while (it.hasNext()) {
            zBooleanValue |= ((Boolean) interfaceC1938ki0.a(it.next(), consumer, c0333y)).booleanValue();
        }
        return zBooleanValue;
    }
}
