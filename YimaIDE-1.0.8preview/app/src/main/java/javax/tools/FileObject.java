package javax.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface FileObject {
    boolean delete();

    CharSequence getCharContent(boolean z) throws IOException;

    long getLastModified();

    String getName();

    InputStream openInputStream() throws IOException;

    OutputStream openOutputStream() throws IOException;

    Reader openReader(boolean z) throws IOException;

    Writer openWriter() throws IOException;

    URI toUri();
}
