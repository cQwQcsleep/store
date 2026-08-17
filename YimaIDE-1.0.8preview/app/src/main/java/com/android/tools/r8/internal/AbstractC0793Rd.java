package com.android.tools.r8.internal;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Rd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0793Rd extends I7 {
    public static final Logger a = Logger.getLogger(AbstractC0793Rd.class.getName());
    public static final boolean b = AbstractC1263cl0.d;

    public static int a(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static int c(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public abstract void a(byte b2);

    public abstract void a(int i, TN tn);

    public abstract void a(int i, boolean z);

    public final void b(int i, int i2) {
        C0689Nd c0689Nd = (C0689Nd) this;
        c0689Nd.c(i, 0);
        c0689Nd.e(i2);
    }

    public abstract void b(long j);

    public abstract void b(U7 u7);

    public abstract void b(String str);

    public abstract void c(int i, int i2);

    public abstract void c(long j);

    public abstract void d(int i);

    public abstract void e(int i);

    public abstract void f(int i);

    public static int b(int i) {
        return c(i << 3);
    }

    public static int a(int i, int i2) {
        return b(i) + (i2 >= 0 ? c(i2) : 10);
    }

    public static int a(int i) {
        if (i >= 0) {
            return c(i);
        }
        return 10;
    }

    public static int a(String str) {
        int length;
        try {
            length = AbstractC2201nl0.a(str);
        } catch (C1944kl0 unused) {
            length = str.getBytes(AbstractC1556gB.b).length;
        }
        return c(length) + length;
    }

    public static int a(U7 u7) {
        int size = u7.size();
        return c(size) + size;
    }

    public static int a(TN tn) {
        int iC = tn.c();
        return c(iC) + iC;
    }

    public final void a() {
        C0689Nd c0689Nd = (C0689Nd) this;
        if (c0689Nd.d - c0689Nd.e == 0) {
            return;
        }
        k2d.a("Did not write as much data as expected.");
    }

    public final void a(String str, C1944kl0 c1944kl0) throws C0741Pd {
        a.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) c1944kl0);
        byte[] bytes = str.getBytes(AbstractC1556gB.b);
        try {
            f(bytes.length);
            ((C0689Nd) this).a(bytes, 0, bytes.length);
        } catch (C0741Pd e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new C0741Pd(e2);
        }
    }
}
