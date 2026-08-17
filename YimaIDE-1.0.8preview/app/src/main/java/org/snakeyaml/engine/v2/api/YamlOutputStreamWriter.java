package org.snakeyaml.engine.v2.api;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class YamlOutputStreamWriter extends OutputStreamWriter implements StreamDataWriter {
    public YamlOutputStreamWriter(OutputStream outputStream, Charset charset) {
        super(outputStream, charset);
    }

    @Override // java.io.OutputStreamWriter, java.io.Writer, java.io.Flushable
    public void flush() {
        try {
            super.flush();
        } catch (IOException e) {
            processIOException(e);
        }
    }

    public void processIOException(IOException iOException) {
        throw new UncheckedIOException(iOException);
    }

    @Override // java.io.OutputStreamWriter, java.io.Writer
    public void write(String str, int i, int i2) {
        try {
            super.write(str, i, i2);
        } catch (IOException e) {
            processIOException(e);
        }
    }

    @Override // java.io.Writer
    public void write(String str) {
        try {
            super.write(str);
        } catch (IOException e) {
            processIOException(e);
        }
    }
}
