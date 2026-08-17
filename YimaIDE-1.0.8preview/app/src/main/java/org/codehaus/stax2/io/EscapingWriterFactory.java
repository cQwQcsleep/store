package org.codehaus.stax2.io;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface EscapingWriterFactory {
    Writer createEscapingWriterFor(OutputStream outputStream, String str) throws UnsupportedEncodingException;

    Writer createEscapingWriterFor(Writer writer, String str) throws UnsupportedEncodingException;
}
