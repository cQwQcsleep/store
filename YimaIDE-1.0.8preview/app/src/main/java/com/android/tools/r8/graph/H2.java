package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC1613gu;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.UK;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.g3c;
import java.io.UTFDataFormatException;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class H2 extends X3 implements InterfaceC0221h5, UK {
    public static final H2[] g = new H2[0];
    public static final /* synthetic */ boolean h = true;
    public final int e;
    public final byte[] f;

    public H2(String str) {
        this.e = str.length();
        this.f = a(str);
    }

    public static UTFDataFormatException e(int i, int i2) {
        return new UTFDataFormatException("bad second byte (first: " + Integer.toHexString((char) (i & 255)) + ", second: " + Integer.toHexString((char) (i2 & 255)) + ")");
    }

    public static UTFDataFormatException k(int i) {
        return new UTFDataFormatException("bad byte: " + Integer.toHexString((char) (i & 255)) + ")");
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0034  */
    public final H2 a(H2 h2, H2 h3, B1 b1) {
        boolean z;
        byte[] bArr;
        if (!h && (!h2.c("L") || !h3.c("L"))) {
            x1f.a();
            return null;
        }
        if (h2.equals(h3)) {
            return this;
        }
        if (h2.e == 1) {
            H2 h4 = b1.F;
            h3.getClass();
            if (h3.a(h4.f)) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        boolean z2 = h3.e == 1 && !h2.a(b1.F.f);
        int i = z ? 1 : z2 ? -1 : 0;
        int i2 = 0;
        while (true) {
            bArr = this.f;
            if (bArr[i2] != 91) {
                break;
            }
            i2++;
        }
        int i3 = ((h3.e + this.e) - h2.e) + i;
        byte[] bArr2 = new byte[((h3.f.length + bArr.length) - h2.f.length) + i];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr2[i4] = 91;
        }
        byte[] bArr3 = h3.f;
        System.arraycopy(bArr3, 0, bArr2, i2, bArr3.length - 1);
        int length = h2.f.length;
        int i5 = length - 1;
        int length2 = h3.f.length;
        int i6 = length2 - 1;
        if (z2) {
            length2 = i6;
        } else if (z) {
            bArr2[i6] = 47;
            length = i5;
        } else {
            length = i5;
            length2 = i6;
        }
        byte[] bArr4 = this.f;
        System.arraycopy(bArr4, length, bArr2, length2, bArr4.length - length);
        return b1.a(i3, bArr2);
    }

    public boolean b(String str) {
        if (this.e != str.length()) {
            return false;
        }
        G2 g2 = new G2(this);
        int i = 0;
        while (g2.a()) {
            try {
                if (g2.b() != str.charAt(i)) {
                    return false;
                }
                i++;
            } catch (UTFDataFormatException unused) {
                return false;
            }
        }
        if (h || i == this.e) {
            return true;
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        if (obj instanceof H2) {
            H2 h2 = (H2) obj;
            if (this.e == h2.e && Arrays.equals(this.f, h2.f)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        return Arrays.hashCode(this.f) + (this.e * 7);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    public final String o0() {
        char[] cArr = new char[this.e];
        return new String(cArr, 0, a(cArr));
    }

    public final int p0() throws UTFDataFormatException {
        byte b;
        int i;
        int i2;
        if (this.e == 0) {
            if (!h && o0().hashCode() != 0) {
                x1f.a();
            }
            return 0;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            byte[] bArr = this.f;
            int i5 = i3 + 1;
            char c = (char) (bArr[i3] & 255);
            if (c == 0) {
                if (h || i4 == o0().hashCode()) {
                    return i4;
                }
                x1f.a();
                return 0;
            }
            if (c < 128) {
                i4 = (i4 * 31) + c;
                i3 = i5;
            } else {
                if ((c & 224) == 192) {
                    i3 += 2;
                    b = bArr[i5];
                    int i6 = b & 255;
                    if ((b & 192) != 128) {
                        throw e(c, i6);
                    }
                    i = i4 * 31;
                    i2 = (c & 31) << 6;
                } else {
                    if ((c & 240) != 224) {
                        throw k(c);
                    }
                    int i7 = i3 + 2;
                    byte b2 = bArr[i5];
                    int i8 = b2 & 255;
                    i3 += 3;
                    b = bArr[i7];
                    int i9 = b & 255;
                    if ((b2 & 192) != 128 || (b & 192) != 128) {
                        throw a(c, i8, i9);
                    }
                    i = i4 * 31;
                    i2 = ((b2 & 63) << 6) | ((c & 15) << 12);
                }
                i4 = i + ((char) ((b & 63) | i2));
            }
        }
    }

    public String q0() {
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        sb.append(" [");
        for (int i = 0; i < this.f.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(Integer.toHexString(this.f[i] & 255));
        }
        sb.append("]");
        return sb.toString();
    }

    public final char r0() {
        return (char) this.f[0];
    }

    public final boolean s0() {
        try {
            return C0929Wj.C(o0());
        } catch (UTFDataFormatException unused) {
            return false;
        }
    }

    public final boolean t0() {
        try {
            return C0929Wj.E(o0());
        } catch (UTFDataFormatException unused) {
            return false;
        }
    }

    public String toString() {
        try {
            return o0();
        } catch (UTFDataFormatException e) {
            g3c.a("Bad format", e);
            return null;
        }
    }

    public final boolean u0() {
        try {
            return C0929Wj.G(o0());
        } catch (UTFDataFormatException unused) {
            return false;
        }
    }

    public String v0() {
        try {
            return Wf0.h(o0());
        } catch (UTFDataFormatException e) {
            g3c.a("Bad format", e);
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 1;
    }

    public H2(int i, byte[] bArr) {
        this.e = i;
        this.f = bArr;
    }

    public final boolean c(EnumC3077y2 enumC3077y2) {
        if (!enumC3077y2.e(EnumC3077y2.F)) {
            return true;
        }
        try {
            return a(enumC3077y2, o0());
        } catch (UTFDataFormatException unused) {
            return false;
        }
    }

    public boolean c(String str) {
        return b(a(str));
    }

    public final boolean b(H2 h2) {
        return this == h2;
    }

    @Override // com.android.tools.r8.internal.UK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.f);
    }

    public final boolean b(byte[] bArr) {
        if (this.f.length < bArr.length) {
            return false;
        }
        for (int i = 0; i < bArr.length - 1; i++) {
            if (this.f[i] != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.f);
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (H2) uk);
    }

    public final int a(char[] cArr) throws UTFDataFormatException {
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f;
            int i3 = i + 1;
            char c = (char) (bArr[i] & 255);
            if (c == 0) {
                return i2;
            }
            cArr[i2] = c;
            if (c < 128) {
                i2++;
                if (i2 == length) {
                    return i2;
                }
                i = i3;
            } else if ((c & 224) == 192) {
                i += 2;
                byte b = bArr[i3];
                int i4 = b & 255;
                if ((b & 192) == 128) {
                    cArr[i2] = (char) ((b & 63) | ((c & 31) << 6));
                    i2++;
                    if (i2 == length) {
                        return i2;
                    }
                } else {
                    throw e(c, i4);
                }
            } else if ((c & 240) == 224) {
                int i5 = i + 2;
                byte b2 = bArr[i3];
                int i6 = b2 & 255;
                i += 3;
                byte b3 = bArr[i5];
                int i7 = b3 & 255;
                if ((b2 & 192) == 128 && (b3 & 192) == 128) {
                    cArr[i2] = (char) ((b3 & 63) | ((b2 & 63) << 6) | ((c & 15) << 12));
                    i2++;
                    if (i2 == length) {
                        return i2;
                    }
                } else {
                    throw a(c, i6, i7);
                }
            } else {
                throw k(c);
            }
        }
    }

    public static UTFDataFormatException a(int i, int i2, int i3) {
        return new UTFDataFormatException("bad second or third byte (first: " + Integer.toHexString((char) (i & 255)) + ", second: " + Integer.toHexString((char) (i2 & 255)) + ", third: " + Integer.toHexString((char) (i3 & 255)) + ")");
    }

    public static byte[] a(String str) {
        int i = 1;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            i += (cCharAt == 0 || cCharAt > 127) ? cCharAt <= 2047 ? 2 : 3 : 1;
            if (!h && i <= 0) {
                x1f.a();
                return null;
            }
        }
        byte[] bArr = new byte[i];
        int iA = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            iA = a(str.charAt(i3), bArr, iA);
        }
        bArr[iA] = 0;
        return bArr;
    }

    public static int a(char c, byte[] bArr, int i) {
        if (c != 0 && c <= 127) {
            int i2 = i + 1;
            bArr[i] = (byte) c;
            return i2;
        }
        if (c <= 2047) {
            int i3 = i + 1;
            bArr[i] = (byte) (((c >> 6) & 31) | 192);
            int i4 = i + 2;
            bArr[i3] = (byte) ((c & '?') | 128);
            return i4;
        }
        bArr[i] = (byte) (((c >> '\f') & 15) | 224);
        int i5 = i + 2;
        bArr[i + 1] = (byte) (((c >> 6) & 63) | 128);
        int i6 = i + 3;
        bArr[i5] = (byte) ((c & '?') | 128);
        return i6;
    }

    @Override // com.android.tools.r8.graph.X3
    public final int a(C0284q5 c0284q5) {
        return C0284q5.a(this, c0284q5.j);
    }

    @Override // com.android.tools.r8.utils.structural.x, com.android.tools.r8.utils.structural.s, java.lang.Comparable
    public int compareTo(H2 h2) {
        int i = 0;
        while (true) {
            byte[] bArr = this.f;
            char c = (char) (bArr[i] & 255);
            byte[] bArr2 = h2.f;
            char c2 = (char) (bArr2[i] & 255);
            int i2 = c - c2;
            if (i2 != 0) {
                if (c == 0 || c2 == 0 || !((c == 192 && (bArr[i + 1] & 255) == 128) || (c2 == 192 && (bArr2[i + 1] & 255) == 128))) {
                    return i2;
                }
                return (c == 192 && (bArr[i + 1] & 255) == 128) ? -1 : 1;
            }
            if (c == 0) {
                return 0;
            }
            i++;
        }
    }

    public static boolean a(EnumC3077y2 enumC3077y2, String str) {
        if (!enumC3077y2.e(EnumC3077y2.F)) {
            return true;
        }
        int iCharCount = 0;
        while (iCharCount < str.length()) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (AbstractC1613gu.d(iCodePointAt)) {
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return true;
    }

    public final boolean a(byte[] bArr) {
        byte[] bArr2 = this.f;
        if (bArr2.length < bArr.length) {
            return false;
        }
        int length = bArr2.length - bArr.length;
        int i = 0;
        while (true) {
            byte[] bArr3 = this.f;
            if (length >= bArr3.length) {
                return true;
            }
            if (bArr3[length] != bArr[i]) {
                return false;
            }
            length++;
            i++;
        }
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (H2) xVar);
    }

    public final H2 a(int i, B1 b1) {
        byte[] bArr = new byte[this.f.length + i];
        Arrays.fill(bArr, 0, i, (byte) 91);
        byte[] bArr2 = this.f;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        return b1.a(this.e + i, bArr);
    }
}
