package np.protect.assets.p;

import java.io.IOException;
import java.io.InputStream;
import obfuse.NPStringFog;

/* renamed from: np.protect.assets.p.۟۟, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0008 extends InputStream {

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    private int[] f17;

    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    private int f18 = 0;

    /* renamed from: ۟۟۟, reason: not valid java name and contains not printable characters */
    private boolean f19 = false;

    /* renamed from: ۟۟۟۟, reason: not valid java name and contains not printable characters */
    private InputStream f20;

    public C0008(InputStream inputStream) {
        this.f20 = inputStream;
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    private void m342() throws IOException {
        String strDecode;
        int i;
        char[] cArr = new char[4];
        int i2 = 0;
        do {
            int i3 = this.f20.read();
            strDecode = NPStringFog.decode("2C1109410C001400445A501E151C040608");
            i = 1;
            if (i3 == -1) {
                if (i2 != 0) {
                    throw new IOException(strDecode);
                }
                this.f17 = new int[0];
                this.f19 = true;
                return;
            }
            char c = (char) i3;
            if (C0009.f21.indexOf(c) != -1 || c == C0009.f22) {
                cArr[i2] = c;
                i2++;
            } else if (c != '\r' && c != '\n') {
                throw new IOException(strDecode);
            }
        } while (i2 < 4);
        boolean z = false;
        for (int i4 = 0; i4 < 4; i4++) {
            if (cArr[i4] != C0009.f22) {
                if (z) {
                    throw new IOException(strDecode);
                }
            } else if (!z) {
                z = true;
            }
        }
        if (cArr[3] != C0009.f22) {
            i = 3;
        } else {
            if (this.f20.read() != -1) {
                throw new IOException(strDecode);
            }
            this.f19 = true;
            if (cArr[2] != C0009.f22) {
                i = 2;
            }
        }
        int iIndexOf = 0;
        for (int i5 = 0; i5 < 4; i5++) {
            if (cArr[i5] != C0009.f22) {
                iIndexOf |= C0009.f21.indexOf(cArr[i5]) << ((3 - i5) * 6);
            }
        }
        this.f17 = new int[i];
        for (int i6 = 0; i6 < i; i6++) {
            this.f17[i6] = (iIndexOf >>> ((2 - i6) * 8)) & 255;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f20.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int[] iArr = this.f17;
        if (iArr == null || this.f18 == iArr.length) {
            if (this.f19) {
                return -1;
            }
            m342();
            if (this.f17.length == 0) {
                this.f17 = null;
                return -1;
            }
            this.f18 = 0;
        }
        int[] iArr2 = this.f17;
        int i = this.f18;
        this.f18 = i + 1;
        return iArr2[i];
    }
}
