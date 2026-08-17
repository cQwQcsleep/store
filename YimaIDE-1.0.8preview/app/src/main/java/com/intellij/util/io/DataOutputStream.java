package com.intellij.util.io;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class DataOutputStream extends java.io.DataOutputStream {
    public DataOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public int getWrittenBytesCount() {
        return ((java.io.DataOutputStream) this).written;
    }

    public int resetWrittenBytesCount() {
        int i = ((java.io.DataOutputStream) this).written;
        ((java.io.DataOutputStream) this).written = 0;
        return i;
    }

    @Override // java.io.DataOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.DataOutput
    public void write(int i) throws IOException {
        ((java.io.DataOutputStream) this).out.write(i);
        ((java.io.DataOutputStream) this).written++;
    }

    @Override // java.io.DataOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws IOException {
        ((java.io.DataOutputStream) this).out.write(bArr, i, i2);
        ((java.io.DataOutputStream) this).written += i2;
    }
}
