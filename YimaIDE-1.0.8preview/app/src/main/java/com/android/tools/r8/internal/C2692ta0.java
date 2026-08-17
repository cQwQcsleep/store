package com.android.tools.r8.internal;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ta0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2692ta0 extends T7 {
    public static final int[] j;
    public final int d;
    public final T7 e;
    public final T7 f;
    public final int g;
    public final int h;
    public int i = 0;

    static {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 1;
        while (i > 0) {
            arrayList.add(Integer.valueOf(i));
            int i3 = i2 + i;
            i2 = i;
            i = i3;
        }
        arrayList.add(Integer.MAX_VALUE);
        j = new int[arrayList.size()];
        int i4 = 0;
        while (true) {
            int[] iArr = j;
            if (i4 >= iArr.length) {
                return;
            }
            iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
            i4++;
        }
    }

    public C2692ta0(T7 t7, T7 t8) {
        this.e = t7;
        this.f = t8;
        int size = t7.size();
        this.g = size;
        this.d = t8.size() + size;
        this.h = Math.max(t7.a(), t8.a()) + 1;
    }

    @Override // com.android.tools.r8.internal.T7
    public final int a(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.g;
        if (i4 <= i5) {
            return this.e.a(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.f.a(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.f.a(this.e.a(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.android.tools.r8.internal.T7
    public final int b(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.g;
        if (i4 <= i5) {
            return this.e.b(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.f.b(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.f.b(this.e.b(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.android.tools.r8.internal.T7
    public final boolean c() {
        int iB = this.e.b(0, 0, this.g);
        T7 t7 = this.f;
        return t7.b(iB, 0, t7.size()) == 0;
    }

    @Override // com.android.tools.r8.internal.T7
    public final int d() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.T7
    public final String e() {
        byte[] bArr;
        int i = this.d;
        if (i == 0) {
            bArr = AbstractC1470fB.a;
        } else {
            byte[] bArr2 = new byte[i];
            b(bArr2, 0, 0, i);
            bArr = bArr2;
        }
        return new String(bArr, "UTF-8");
    }

    public final boolean equals(Object obj) {
        int iD;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof T7)) {
            return false;
        }
        T7 t7 = (T7) obj;
        if (this.d != t7.size()) {
            return false;
        }
        if (this.d == 0) {
            return true;
        }
        if (this.i != 0 && (iD = t7.d()) != 0 && this.i != iD) {
            return false;
        }
        C2521ra0 c2521ra0 = new C2521ra0(this);
        CL clA = c2521ra0.next();
        C2521ra0 c2521ra1 = new C2521ra0(t7);
        CL clA2 = c2521ra1.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int length = clA.d.length - i;
            int length2 = clA2.d.length - i2;
            int iMin = Math.min(length, length2);
            if (!(i == 0 ? clA.a(clA2, i2, iMin) : clA2.a(clA, i, iMin))) {
                return false;
            }
            i3 += iMin;
            int i4 = this.d;
            if (i3 >= i4) {
                if (i3 == i4) {
                    return true;
                }
                g33.a();
                return false;
            }
            if (iMin == length) {
                clA = c2521ra0.next();
                i = 0;
            } else {
                i += iMin;
            }
            if (iMin == length2) {
                clA2 = c2521ra1.next();
                i2 = 0;
            } else {
                i2 += iMin;
            }
        }
    }

    public final int hashCode() {
        int iA = this.i;
        if (iA == 0) {
            int i = this.d;
            iA = a(i, 0, i);
            if (iA == 0) {
                iA = 1;
            }
            this.i = iA;
        }
        return iA;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new C2606sa0(this);
    }

    @Override // com.android.tools.r8.internal.T7
    public final int size() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.T7
    public final void a(OutputStream outputStream, int i, int i2) {
        int i3 = i + i2;
        int i4 = this.g;
        if (i3 <= i4) {
            this.e.a(outputStream, i, i2);
        } else {
            if (i >= i4) {
                this.f.a(outputStream, i - i4, i2);
                return;
            }
            int i5 = i4 - i;
            this.e.a(outputStream, i, i5);
            this.f.a(outputStream, 0, i2 - i5);
        }
    }

    @Override // com.android.tools.r8.internal.T7
    public final void b(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.g;
        if (i4 <= i5) {
            this.e.b(bArr, i, i2, i3);
        } else {
            if (i >= i5) {
                this.f.b(bArr, i - i5, i2, i3);
                return;
            }
            int i6 = i5 - i;
            this.e.b(bArr, i, i2, i6);
            this.f.b(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    @Override // com.android.tools.r8.internal.T7
    public final int a() {
        return this.h;
    }

    @Override // com.android.tools.r8.internal.T7
    public final boolean b() {
        return this.d >= j[this.h];
    }
}
