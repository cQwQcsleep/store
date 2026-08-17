package org.codehaus.stax2.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class Stax2Result implements Result {
    protected String mEncoding;
    protected String mPublicId;
    protected String mSystemId;

    public abstract OutputStream constructOutputStream() throws IOException;

    public abstract Writer constructWriter() throws IOException;

    public String getEncoding() {
        return this.mEncoding;
    }

    public String getPublicId() {
        return this.mPublicId;
    }

    @Override // javax.xml.transform.Result
    public String getSystemId() {
        return this.mSystemId;
    }

    public void setEncoding(String str) {
        this.mEncoding = str;
    }

    public void setPublicId(String str) {
        this.mPublicId = str;
    }

    @Override // javax.xml.transform.Result
    public void setSystemId(String str) {
        this.mSystemId = str;
    }
}
