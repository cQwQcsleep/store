package org.codehaus.stax2.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.transform.Source;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class Stax2Source implements Source {
    protected String mEncoding;
    protected String mPublicId;
    protected String mSystemId;

    public abstract InputStream constructInputStream() throws IOException;

    public abstract Reader constructReader() throws IOException;

    public String getEncoding() {
        return this.mEncoding;
    }

    public String getPublicId() {
        return this.mPublicId;
    }

    public abstract URL getReference();

    @Override // javax.xml.transform.Source
    public String getSystemId() {
        return this.mSystemId;
    }

    public void setEncoding(String str) {
        this.mEncoding = str;
    }

    public void setPublicId(String str) {
        this.mPublicId = str;
    }

    @Override // javax.xml.transform.Source
    public void setSystemId(String str) {
        this.mSystemId = str;
    }
}
