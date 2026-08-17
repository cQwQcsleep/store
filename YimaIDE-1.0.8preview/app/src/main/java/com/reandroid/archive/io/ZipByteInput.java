package com.reandroid.archive.io;

import com.reandroid.common.BytesInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipByteInput extends ZipInput {
    private final byte[] array;
    private final int length;
    private final int offset;
    private int position;

    public ZipByteInput(byte[] bArr, int i, int i2) {
        i = i >= bArr.length ? bArr.length - 1 : i;
        i = i < 0 ? 0 : i;
        int length = bArr.length - i;
        i2 = i2 > length ? length : i2;
        this.array = bArr;
        this.offset = i;
        this.length = i2;
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.position = this.length;
    }

    @Override // com.reandroid.archive.io.ZipInput
    public byte[] getFooter(int i) {
        if (i <= 0) {
            return new byte[0];
        }
        int i2 = this.length;
        if (i > i2) {
            if (this.offset == 0) {
                return (byte[]) this.array.clone();
            }
            i = i2;
        }
        byte[] bArr = new byte[i];
        byte[] bArr2 = this.array;
        System.arraycopy(bArr2, (bArr2.length - this.offset) - i, bArr, 0, i);
        return bArr;
    }

    @Override // com.reandroid.archive.io.ReadOnlyStream
    public InputStream getInputStream(long j, long j2) throws IOException {
        return new BytesInputStream(this.array, (int) (j + ((long) this.offset)), (int) j2);
    }

    @Override // com.reandroid.archive.io.ReadOnlyStream
    public long getLength() throws IOException {
        return this.length;
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // com.reandroid.archive.io.RandomStream
    public void position(long j) throws IOException {
        if (j > getLength()) {
            j = getLength() - 1;
        }
        if (j < 0) {
            j = 0;
        }
        this.position = (int) j;
    }

    public ZipByteInput(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    @Override // com.reandroid.archive.io.RandomStream
    public long position() throws IOException {
        return this.position;
    }
}
