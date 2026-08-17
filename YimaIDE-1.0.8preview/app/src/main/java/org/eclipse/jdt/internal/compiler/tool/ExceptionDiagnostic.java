package org.eclipse.jdt.internal.compiler.tool;

import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
final class ExceptionDiagnostic implements Diagnostic<JavaFileObject> {
    private final Exception exception;

    public ExceptionDiagnostic(Exception exc) {
        this.exception = exc;
    }

    public String getCode() {
        return "exception";
    }

    public long getColumnNumber() {
        return 0L;
    }

    public long getEndPosition() {
        return 0L;
    }

    public Diagnostic.Kind getKind() {
        return Diagnostic.Kind.ERROR;
    }

    public long getLineNumber() {
        return 0L;
    }

    public String getMessage(Locale locale) {
        return this.exception.toString();
    }

    public long getPosition() {
        return 0L;
    }

    public long getStartPosition() {
        return 0L;
    }

    public JavaFileObject getSource() {
        return null;
    }
}
