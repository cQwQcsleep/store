package com.android.tools.r8.internal;

import defpackage.m71;
import defpackage.n71;
import defpackage.o71;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CL extends T7 {
    public final byte[] d;
    public int e = 0;

    public CL(byte[] bArr) {
        this.d = bArr;
    }

    public final boolean a(CL cl, int i, int i2) {
        byte[] bArr = cl.d;
        if (i2 > bArr.length) {
            o71.a(i2, this.d.length);
            return false;
        }
        if (i + i2 > bArr.length) {
            n71.a(i, i2, bArr.length);
            return false;
        }
        byte[] bArr2 = this.d;
        int i3 = 0;
        while (i3 < i2) {
            if (bArr2[i3] != bArr[i]) {
                return false;
            }
            i3++;
            i++;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
    
        if (r7[r9] > (-65)) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001c, code lost:
    
        r9 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004f, code lost:
    
        if (r7[r9] > (-65)) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x009a, code lost:
    
        if (r7[r8] > (-65)) goto L64;
     */
    @Override // com.android.tools.r8.internal.T7
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int b(int i, int i2, int i3) {
        byte b;
        int i4;
        int i5;
        byte[] bArr = this.d;
        int i6 = i3 + i2;
        if (i != 0) {
            if (i2 >= i6) {
                return i;
            }
            byte b2 = (byte) i;
            if (b2 < -32) {
                if (b2 >= -62) {
                    i5 = i2 + 1;
                }
                return -1;
            }
            if (b2 < -16) {
                byte b3 = (byte) (~(i >> 8));
                if (b3 == 0) {
                    int i7 = i2 + 1;
                    byte b4 = bArr[i2];
                    if (i7 >= i6) {
                        if (b2 <= -12 && b4 <= -65) {
                            return (b4 << 8) ^ b2;
                        }
                        return -1;
                    }
                    i2 = i7;
                    b3 = b4;
                }
                if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                    i5 = i2 + 1;
                }
                return -1;
            }
            byte b5 = (byte) (~(i >> 8));
            if (b5 == 0) {
                i4 = i2 + 1;
                b5 = bArr[i2];
                if (i4 < i6) {
                    b = 0;
                } else if (b2 <= -12 && b5 <= -65) {
                    return (b5 << 8) ^ b2;
                }
                return -1;
            }
            b = (byte) (i >> 16);
            i4 = i2;
            if (b == 0) {
                int i8 = i4 + 1;
                byte b6 = bArr[i4];
                if (i8 >= i6) {
                    if (b2 <= -12 && b5 <= -65 && b6 <= -65) {
                        return ((b5 << 8) ^ b2) ^ (b6 << 16);
                    }
                    return -1;
                }
                b = b6;
                i4 = i8;
            }
            if (b5 <= -65) {
                if ((((b5 + 112) + (b2 << 28)) >> 30) == 0 && b <= -65) {
                    i2 = i4 + 1;
                }
            }
            return -1;
        }
        return AbstractC2115ml0.b(bArr, i2, i6);
    }

    @Override // com.android.tools.r8.internal.T7
    public final boolean c() {
        byte[] bArr = this.d;
        return AbstractC2115ml0.b(bArr, 0, bArr.length) == 0;
    }

    @Override // com.android.tools.r8.internal.T7
    public final int d() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.T7
    public final String e() {
        byte[] bArr = this.d;
        return new String(bArr, 0, bArr.length, "UTF-8");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof T7) || size() != ((T7) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof CL) {
            return a((CL) obj, 0, size());
        }
        if (obj instanceof C2692ta0) {
            return obj.equals(this);
        }
        String strValueOf = String.valueOf(obj.getClass());
        m71.a(strValueOf.length() + 49, strValueOf);
        return false;
    }

    public final int hashCode() {
        int iA = this.e;
        if (iA == 0) {
            int size = size();
            iA = a(size, 0, size);
            if (iA == 0) {
                iA = 1;
            }
            this.e = iA;
        }
        return iA;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new BL(this);
    }

    @Override // com.android.tools.r8.internal.T7
    public int size() {
        return this.d.length;
    }

    @Override // com.android.tools.r8.internal.T7
    public final int a() {
        return 0;
    }

    @Override // com.android.tools.r8.internal.T7
    public final void a(OutputStream outputStream, int i, int i2) throws IOException {
        outputStream.write(this.d, i, i2);
    }

    @Override // com.android.tools.r8.internal.T7
    public final int a(int i, int i2, int i3) {
        byte[] bArr = this.d;
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    @Override // com.android.tools.r8.internal.T7
    public void b(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.d, i, bArr, i2, i3);
    }

    @Override // com.android.tools.r8.internal.T7
    public final boolean b() {
        return true;
    }
}
