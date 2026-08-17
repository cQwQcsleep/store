package org.codehaus.stax2.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Stax2URLSource extends Stax2ReferentialSource {
    final URL mURL;

    public Stax2URLSource(URL url) {
        this.mURL = url;
    }

    @Override // org.codehaus.stax2.io.Stax2ReferentialSource, org.codehaus.stax2.io.Stax2Source
    public InputStream constructInputStream() throws IOException {
        return "file".equals(this.mURL.getProtocol()) ? new FileInputStream(this.mURL.getPath()) : this.mURL.openStream();
    }

    @Override // org.codehaus.stax2.io.Stax2ReferentialSource, org.codehaus.stax2.io.Stax2Source
    public Reader constructReader() throws IOException {
        String encoding = getEncoding();
        return (encoding == null || encoding.length() <= 0) ? new InputStreamReader(constructInputStream()) : new InputStreamReader(constructInputStream(), encoding);
    }

    @Override // org.codehaus.stax2.io.Stax2ReferentialSource, org.codehaus.stax2.io.Stax2Source
    public URL getReference() {
        return this.mURL;
    }
}
