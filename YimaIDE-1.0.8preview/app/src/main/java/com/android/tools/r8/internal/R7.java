package com.android.tools.r8.internal;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R7 extends OutputStream {
    public static final byte[] f = new byte[0];
    public int c;
    public int e;
    public final int a = 128;
    public final ArrayList b = new ArrayList();
    public byte[] d = new byte[128];

    public final synchronized T7 c() {
        T7 t7A;
        try {
            int i = this.e;
            byte[] bArr = this.d;
            if (i >= bArr.length) {
                this.b.add(new CL(bArr));
                this.d = f;
            } else if (i > 0) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i));
                this.b.add(new CL(bArr2));
            }
            this.c += this.e;
            this.e = 0;
            ArrayList arrayList = this.b;
            if (arrayList == null) {
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add((T7) it.next());
                }
                arrayList = arrayList2;
            }
            if (arrayList.isEmpty()) {
                t7A = T7.b;
            } else {
                t7A = T7.a(arrayList.size(), arrayList.iterator());
            }
        } catch (Throwable th) {
            throw th;
        }
        return t7A;
    }

    public final String toString() {
        int i;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        synchronized (this) {
            i = this.c + this.e;
        }
        return String.format("<ByteString.Output@%s size=%d>", hexString, Integer.valueOf(i));
    }

    @Override // java.io.OutputStream
    public final synchronized void write(byte[] bArr, int i, int i2) {
        try {
            byte[] bArr2 = this.d;
            int length = bArr2.length;
            int i3 = this.e;
            if (i2 <= length - i3) {
                System.arraycopy(bArr, i, bArr2, i3, i2);
                this.e += i2;
            } else {
                int length2 = bArr2.length - i3;
                System.arraycopy(bArr, i, bArr2, i3, length2);
                int i4 = i2 - length2;
                this.b.add(new CL(this.d));
                int length3 = this.c + this.d.length;
                this.c = length3;
                byte[] bArr3 = new byte[Math.max(this.a, Math.max(i4, length3 >>> 1))];
                this.d = bArr3;
                this.e = 0;
                System.arraycopy(bArr, i + length2, bArr3, 0, i4);
                this.e = i4;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.OutputStream
    public final synchronized void write(int i) {
        try {
            int i2 = this.e;
            byte[] bArr = this.d;
            if (i2 == bArr.length) {
                this.b.add(new CL(bArr));
                int length = this.c + this.d.length;
                this.c = length;
                this.d = new byte[Math.max(this.a, Math.max(1, length >>> 1))];
                this.e = 0;
            }
            byte[] bArr2 = this.d;
            int i3 = this.e;
            this.e = i3 + 1;
            bArr2[i3] = (byte) i;
        } catch (Throwable th) {
            throw th;
        }
    }
}
