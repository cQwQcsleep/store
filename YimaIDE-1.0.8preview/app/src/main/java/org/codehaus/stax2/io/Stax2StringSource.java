package org.codehaus.stax2.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Stax2StringSource extends Stax2BlockSource {
    final String mText;

    public Stax2StringSource(String str) {
        this.mText = str;
    }

    @Override // org.codehaus.stax2.io.Stax2BlockSource, org.codehaus.stax2.io.Stax2Source
    public InputStream constructInputStream() throws IOException {
        return null;
    }

    @Override // org.codehaus.stax2.io.Stax2BlockSource, org.codehaus.stax2.io.Stax2Source
    public Reader constructReader() throws IOException {
        return new StringReader(this.mText);
    }

    public String getText() {
        return this.mText;
    }
}
