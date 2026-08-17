package javax.xml.transform;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TransformerException extends Exception {
    private static final long serialVersionUID = 975798773772956428L;
    Throwable containedException;
    SourceLocator locator;

    public TransformerException(String str, SourceLocator sourceLocator, Throwable th) {
        super((str == null || str.length() == 0) ? th == null ? "" : th.toString() : str);
        this.containedException = th;
        this.locator = sourceLocator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLocationString() {
        if (this.locator == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String systemId = this.locator.getSystemId();
        int lineNumber = this.locator.getLineNumber();
        int columnNumber = this.locator.getColumnNumber();
        if (systemId != null) {
            sb.append("; SystemID: ");
            sb.append(systemId);
        }
        if (lineNumber != 0) {
            sb.append("; Line#: ");
            sb.append(lineNumber);
        }
        if (columnNumber != 0) {
            sb.append("; Column#: ");
            sb.append(columnNumber);
        }
        return sb.toString();
    }

    private ProtectionDomain getNonPrivDomain() {
        return new ProtectionDomain(new CodeSource((URL) null, (CodeSigner[]) null), new Permissions());
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        Throwable th = this.containedException;
        if (th == this) {
            return null;
        }
        return th;
    }

    public Throwable getException() {
        return this.containedException;
    }

    public String getLocationAsString() {
        if (this.locator == null) {
            return null;
        }
        return System.getSecurityManager() == null ? getLocationString() : (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: ake
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return this.a.getLocationString();
            }
        }, new AccessControlContext(new ProtectionDomain[]{getNonPrivDomain()}));
    }

    public SourceLocator getLocator() {
        return this.locator;
    }

    public String getMessageAndLocation() {
        return Objects.toString(super.getMessage(), "") + Objects.toString(getLocationAsString(), "");
    }

    @Override // java.lang.Throwable
    public synchronized Throwable initCause(Throwable th) {
        if (this.containedException != null) {
            throw new IllegalStateException("Can't overwrite cause");
        }
        if (th == this) {
            throw new IllegalArgumentException("Self-causation not permitted");
        }
        this.containedException = th;
        return this;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        if (printWriter == null) {
            printWriter = new PrintWriter((OutputStream) System.err, true);
        }
        try {
            String locationAsString = getLocationAsString();
            if (locationAsString != null) {
                printWriter.println(locationAsString);
            }
            super.printStackTrace(printWriter);
        } catch (Throwable unused) {
        }
        try {
            Throwable exception = getException();
            for (int i = 0; i < 10 && exception != null; i++) {
                printWriter.println("---------");
                try {
                    exception.printStackTrace(printWriter);
                    if (exception instanceof TransformerException) {
                        break;
                    }
                    Throwable th = null;
                    try {
                        Method method = exception.getClass().getMethod("getException", null);
                        if (method == null) {
                            continue;
                        } else {
                            Throwable th2 = (Throwable) method.invoke(exception, null);
                            if (exception == th2) {
                                break;
                            } else {
                                th = th2;
                            }
                        }
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
                    }
                    exception = th;
                } catch (Throwable unused3) {
                    printWriter.println("Could not print stack trace...");
                }
            }
            printWriter.flush();
        } catch (Throwable th3) {
            printWriter.flush();
            throw th3;
        }
    }

    public void setLocator(SourceLocator sourceLocator) {
        this.locator = sourceLocator;
    }

    public TransformerException(Throwable th) {
        this(null, null, th);
    }

    public TransformerException(String str, Throwable th) {
        this(str, null, th);
    }

    public TransformerException(String str, SourceLocator sourceLocator) {
        this(str, sourceLocator, null);
    }

    public TransformerException(String str) {
        this(str, null, null);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        printStackTrace(new PrintWriter(printStream));
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(new PrintWriter((OutputStream) System.err, true));
    }
}
