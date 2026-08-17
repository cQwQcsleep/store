package org.bouncycastle.cms;

import defpackage.k2d;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class InputStreamWithMAC extends InputStream {
    private final InputStream base;
    private boolean baseFinished = false;
    private int index = 0;
    private byte[] mac;
    private MACProvider macProvider;

    public InputStreamWithMAC(InputStream inputStream, MACProvider mACProvider) {
        this.base = inputStream;
        this.macProvider = mACProvider;
    }

    public byte[] getMAC() {
        if (this.baseFinished) {
            return Arrays.clone(this.mac);
        }
        k2d.a("input stream not fully processed");
        return null;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            x0e.a("input array is null");
            return 0;
        }
        if (i < 0 || bArr.length < i + i2) {
            qnd.a("invalid off(", i, ") and len(", i2, ")");
            return 0;
        }
        if (this.baseFinished) {
            int i3 = this.index;
            byte[] bArr2 = this.mac;
            if (i3 >= bArr2.length) {
                return -1;
            }
            if (i2 >= bArr2.length - i3) {
                System.arraycopy(bArr2, i3, bArr, i, bArr2.length - i3);
                byte[] bArr3 = this.mac;
                int length = bArr3.length - this.index;
                this.index = bArr3.length;
                return length;
            }
            System.arraycopy(bArr2, i3, bArr, i, i2);
        } else {
            int i4 = this.base.read(bArr, i, i2);
            if (i4 >= 0) {
                return i4;
            }
            this.baseFinished = true;
            MACProvider mACProvider = this.macProvider;
            if (mACProvider != null) {
                mACProvider.init();
                this.mac = this.macProvider.getMAC();
            }
            byte[] bArr4 = this.mac;
            if (i2 >= bArr4.length) {
                System.arraycopy(bArr4, 0, bArr, i, bArr4.length);
                byte[] bArr5 = this.mac;
                this.index = bArr5.length;
                return bArr5.length;
            }
            System.arraycopy(bArr4, 0, bArr, i, i2);
        }
        this.index += i2;
        return i2;
    }

    public InputStreamWithMAC(InputStream inputStream, byte[] bArr) {
        this.base = inputStream;
        this.mac = bArr;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte b;
        if (this.baseFinished) {
            int i = this.index;
            byte[] bArr = this.mac;
            if (i >= bArr.length) {
                return -1;
            }
            this.index = i + 1;
            b = bArr[i];
        } else {
            int i2 = this.base.read();
            if (i2 >= 0) {
                return i2;
            }
            this.baseFinished = true;
            MACProvider mACProvider = this.macProvider;
            if (mACProvider != null) {
                mACProvider.init();
                this.mac = this.macProvider.getMAC();
            }
            byte[] bArr2 = this.mac;
            int i3 = this.index;
            this.index = i3 + 1;
            b = bArr2[i3];
        }
        return b & 255;
    }
}
