package javax.xml.xpath;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class XPathException extends Exception {
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("cause", Throwable.class)};
    private static final long serialVersionUID = -1837080260374986980L;

    public XPathException(String str) {
        super(str);
        if (str != null) {
            return;
        }
        x0e.a("message can't be null");
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Throwable th = (Throwable) objectInputStream.readFields().get("cause", (Object) null);
        if (super.getCause() != null || th == null) {
            return;
        }
        try {
            super.initCause(th);
        } catch (IllegalStateException unused) {
            throw new InvalidClassException("Inconsistent state: two causes");
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.putFields().put("cause", super.getCause());
        objectOutputStream.writeFields();
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return super.getCause();
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        if (getCause() != null) {
            getCause().printStackTrace(printStream);
            printStream.println("--------------- linked to ------------------");
        }
        super.printStackTrace(printStream);
    }

    public XPathException(Throwable th) {
        super(th);
        if (th != null) {
            return;
        }
        x0e.a("cause can't be null");
        throw null;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        if (getCause() != null) {
            getCause().printStackTrace(printWriter);
            printWriter.println("--------------- linked to ------------------");
        }
        super.printStackTrace(printWriter);
    }
}
