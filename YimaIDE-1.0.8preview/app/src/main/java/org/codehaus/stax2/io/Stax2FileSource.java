package org.codehaus.stax2.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Stax2FileSource extends Stax2ReferentialSource {
    final File _file;

    public Stax2FileSource(File file) {
        this._file = file;
    }

    @Override // org.codehaus.stax2.io.Stax2ReferentialSource, org.codehaus.stax2.io.Stax2Source
    public InputStream constructInputStream() throws IOException {
        return new FileInputStream(this._file);
    }

    @Override // org.codehaus.stax2.io.Stax2ReferentialSource, org.codehaus.stax2.io.Stax2Source
    public Reader constructReader() throws IOException {
        String encoding = getEncoding();
        return (encoding == null || encoding.length() <= 0) ? new FileReader(this._file) : new InputStreamReader(constructInputStream(), encoding);
    }

    public File getFile() {
        return this._file;
    }

    @Override // org.codehaus.stax2.io.Stax2ReferentialSource, org.codehaus.stax2.io.Stax2Source
    public URL getReference() {
        try {
            return this._file.toURL();
        } catch (MalformedURLException e) {
            StringBuilder sb = new StringBuilder("(was ");
            sb.append(e.getClass());
            String path = this._file.getPath();
            sb.append(") Could not convert File '");
            sb.append(path);
            sb.append("' to URL: ");
            sb.append(e);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
