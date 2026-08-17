package com.android.tools.r8.internal;

import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2437qb extends Reader {
    public CharSequence a;
    public int b;
    public int c;

    public C2437qb(CharSequence charSequence) {
        charSequence.getClass();
        this.a = charSequence;
    }

    public final void c() {
        if (this.a != null) {
            return;
        }
        a16.a("reader closed");
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.a = null;
    }

    @Override // java.io.Reader
    public final synchronized void mark(int i) {
        try {
            if (!(i >= 0)) {
                throw new IllegalArgumentException(Xf0.a("readAheadLimit (%s) may not be negative", new Object[]{Integer.valueOf(i)}));
            }
            c();
            this.c = this.b;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Reader
    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader
    public final synchronized int read(char[] cArr, int i, int i2) {
        DX.a(i, i + i2, cArr.length);
        c();
        Objects.requireNonNull(this.a);
        Objects.requireNonNull(this.a);
        if (this.a.length() - this.b <= 0) {
            return -1;
        }
        Objects.requireNonNull(this.a);
        int iMin = Math.min(i2, this.a.length() - this.b);
        for (int i3 = 0; i3 < iMin; i3++) {
            CharSequence charSequence = this.a;
            int i4 = this.b;
            this.b = i4 + 1;
            cArr[i + i3] = charSequence.charAt(i4);
        }
        return iMin;
    }

    @Override // java.io.Reader
    public final synchronized boolean ready() {
        c();
        return true;
    }

    @Override // java.io.Reader
    public final synchronized void reset() {
        c();
        this.b = this.c;
    }

    @Override // java.io.Reader
    public final synchronized long skip(long j) {
        int iMin;
        try {
            if (!(j >= 0)) {
                throw new IllegalArgumentException(Xf0.a("n (%s) may not be negative", new Object[]{Long.valueOf(j)}));
            }
            c();
            Objects.requireNonNull(this.a);
            iMin = (int) Math.min(this.a.length() - this.b, j);
            this.b += iMin;
        } catch (Throwable th) {
            throw th;
        }
        return iMin;
    }

    @Override // java.io.Reader
    public final synchronized int read() {
        int iCharAt;
        c();
        Objects.requireNonNull(this.a);
        Objects.requireNonNull(this.a);
        int length = this.a.length();
        int i = this.b;
        if (length - i > 0) {
            CharSequence charSequence = this.a;
            this.b = i + 1;
            iCharAt = charSequence.charAt(i);
        } else {
            iCharAt = -1;
        }
        return iCharAt;
    }

    @Override // java.io.Reader, java.lang.Readable
    public final synchronized int read(CharBuffer charBuffer) {
        charBuffer.getClass();
        c();
        Objects.requireNonNull(this.a);
        Objects.requireNonNull(this.a);
        if (this.a.length() - this.b <= 0) {
            return -1;
        }
        int iRemaining = charBuffer.remaining();
        Objects.requireNonNull(this.a);
        int iMin = Math.min(iRemaining, this.a.length() - this.b);
        for (int i = 0; i < iMin; i++) {
            CharSequence charSequence = this.a;
            int i2 = this.b;
            this.b = i2 + 1;
            charBuffer.put(charSequence.charAt(i2));
        }
        return iMin;
    }
}
