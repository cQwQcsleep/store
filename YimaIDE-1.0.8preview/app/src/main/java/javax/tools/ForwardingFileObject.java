package javax.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.util.Objects;
import javax.tools.FileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ForwardingFileObject<F extends FileObject> implements FileObject {
    protected final F fileObject;

    public ForwardingFileObject(F f) {
        Objects.requireNonNull(f);
        this.fileObject = f;
    }

    @Override // javax.tools.FileObject
    public boolean delete() {
        return this.fileObject.delete();
    }

    @Override // javax.tools.FileObject
    public CharSequence getCharContent(boolean z) throws IOException {
        return this.fileObject.getCharContent(z);
    }

    @Override // javax.tools.FileObject
    public long getLastModified() {
        return this.fileObject.getLastModified();
    }

    @Override // javax.tools.FileObject
    public String getName() {
        return this.fileObject.getName();
    }

    @Override // javax.tools.FileObject
    public InputStream openInputStream() throws IOException {
        return this.fileObject.openInputStream();
    }

    @Override // javax.tools.FileObject
    public OutputStream openOutputStream() throws IOException {
        return this.fileObject.openOutputStream();
    }

    @Override // javax.tools.FileObject
    public Reader openReader(boolean z) throws IOException {
        return this.fileObject.openReader(z);
    }

    @Override // javax.tools.FileObject
    public Writer openWriter() throws IOException {
        return this.fileObject.openWriter();
    }

    @Override // javax.tools.FileObject
    public URI toUri() {
        return this.fileObject.toUri();
    }
}
