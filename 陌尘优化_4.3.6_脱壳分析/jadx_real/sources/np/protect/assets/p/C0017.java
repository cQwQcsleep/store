package np.protect.assets.p;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: np.protect.assets.p.ۣ۟۟۟۟, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0017 extends InputStream {

    /* renamed from: ۟۟۟۠ۤ, reason: not valid java name and contains not printable characters */
    private byte[] f40;

    /* renamed from: ۟۟۟ۡ۠, reason: not valid java name and contains not printable characters */
    private InputStream f41;

    public C0017(InputStream inputStream, byte[] bArr) {
        this.f41 = inputStream;
        this.f40 = bArr;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f41.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f41.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        synchronized (this) {
            this.f41.mark(i);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f41.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.f41.read();
        if (i == -1) {
            return -1;
        }
        if (C0010.f24) {
            return i;
        }
        return i ^ this.f40[r1.length - 1];
    }

    @Override // java.io.InputStream
    public void reset() {
        synchronized (this) {
            this.f41.reset();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return this.f41.skip(j);
    }
}
