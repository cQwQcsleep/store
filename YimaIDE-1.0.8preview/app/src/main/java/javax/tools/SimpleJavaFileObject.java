package javax.tools;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URI;
import java.nio.CharBuffer;
import java.util.Objects;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class SimpleJavaFileObject implements JavaFileObject {
    protected final JavaFileObject.Kind kind;
    protected final URI uri;

    public SimpleJavaFileObject(URI uri, JavaFileObject.Kind kind) {
        Objects.requireNonNull(uri);
        Objects.requireNonNull(kind);
        if (uri.getPath() == null) {
            aca.a("URI must have a path: ", uri);
            throw null;
        }
        this.uri = uri;
        this.kind = kind;
    }

    public static JavaFileObject forSource(URI uri, final String str) {
        return new SimpleJavaFileObject(uri, JavaFileObject.Kind.SOURCE) { // from class: javax.tools.SimpleJavaFileObject.1
            @Override // javax.tools.SimpleJavaFileObject
            public CharSequence getCharContent(boolean z) {
                return str;
            }
        };
    }

    public boolean delete() {
        return false;
    }

    public Modifier getAccessLevel() {
        return null;
    }

    public CharSequence getCharContent(boolean z) throws IOException {
        throw new UnsupportedOperationException();
    }

    public JavaFileObject.Kind getKind() {
        return this.kind;
    }

    public long getLastModified() {
        return 0L;
    }

    public String getName() {
        return toUri().getPath();
    }

    public NestingKind getNestingKind() {
        return null;
    }

    public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
        String str2 = str + kind.extension;
        if (kind.equals(getKind())) {
            return str2.equals(toUri().getPath()) || toUri().getPath().endsWith("/".concat(str2));
        }
        return false;
    }

    public InputStream openInputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    public OutputStream openOutputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    public Reader openReader(boolean z) throws IOException {
        CharSequence charContent = getCharContent(z);
        if (charContent == null) {
            a9g.a();
            return null;
        }
        if (charContent instanceof CharBuffer) {
            CharBuffer charBuffer = (CharBuffer) charContent;
            if (charBuffer.hasArray()) {
                return new CharArrayReader(charBuffer.array());
            }
        }
        return new StringReader(charContent.toString());
    }

    public Writer openWriter() throws IOException {
        return new OutputStreamWriter(openOutputStream());
    }

    public String toString() {
        return getClass().getName() + "[" + toUri() + "]";
    }

    public URI toUri() {
        return this.uri;
    }
}
