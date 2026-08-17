package org.jline.utils;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public abstract class NonBlockingReader extends Reader {
    public int peek(long j) throws IOException {
        return read(j, true);
    }

    public abstract int read(long j, boolean z) throws IOException;

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        cArr.getClass();
        if (i < 0 || i2 < 0 || i2 > cArr.length - i) {
            qc6.a();
            return 0;
        }
        if (i2 == 0) {
            return 0;
        }
        int i3 = read(0L);
        if (i3 == -1) {
            return -1;
        }
        cArr[i] = (char) i3;
        return 1;
    }

    public abstract int readBuffered(char[] cArr, int i, int i2, long j) throws IOException;

    public int readBuffered(char[] cArr, long j) throws IOException {
        return readBuffered(cArr, 0, cArr.length, j);
    }

    public int readBuffered(char[] cArr) throws IOException {
        return readBuffered(cArr, 0L);
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        return read(0L, false);
    }

    public int read(long j) throws IOException {
        return read(j, false);
    }
}
