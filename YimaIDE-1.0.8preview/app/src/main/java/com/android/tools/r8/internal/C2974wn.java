package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2974wn {
    public static final /* synthetic */ boolean c = true;
    public final AbstractC0706Nu a;
    public final AbstractC0706Nu b;

    public C2974wn(AbstractC0706Nu abstractC0706Nu, AbstractC0706Nu abstractC0706Nu2) {
        this.a = abstractC0706Nu;
        this.b = abstractC0706Nu2;
    }

    public final int a(C0245l1 c0245l1) {
        if (!c && !b(c0245l1.w0())) {
            x1f.a();
            return 0;
        }
        C2888vn c2888vnA = a(c(c0245l1.w0()));
        if (C2888vn.f || c2888vnA.c.containsKey(c0245l1)) {
            return ((Integer) c2888vnA.c.get(c0245l1)).intValue();
        }
        x1f.a();
        return 0;
    }

    public final boolean b(C0245l1 c0245l1) {
        com.android.tools.r8.graph.I2 i2C = c(c0245l1.w0());
        return this.a.containsKey(i2C) && a(i2C).c.containsKey(c0245l1);
    }

    public final com.android.tools.r8.graph.I2 c(com.android.tools.r8.graph.I2 i2) {
        Object obj = this.b.get(i2);
        Object obj2 = i2;
        if (obj != null) {
            obj2 = obj;
        }
        return (com.android.tools.r8.graph.I2) obj2;
    }

    public final boolean b(com.android.tools.r8.graph.D2 d2) {
        return b(d2.getType());
    }

    public boolean b(com.android.tools.r8.graph.I2 i2) {
        return this.a.containsKey(c(i2));
    }

    public boolean b() {
        return this.a.isEmpty();
    }

    public final C2888vn a(com.android.tools.r8.graph.I2 i2) {
        C2888vn c2888vn = (C2888vn) this.a.get(c(i2));
        if (c || c2888vn != null) {
            return c2888vn;
        }
        x1f.a();
        return null;
    }

    public final C2888vn a(com.android.tools.r8.graph.D2 d2) {
        return a(d2.getType());
    }

    public final Set a() {
        Set setC = AbstractC2780ub0.c();
        setC.addAll(this.a.keySet());
        setC.addAll(this.b.keySet());
        return setC;
    }

    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        boolean z = c;
        if (!z && i3 == null) {
            x1f.a();
            return false;
        }
        if (z || i2 != null) {
            return i3 == i2 || i3 == this.b.get(i2);
        }
        x1f.a();
        return false;
    }
}
