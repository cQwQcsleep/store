package org.w3c.dom.ls;

import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LSOutput {
    OutputStream getByteStream();

    Writer getCharacterStream();

    String getEncoding();

    String getSystemId();

    void setByteStream(OutputStream outputStream);

    void setCharacterStream(Writer writer);

    void setEncoding(String str);

    void setSystemId(String str);
}
