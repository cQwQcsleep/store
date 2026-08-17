package com.android.tools.r8.internal;

import defpackage.f0c;
import defpackage.g3c;
import defpackage.i0e;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T7 implements Iterable {
    public static final CL b = new CL(new byte[0]);
    public static final /* synthetic */ boolean c = true;

    public abstract int a();

    public abstract int a(int i, int i2, int i3);

    public final T7 a(T7 t7) {
        int size = size();
        int size2 = t7.size();
        if (((long) size) + ((long) size2) >= 2147483647L) {
            i0e.a(53, "ByteString would be too long: ", size, size2);
            return null;
        }
        int[] iArr = C2692ta0.j;
        C2692ta0 c2692ta0 = this instanceof C2692ta0 ? (C2692ta0) this : null;
        if (t7.size() == 0) {
            return this;
        }
        if (size() == 0) {
            return t7;
        }
        int size3 = t7.size() + size();
        if (size3 < 128) {
            int size4 = size();
            int size5 = t7.size();
            byte[] bArr = new byte[size4 + size5];
            a(bArr, 0, 0, size4);
            t7.a(bArr, 0, size4, size5);
            return new CL(bArr);
        }
        if (c2692ta0 != null) {
            if (t7.size() + c2692ta0.f.size() < 128) {
                T7 t8 = c2692ta0.f;
                int size6 = t8.size();
                int size7 = t7.size();
                byte[] bArr2 = new byte[size6 + size7];
                t8.a(bArr2, 0, 0, size6);
                t7.a(bArr2, 0, size6, size7);
                return new C2692ta0(c2692ta0.e, new CL(bArr2));
            }
        }
        if (c2692ta0 != null && c2692ta0.e.a() > c2692ta0.f.a() && c2692ta0.h > t7.a()) {
            return new C2692ta0(c2692ta0.e, new C2692ta0(c2692ta0.f, t7));
        }
        if (size3 >= C2692ta0.j[Math.max(a(), t7.a()) + 1]) {
            return new C2692ta0(this, t7);
        }
        C2436qa0 c2436qa0 = new C2436qa0();
        c2436qa0.a(this);
        c2436qa0.a(t7);
        T7 c2692ta1 = (T7) c2436qa0.a.pop();
        while (!c2436qa0.a.isEmpty()) {
            c2692ta1 = new C2692ta0((T7) c2436qa0.a.pop(), c2692ta1);
        }
        return c2692ta1;
    }

    public abstract void a(OutputStream outputStream, int i, int i2);

    public abstract int b(int i, int i2, int i3);

    public abstract void b(byte[] bArr, int i, int i2, int i3);

    public abstract boolean b();

    public abstract boolean c();

    public abstract int d();

    public abstract String e();

    public final String g() {
        try {
            return e();
        } catch (UnsupportedEncodingException e) {
            g3c.a("UTF-8 not supported?", e);
            return null;
        }
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    public static CL a(String str) {
        try {
            return new CL(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            g3c.a("UTF-8 not supported?", e);
            return null;
        }
    }

    public static T7 a(int i, Iterator it) {
        if (!c && i < 1) {
            x1f.a();
            return null;
        }
        if (i == 1) {
            return (T7) it.next();
        }
        int i2 = i >>> 1;
        return a(i2, it).a(a(i - i2, it));
    }

    public final void a(byte[] bArr, int i, int i2, int i3) {
        if (i < 0) {
            f0c.a(30, "Source offset < 0: ", i);
            return;
        }
        if (i2 < 0) {
            f0c.a(30, "Target offset < 0: ", i2);
            return;
        }
        if (i3 >= 0) {
            int i4 = i + i3;
            if (i4 <= size()) {
                int i5 = i2 + i3;
                if (i5 > bArr.length) {
                    f0c.a(34, "Target end offset < 0: ", i5);
                    return;
                } else {
                    if (i3 > 0) {
                        b(bArr, i, i2, i3);
                        return;
                    }
                    return;
                }
            }
            f0c.a(34, "Source end offset < 0: ", i4);
            return;
        }
        f0c.a(23, "Length < 0: ", i3);
    }
}
